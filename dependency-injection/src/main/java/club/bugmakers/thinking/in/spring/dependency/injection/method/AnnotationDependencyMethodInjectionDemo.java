package club.bugmakers.thinking.in.spring.dependency.injection.method;

import club.bugmakers.thinking.in.spring.dependency.injection.UserHolder;
import club.bugmakers.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于 Annotation 资源的依赖 方法 注入示例
 */
public class AnnotationDependencyMethodInjectionDemo {

    private UserHolder userHolder;
    private UserHolder userHolderResource;

    @Autowired
    public void initUserHolder(UserHolder userHolder) {
        this.userHolder = userHolder;
    }

    @Resource
    public void initUserHolderResource(UserHolder userHolder) {
        this.userHolderResource = userHolder;
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }

    public static void main(String[] args) {

        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 测试一：注册 Configuration Class (配置类，代替 XML 文件)
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找 AnnotationDependencyFieldInjectionDemo# Bean
        AnnotationDependencyMethodInjectionDemo demo = applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);

        // 依赖查找并且创建 Bean
        UserHolder bean = demo.userHolder;
        System.out.println(bean);
        System.out.println(demo.userHolderResource);   // byType 的方式
        System.out.println(bean == demo.userHolderResource);

        // 关闭应用上下文
        applicationContext.close();
    }
}
