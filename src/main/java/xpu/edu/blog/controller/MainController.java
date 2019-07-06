package xpu.edu.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/")
@Slf4j
public class MainController {

    @RequestMapping(value = {"/", "/index", "index.html"})
    public String index(@CookieValue(value = "userId", required = false) String userId, Map<String, Object> map){
        log.info("userId={}", userId);
        if(userId != null) map.put("userId", userId);
        return "index";
    }

    @RequestMapping(value = {"/login"})
    public String login(Map<String, Object> map){
        map.put("ret", false);
        return "user/login";
    }

    @RequestMapping(value = {"/register"})
    public String register(){
        return "user/register";
    }

    @RequestMapping(value = {"/404"})
    public String error(){
        return "404";
    }
}
