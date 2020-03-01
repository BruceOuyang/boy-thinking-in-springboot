package club.bugmakers.thinking.in.spring.dependency.lookup;

import club.bugmakers.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 通过 {@link ObjectProvider} 进行依赖查找
 */
public class ObjectProviderDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 将当前类作为配置类
        applicationContext.register(ObjectProviderDemo.class);

        // 启动应用上下文
        applicationContext.refresh();

        // 按照类型查找 beans
        lookupByObjectProvider(applicationContext);
        lookupIfAvaliable(applicationContext);
        lookupByStreamOps(applicationContext);

        // 关闭应用上下文
        applicationContext.close();
    }

    /**
     * 延迟查找 + 使用 java 8 特性
     * @param applicationContext
     */
    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);

//        Iterable<String> stringIterable = objectProvider;
//        for (String str : stringIterable) {
//            System.out.println(str);
//        }

        objectProvider.stream().forEach(System.out::println);
    }

    /**
     * 延迟查找
     * @param applicationContext
     */
    private static void lookupIfAvaliable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        User user = beanProvider.getIfAvailable(User::createUser);
        System.out.println("当前 User 对象：" + user);
    }

    /**
     * 默认方法名就是 bean 的名称
     * @return
     */
    @Bean
    @Primary // 标识为主要的
    public String helloWorld() {
        return "Hello World";
    }

    @Bean
    public String message() {
        return "Message";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }
}
