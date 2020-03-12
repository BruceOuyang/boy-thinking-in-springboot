package club.bugmakers.thinking.in.spring.bean.lifecycle;

import club.bugmakers.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * BeanDefinition 合并示例
 */
public class MergeBeanDifinitionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 基于 XML 资源 BeanDefinitionReader
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        String location = "META-INF/dependency-lookup-context.xml";
        // 基于 classpath 加载 XML 文件
        Resource resource = new ClassPathResource(location);
        // 指定字符集编码 UTF-8
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int beanNumbers = reader.loadBeanDefinitions(encodedResource);
        System.out.println("已加载 BeanDefinition 数量：" + beanNumbers);

        // 依赖查找
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(superUser);
    }
}
