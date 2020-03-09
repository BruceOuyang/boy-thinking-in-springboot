package club.bugmakers.thinking.in.spring.ioc.bean.scope;

import club.bugmakers.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class ThreadLocalScopeDemo {

    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    public User user() {
        return createUser();
    }

    public static User createUser(){
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    public static void main(String[] args) {

        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 测试一：注册 Configuration Class (配置类，代替 XML 文件)
        applicationContext.register(ThreadLocalScopeDemo.class);

        // 加入生命周期管理
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            // 注册自定义 scope
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找
        scopedBeansByLookup(applicationContext);

        // 关闭应用上下文
        applicationContext.close();
    }

    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
//        for (int i = 0; i < 3; i++) {
//            User user = applicationContext.getBean("user", User.class);
//            System.out.println("user: " + user);
//        }

        for (int i = 0; i < 3; i++) {

            Thread thread = new Thread(() -> {
                User user = applicationContext.getBean("user", User.class);
                System.out.printf("[thread id :%d] user = %s%n: ", Thread.currentThread().getId(), user);
            });

            // 启动线程
            thread.start();

            // 强制线程执行完成
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void scopedBeansByInjection(AnnotationConfigApplicationContext applicationContext) {

    }

}
