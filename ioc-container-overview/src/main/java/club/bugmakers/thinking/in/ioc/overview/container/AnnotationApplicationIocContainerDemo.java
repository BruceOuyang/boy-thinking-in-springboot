package club.bugmakers.thinking.in.ioc.overview.container;

import club.bugmakers.thinking.in.ioc.overview.domain.SuperUser;
import club.bugmakers.thinking.in.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * 注解能力 {@link ApplicationContext} 作为 IOC 容器示例
 */
public class AnnotationApplicationIocContainerDemo {

    public static void main(String[] args) {
        // 创建 beanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 将当前类 AnnotationApplicationIocContainerDemo 作为配置类（Configuration Class）
        applicationContext.register(AnnotationApplicationIocContainerDemo.class);

        // 启动应用上下文
        applicationContext.refresh();

        // 查找 bean
        lookupCollectionByType(applicationContext);

        // 关闭应用上下文
        applicationContext.close();

        // 查找 bean
        lookupCollectionByType(applicationContext);
    }

    @Bean
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("BruceOuyang");
        return user;
    }
    @Bean
    public SuperUser superUser() {
        SuperUser user = new SuperUser();
        user.setId(1L);
        user.setName("BruceOuyang");
        user.setAddr("Shanghai");
        return user;
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查询到的所有的 User 集合对象：" + users);
        }
    }
}
