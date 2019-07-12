package xpu.edu.blog.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    @PostMapping("/login")
    public String login(@RequestParam("adminId") String adminId,
                        @RequestParam("adminPassword") String adminPassword,
                        Map<String, Object> map, HttpServletResponse response){
        if(adminId.equals("123456") && adminPassword.equals("123456")){
            Cookie cookie = new Cookie("userId", "123456");
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            return "redirect:/admin/center";
        }else{
            map.put("ret", true);
            return "admin/user_login";
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
    public String center(@CookieValue(value = "userId", required = false) String userId){
        if(userId == null) return "redirect:/login";
        return "admin/center";
    }

    @GetMapping("/user_discuss")
    public String user_discuss(){
        return "admin/user_discuss";
    }
}
