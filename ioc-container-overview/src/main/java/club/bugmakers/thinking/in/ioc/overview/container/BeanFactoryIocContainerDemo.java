package club.bugmakers.thinking.in.ioc.overview.container;

import club.bugmakers.thinking.in.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * {@link BeanFactory} 作为 IOC 容器示例
 */
public class BeanFactoryIocContainerDemo {

    public static void main(String[] args) {
        // 创建 beanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        int beanDefinitionsCount = reader.loadBeanDefinitions(location);
        System.out.println("bean 定义加载的数量：" + beanDefinitionsCount);

        // 依赖查找集合
        lookupCollectionByType(beanFactory);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查询到的所有的 User 集合对象：" + users);
        }
    }
}
