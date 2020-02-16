package club.bugmakers.thinking.in.ioc.overview.dependency.injection;

import club.bugmakers.thinking.in.ioc.overview.annotation.Super;
import club.bugmakers.thinking.in.ioc.overview.domain.User;
import club.bugmakers.thinking.in.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * 依赖注入示例
 * 1.通过名称
 * 2.通过类型
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {

        // 配置 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");

        // 依赖来源一：这个是自定义 bean
        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository");
        System.out.println(userRepository.getUsers());

        // 依赖来源二：依赖注入（容器内建依赖）
        System.out.println(userRepository.getBeanFactory());

        // 查看注入的beanFactory和最上面的beanFactory是否对等（）
        System.out.println(userRepository.getBeanFactory() == beanFactory);

        // 以上结果是否定的，最上面的beanFactory是通过依赖查找获取的，而userRepository中的beanFactory是依赖注入得到的，因此，依赖查找和依赖注入两者的依赖是不同源的

        System.out.println(userRepository.getUserObjectFactory().getObject());

        System.out.println(userRepository.getApplicationContextObjectFactory().getObject() == beanFactory);

        // 依赖来源三：容器内建 bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("获取 Environment 类型的 bean：" + environment);
    }

    private static void whoIsIocContainer(BeanFactory beanFactory) {
        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository");
        System.out.println(userRepository.getBeanFactory() == beanFactory);
    }

}
