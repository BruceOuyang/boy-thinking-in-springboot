package club.bugmakers.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 参考 {@link UserFactory} 默认实现
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

     // init 1.基于 @PostConstruct 注解
    @PostConstruct
    public void init() {
        System.out.println("@PostContruct: UserFactory 初始化中...");
    }

    // init 2.实现InitializingBean#afterPropertiesSet()
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet(): UserFactory 初始化中...");
    }

    // init 3.自定义初始化方法
    public void initUserFactory() {
        System.out.println("自定义初始化方法 initUserFactory: UserFactory 初始化中...");
    }

    // destroy 1.基于 @PreDestroy 注解
    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy: UserFactory 销毁中...");
    }

    // destroy 2.实现DisposableBean#destroy()
    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy(): UserFactory 销毁中...");
    }

    // destroy 3.自定义销毁方法
    public void destroyUserFactory() {
        System.out.println("自定义销毁方法 destroyUserFactory: UserFactory 销毁中...");
    }
}
