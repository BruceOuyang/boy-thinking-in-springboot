package club.bugmakers.thinking.in.spring.bean.definition;

import club.bugmakers.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 别名示例
 */
public class BeanAliasDemo {

    public static void main(String[] args) {

        // 启动应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context.xml");

        // 通过别名 bruce-user 获取 user 的 bean
        User bruce_user = beanFactory.getBean("bruce-user", User.class);
        User user = beanFactory.getBean("user", User.class);

        System.out.println("bruce-user 是否与 user 相同：" + (bruce_user == user));
    }
}
