package club.bugmakers.thinking.in.spring.dependency.injection.constructor;

import club.bugmakers.thinking.in.spring.dependency.injection.UserHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于 XML 资源的依赖构造器注入示例
 */
public class XmlDependencyConstructInjectionDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String xmlResourcePath = "classpath:/META-INF/dependency-constructor-injection.xml";

        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 依赖查找并且创建 Bean
        UserHolder bean = beanFactory.getBean(UserHolder.class);
        System.out.println(bean);
    }
}
