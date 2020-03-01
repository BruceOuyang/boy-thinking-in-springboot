package club.bugmakers.thinking.in.spring.dependency.injection;

import club.bugmakers.thinking.in.spring.ioc.overview.domain.User;

/**
 * {@link User} 的 Holder 类
 */
public class UserHolder {

    public UserHolder(){}

    public UserHolder(User user) {
        this.user = user;
    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
