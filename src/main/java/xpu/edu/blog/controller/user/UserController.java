package xpu.edu.blog.controller.user;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xpu.edu.blog.entity.UserInfo;
import xpu.edu.blog.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/login")
    public String login(String userIdOrEmail, String userPassword,
                        Map<String, Object> map, HttpServletResponse response){
        UserInfo findRet = service.getUserBuIdOrEmail(userIdOrEmail, userPassword);
        if(findRet == null){
            map.put("ret", true);
            return "/user/login";
        }else{
            log.info("userInfo={}", findRet);
            Cookie cookie = new Cookie("userId", findRet.getUserId().toString());
            cookie.setPath("/");
            cookie.setMaxAge(3600 * 30);
            response.addCookie(cookie);
            return "redirect:/";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            if("userId".equals(cookie.getName())){
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return "redirect:/";
    }


    @GetMapping("/center")
    public String center(){
        return "user/center";
    }
}
