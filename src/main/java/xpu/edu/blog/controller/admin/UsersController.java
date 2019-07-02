package xpu.edu.blog.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xpu.edu.blog.service.impl.UserServiceImpl;

import java.util.Map;

/**
 * 超级管理员
 */
@Controller
@RequestMapping("/users")
@Slf4j
public class UsersController {
    private final UserServiceImpl userService;

    @Autowired
    public UsersController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * 展示所有用户
     */
    @GetMapping
    public String list(Map<String, Object> map){
        map.put("userList", userService.listUsers());
        map.put("title", "用户管理");
        return "admin/user";
    }
}