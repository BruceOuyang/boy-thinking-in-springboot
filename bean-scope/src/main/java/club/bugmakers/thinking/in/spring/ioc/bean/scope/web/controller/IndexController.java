package club.bugmakers.thinking.in.spring.ioc.bean.scope.web.controller;

import club.bugmakers.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private User user; // 通过 cg-lib 代理的对象

    @GetMapping("/index.html")
    public String index(Model model){
        model.addAttribute("userObject", user);
        return "index";
    }
}
