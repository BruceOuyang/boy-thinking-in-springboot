package club.bugmakers.thinking.in.spring.dependency.injection.resolution;

import club.bugmakers.thinking.in.spring.dependency.injection.UserHolder;
import club.bugmakers.thinking.in.spring.dependency.injection.annotation.InjectedUser;
import club.bugmakers.thinking.in.spring.dependency.injection.annotation.MyAutowired;
import club.bugmakers.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.*;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

/**
 * 注解驱动的依赖注入处理过程
 *
 * 入口 -> DefaultListableBeanFactory#resolveDependency
 * 依赖描述符 -> DependencyDescriptor
 * 自动绑定候选对象处理器 -> AutowireCandidateResolver
 */
public class AnnotationDependencyInjectionResolutionDemo {

    @Autowired
    @Lazy
    private User lazyUser;

    /**
     * 依赖查找（处理）
     * DependencyDescriptor ->
     *  必须(require = true)
     *  实时注入(eager = true)
     *  通过类型(User.class)依赖查找
     *  通过字段("user")依赖查找
     *  是否首要(primary = true)
     */
    @Autowired
    private User user;

    /**
     * 集合类型注入
     */
    @Autowired
    private Map<String, User> users; // user + superUser

    /**
     * Optional Dependency
     */
    @Autowired
    private Optional<User> optionalUser;

    @Inject
    private User injectUser;

    /**
     * Optional Dependency
     */
    @MyAutowired
    private Optional<User> optionalUserMyAutowired;

    @InjectedUser
    private User myInjectedUser;

    //    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        // 1.替换原有注解处理，使用新注解 @InjectedUser (需要配合 @Order 一起使用)
        beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);

        // 2.@Autowired + @Inject + @InjectedUser（需要配合 beanName = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME 一起使用）
//        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(Arrays.asList(Autowired.class, Inject.class, InjectedUser.class));
//        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
        return beanPostProcessor;
    }

    public static void main(String[] args) {

        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 测试一：注册 Configuration Class (配置类，代替 XML 文件)
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找 AnnotationDependencyFieldInjectionDemo# Bean
        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

        // 依赖查找并且创建 Bean
        System.out.println("demo.lazyUser = " + demo.lazyUser);
        System.out.println("demo.user = " + demo.user);
        System.out.println("demo.users = " + demo.users);
        System.out.println("demo.optionalUser = " + demo.optionalUser);
        System.out.println("demo.injectUser = " + demo.injectUser);
        System.out.println("demo.optionalUserMyAutowired = " + demo.optionalUserMyAutowired);
        System.out.println("demo.myInjectedUser = " + demo.myInjectedUser);

        // 关闭应用上下文
        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
