package club.bugmakers.thinking.in.spring.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * 外部化配置作为依赖来源
 */
@Configuration
@PropertySource(value = "META-INF/default.properties", encoding = "UTF-8")
public class ExternalConfigurationDependencySource {

    @Value("${usr.id:-1}")
    private Long id;

    @Value("${usr.name}")
    private String name;

    @Value("${usr.resource:classpath://default.properties}")
    private Resource resource;

    public static void main(String[] args) {

        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 测试一：注册 Configuration Class (配置类，代替 XML 文件)
        applicationContext.register(ExternalConfigurationDependencySource.class);

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找
        ExternalConfigurationDependencySource demo = applicationContext.getBean(ExternalConfigurationDependencySource.class);
        System.out.println("demo.id = " + demo.id);
        System.out.println("demo.name = " + demo.name);
        System.out.println("demo.resource = " + demo.resource);

        // 关闭应用上下文
        applicationContext.close();
    }
}
