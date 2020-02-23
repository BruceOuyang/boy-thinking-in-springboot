package club.bugmakers.thinking.in.spring.bean.definition;

import club.bugmakers.thinking.in.spring.bean.factory.DefaultUserFactory;
import club.bugmakers.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean 初始化 demo
 * 1.@PostContruct
 * 2.指定bean的initMehtod
 * 3.实现InitializingBean的afterPropertiesSet方法
 *
 * 优先级：1>3>2
 */
@Configuration
public class BeanInitializationDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 测试一：注册 Configuration Class (配置类，代替 XML 文件)
        applicationContext.register(BeanInitializationDemo.class);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找 UserFactory
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);

        // 关闭 Spring 应用上下文
        applicationContext.close();
    }

    @Bean(initMethod = "initUserFactory")
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
