package club.bugmakers.thinking.in.spring.bean.definition;

import club.bugmakers.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化示例
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {

        // 启动应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
        User userByStaticMethod = beanFactory.getBean("user-by-static-method", User.class);
        User userByInstanceMehtod = beanFactory.getBean("user-by-instance-method", User.class);
        User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println(userByStaticMethod);
        System.out.println(userByInstanceMehtod);
        System.out.println(userByFactoryBean);
        System.out.println(userByInstanceMehtod == userByStaticMethod);
    }
}
