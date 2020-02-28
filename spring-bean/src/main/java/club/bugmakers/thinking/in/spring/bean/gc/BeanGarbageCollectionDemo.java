package club.bugmakers.thinking.in.spring.bean.gc;

import club.bugmakers.thinking.in.spring.bean.definition.BeanInitializationDemo;
import club.bugmakers.thinking.in.spring.bean.factory.DefaultUserFactory;
import club.bugmakers.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Bean 垃圾回收（GC） demo
 *
 * Spring Bean 覆盖的 finalize 方法会被 GC 回收
 */
@Configuration
public class BeanGarbageCollectionDemo {

    public static void main(String[] args) throws InterruptedException {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 测试一：注册 Configuration Class (配置类，代替 XML 文件)
        applicationContext.register(BeanInitializationDemo.class);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        Thread.sleep(1000L);

        // 关闭 Spring 应用上下文
        applicationContext.close();

        Thread.sleep(2000L);

        // 强制触发 GC
        System.gc();

        Thread.sleep(2000L);
    }

}
