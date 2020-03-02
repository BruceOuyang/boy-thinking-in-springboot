package club.bugmakers.thinking.in.spring.dependency.injection.setter;

import club.bugmakers.thinking.in.spring.dependency.injection.UserHolder;
import club.bugmakers.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于 Annotation 资源的依赖 Setter 方法注入示例
 */
public class AnnotationDependencySetterInjectionDemo {

    public static void main(String[] args) {

        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 测试一：注册 Configuration Class (配置类，代替 XML 文件)
        applicationContext.register(AnnotationDependencySetterInjectionDemo.class);

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找并且创建 Bean
        UserHolder bean = applicationContext.getBean(UserHolder.class);
        System.out.println(bean);

        // 关闭应用上下文
        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }
}
