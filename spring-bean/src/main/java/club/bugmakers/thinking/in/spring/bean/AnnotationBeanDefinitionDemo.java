package club.bugmakers.thinking.in.spring.bean;

import club.bugmakers.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 注解 BeanDefinition 示例
 */
// 3.通过 @Import 来进行导入
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {

        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 测试一：注册 Configuration Class (配置类，代替 XML 文件)
        applicationContext.register(AnnotationBeanDefinitionDemo.class);

        // 测试二：通过 BeanDefinition 注册 API 实现
        // 1.命名 bean 的注册方式
        registryUserBeanDefinition(applicationContext, "ss-user");
        // 2.非命名 bean 的注册方式
        registryUserBeanDefinition(applicationContext);

        // 启动应用上下文
        applicationContext.refresh();

        // 按照类型查找 beans
        Map<String, Config> configBeans = applicationContext.getBeansOfType(Config.class);
        Map<String, User> userBeans = applicationContext.getBeansOfType(User.class);
        System.out.println("Config 类型的所有 Beans：" + configBeans);
        System.out.println("User 类型的所有 Beans：" + userBeans);

        // 关闭应用上下文
        applicationContext.close();
    }

    // 2. 通过定义当前类为 Spring Bean （组件）
    @Component
    public static class Config {

        // 1.通过 @Bean 方式定义
        /**
         * 通过 Java 注解的方式定义一个 Bean
         * @return
         */
        @Bean(name = {"user", "bruce-user"}) // 第一个是名称，第二个是别名
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("BruceOuyang");
            return user;
        }
    }

    /**
     * 注册 BeanDefinition
     * @param registry
     * @param beanName
     */
    public static void registryUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id", 1L)
                .addPropertyValue("name", "BruceOuyang");

        // 判断如果 beanName 参数是否存在
        if (StringUtils.hasText(beanName)) {
            // 命名 bean 的注册方式
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            // 非命名 bean
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }

    }

    public static void registryUserBeanDefinition(BeanDefinitionRegistry registry) {
        registryUserBeanDefinition(registry, null);
    }
}
