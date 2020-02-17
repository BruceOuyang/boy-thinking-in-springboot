package club.bugmakers.thinking.in.spring.ioc.overview.domain;

import club.bugmakers.thinking.in.spring.ioc.overview.annotation.Super;

/**
 * 超级用户
 */
@Super
public class SuperUser extends User {

    private String addr;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "addr='" + addr + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
