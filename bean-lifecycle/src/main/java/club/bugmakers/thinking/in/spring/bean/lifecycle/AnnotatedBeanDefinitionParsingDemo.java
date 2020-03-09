package club.bugmakers.thinking.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 注解 BeanDefinition 解析示例
 */
public class AnnotatedBeanDefinitionParsingDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 实例化基于 Java 注解 AnnotatedBeanDefinitionReader 的实现
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);

        int beforeCount = beanFactory.getBeanDefinitionCount();

        // 注册当前类（非 @Component class）
        reader.register(AnnotatedBeanDefinitionParsingDemo.class);

        int afterCount = beanFactory.getBeanDefinitionCount();

        int beanNumbers = afterCount - beforeCount;

        System.out.println("已加载的 BeanDefinition 数量：" + beanNumbers);

        // 普通的 class 作为 Component 注册到 Spring IoC 容器后，通常命名为  annotatedBeanDefinitionParsingDemo （首字母小写）
        // Bean 名称生成来自于 BeanNameGenerator，注解实现 AnnotatedBeanNameGenerator
        AnnotatedBeanDefinitionParsingDemo bean = beanFactory.getBean(AnnotatedBeanDefinitionParsingDemo.class);
        System.out.println(bean);
    }
}
