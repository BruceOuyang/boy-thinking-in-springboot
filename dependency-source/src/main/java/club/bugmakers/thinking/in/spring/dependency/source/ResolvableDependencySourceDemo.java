package club.bugmakers.thinking.in.spring.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ResolvableDependency 作为依赖来源
 */
public class ResolvableDependencySourceDemo {

    @Autowired
    private String value;

    @PostConstruct
    public void init(){
        System.out.println("value = " + value);
    }

    public static void main(String[] args) {

        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 测试一：注册 Configuration Class (配置类，代替 XML 文件)
        applicationContext.register(ResolvableDependencySourceDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            // 注册 ResolvableDependency
            beanFactory.registerResolvableDependency(String.class, "Hello World");
        });

        // 启动应用上下文
        applicationContext.refresh();

        // 关闭应用上下文
        applicationContext.close();
    }
}
