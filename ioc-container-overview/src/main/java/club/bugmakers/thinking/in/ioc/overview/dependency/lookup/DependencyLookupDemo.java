package club.bugmakers.thinking.in.ioc.overview.dependency.lookup;

import club.bugmakers.thinking.in.ioc.overview.annotation.Super;
import club.bugmakers.thinking.in.ioc.overview.domain.User;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找示例
 * 1.通过名称
 * 2.通过类型
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {

        // 配置 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");

        // 启动 Spring 应用上下文

        // 按照 id 查找
//        lookupInLazy(beanFactory);
//        lookupInRealTime(beanFactory);

        // 按照类型查找
        lookupByType(beanFactory);

        // 按照类型查找集合对象
        lookupCollectionByType(beanFactory);

        // 按照注解查找
        lookupByAnnotationType(beanFactory);
    }

    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查询 标注@Super 所有的 User 集合对象：" + users);
        }
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查询到的所有的 User 集合对象：" + users);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("实时查找：" + user);
    }

    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找：" + user);
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找：" + user);
    }
}
