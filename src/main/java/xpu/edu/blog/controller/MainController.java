package xpu.edu.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * 主页控制器
 */
@Controller
public class MainController {
    @GetMapping("/")
    public String root(){
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Map<String, Object> map){
        map.put("loginError", true);
        map.put("errorMsg", "登录失败,用户名或者密码错误");
        return "login";
    }

    //TODO 8:20 11-3 11-4
}
