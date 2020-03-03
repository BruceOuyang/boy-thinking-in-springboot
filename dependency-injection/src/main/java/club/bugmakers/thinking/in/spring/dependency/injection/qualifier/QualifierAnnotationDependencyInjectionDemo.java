package club.bugmakers.thinking.in.spring.dependency.injection.qualifier;

import club.bugmakers.thinking.in.spring.dependency.injection.UserHolder;
import club.bugmakers.thinking.in.spring.dependency.injection.annotation.UserGroup;
import club.bugmakers.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * 基于 XML 资源的依赖 Setter 方法注入示例
 */
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("user") // 指定 bean 名称或 id
    private User namedUser;

    @Bean
    @Qualifier
    public User user1() {
        return createUser(7L);
    }

    @Bean
    @Qualifier
    public User user2() {
        return createUser(8L);
    }

    @Bean
    @UserGroup
    public User user3() {
        return createUser(9L);
    }

    @Bean
    @UserGroup
    public User user4() {
        return createUser(10L);
    }

    private static User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    /**
     * user + namedUser
     */
    @Autowired
    private Collection<User> allUsers;

    /**
     * user1 + user2 + user3 + user4
     */
    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers;

    /**
     * user3 + user4
     */
    @Autowired
    @UserGroup
    private Collection<User> groupedUsers;

    public static void main(String[] args) {

        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找并且创建 Bean
        QualifierAnnotationDependencyInjectionDemo bean = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);
        System.out.println("bean.user = " + bean.user);
        System.out.println("bean.namedUser = " + bean.namedUser);
        System.out.println("bean.allUsers = " + bean.allUsers);
        System.out.println("bean.qualifierUsers = " + bean.qualifierUsers);
        System.out.println("bean.groupedUsers = " + bean.groupedUsers);

        // 关闭应用上下文
        applicationContext.close();
    }
}
