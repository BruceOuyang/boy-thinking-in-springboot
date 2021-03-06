package club.bugmakers.thinking.in.spring.ioc.bean.scope;

import club.bugmakers.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

/**
 * Bean 的作用域示例
 *   Singleton  单例模式  --- 默认
 *   Prototype  原型模式
 */
public class BeanScopeDemo implements DisposableBean {

    // Spring Bean 默认作用域就是 SINGLETON
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public static User singletonUser(){
        return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser(){
        return createUser();
    }

    public static User createUser(){
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;

    @Autowired
    private Map<String, User> users;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    public static void main(String[] args) {

        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 测试一：注册 Configuration Class (配置类，代替 XML 文件)
        applicationContext.register(BeanScopeDemo.class);


        // 加入生命周期管理
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
           beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
               @Override
               public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                   System.out.printf("%s Bean 名称: %s 初始化后回调...%n", bean.getClass().getName(), beanName);
                   return bean;
               }
           });
        });

        // 启动应用上下文
        applicationContext.refresh();

        // 结论一：
        // Singleton Bean 无论是依赖查找还是依赖注入，均为同一个对象
        // Prototype Bean 无论是依赖查找还是依赖注入，均为新生成的对象

        // 结论二：
        // 如果依赖注入集合类型的对象，Singleton Bean 和 Prototype Bean 均会存在一个
        // Prototype Bean 有别于其他地方的依赖注入 PrototypeBean
        scopedBeansByLookup(applicationContext);
        scopedBeansByInjection(applicationContext);

        // 关闭应用上下文
        applicationContext.close();
    }

    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            User singleton = applicationContext.getBean("singletonUser", User.class);
            System.out.println("singletonUser: " + singleton);

            User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
            System.out.println("prototypeUser: " + prototypeUser);
        }
    }

    private static void scopedBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo demo = applicationContext.getBean(BeanScopeDemo.class);

        System.out.println("singletonUser: " + demo.singletonUser);
        System.out.println("prototypeUser: " + demo.prototypeUser);
        System.out.println("prototypeUser1: " + demo.prototypeUser1);
        System.out.println("prototypeUser2: " + demo.prototypeUser2);
        System.out.println("users: " + demo.users);
    }

    @Override
    public void destroy() throws Exception {

        System.out.println("当前 BeanScopeDemo 正在销毁中....");

        // 销毁 prototype bean
        this.prototypeUser.destroy();
        this.prototypeUser1.destroy();
        this.prototypeUser2.destroy();

        // 销毁集合类型中的 prototype bean
        for (Map.Entry<String, User> entry: this.users.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if (beanDefinition.isPrototype()) {
                User user = entry.getValue();
                user.destroy();
            }
        }

        System.out.println("当前 BeanScopeDemo 销毁完成....");
    }
}
