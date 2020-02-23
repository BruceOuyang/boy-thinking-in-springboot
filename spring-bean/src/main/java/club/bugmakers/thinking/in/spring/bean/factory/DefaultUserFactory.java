package club.bugmakers.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * 参考 {@link UserFactory} 默认实现
 */
public class DefaultUserFactory implements UserFactory, InitializingBean {

     // 1.基于 @PostConstruct 注解
    @PostConstruct
    public void init() {
        System.out.println("@PostContruct: UserFactory 初始化中...");
    }

    // 2.自定义初始化方法
    public void initUserFactory() {
        System.out.println("自定义初始化方法 initUserFactory: UserFactory 初始化中...");
    }

    // 3.实现InitializingBean#afterPropertiesSet()
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet(): UserFactory 初始化中...");
    }
}
