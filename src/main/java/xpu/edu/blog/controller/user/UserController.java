package xpu.edu.blog.controller.user;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
            return "user/login";
        }else{
            log.info("userInfo={}", findRet);
            Cookie cookie = new Cookie("userId", findRet.getUserId());
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

    @PostMapping("/update")
    public String updateUserInfo(@CookieValue(value = "userId", required = false) String id,
                                 @RequestParam("userEmail") String userEmail,
                                 @RequestParam("userName") String userName,
                                 Map<String, Object> map){
        UserInfo findResult = service.getUserById(id);
        findResult.setUserEmail(userEmail);
        findResult.setUserName(userName);
        map.put("user", service.updateUser(findResult));
        return "user/center/user_info";
    }

    @GetMapping("/center")
    public String center(@CookieValue(value = "userId", required = false) String userId){
        if(userId == null) return "redirect:/login";
        return "user/center";
    }

    @GetMapping("/user_info")
    public String user_info(@CookieValue(value = "userId", required = false) String userId, Map<String, Object> map){
        if(userId == null) return "redirect:/login";
        map.put("user", service.getUserById(userId));
        return "user/center/user_info";
    }

    @GetMapping("/user_blog")
    public String user_blog(){
        //return "user/center/user_blog";
        return "redirect:/blog/my_list";
    }

    @GetMapping("/user_collect")
    public String user_collect(){
        return "user/center/user_collect";
    }

    @GetMapping("/user_discuss")
    public String user_discuss(){
        return "user/center/user_discuss";
    }

}
