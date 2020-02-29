package club.bugmakers.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性依赖查找
 */
public class HierarchicalDependencyLookupDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 将当前类作为配置类
        applicationContext.register(HierarchicalDependencyLookupDemo.class);

        // 1.获取 HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getDefaultListableBeanFactory();
        System.out.println("当前 BeanFactory 的 ParentBeanFactory：" + beanFactory.getParentBeanFactory());

        // 2.设置 parentBeanFactory
        HierarchicalBeanFactory parentFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentFactory);
        System.out.println("当前 BeanFactory 的 ParentBeanFactory：" + beanFactory.getParentBeanFactory());

        displayContainsLocalBean(beanFactory, "user");
        displayContainsLocalBean(parentFactory, "user");

        displayContainsBean(beanFactory, "user");
        displayContainsBean(parentFactory, "user");

        // 启动应用上下文
        applicationContext.refresh();

        // 按照类型查找 beans

        // 关闭应用上下文
        applicationContext.close();
    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 local bean[name : %s] : %s \n", beanFactory, beanName, containsBean(beanFactory, beanName));
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        // 判断父容器是否包含
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory parentHierarchcalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            return containsBean(parentHierarchcalBeanFactory, beanName);
        }
        // 判断当前容器是否包含
        return beanFactory.containsLocalBean(beanName);
    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 local Bean[name : %s] : %s \n", beanFactory, beanName, beanFactory.containsLocalBean(beanName));
    }

    private static HierarchicalBeanFactory createParentBeanFactory(){
        // 创建 beanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        return beanFactory;
    }
}
