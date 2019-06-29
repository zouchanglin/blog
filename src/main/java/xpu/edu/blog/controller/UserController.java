package xpu.edu.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xpu.edu.blog.domain.UserInfo;
import xpu.edu.blog.service.impl.UserServiceImpl;

import java.util.Map;

@Controller
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    /**
     * 展示所有用户
     */
    @GetMapping
    public String list(Map<String, Object> map){
        map.put("userList", userService.listUsers());
        map.put("title", "用户管理");
        return "users/list";
    }

    /**
     * 查找用户
     */
    @GetMapping("{id}")
    public String view(@PathVariable("id") String id, Map<String, Object> map){
        UserInfo userInfo = userService.getUserById(id);
        map.put("user", userInfo);
        map.put("title", "查看用户");
        return "users/view";
    }

    /**
     * 创建表单页面
     */
    @GetMapping("form")
    public String createForm(Map<String, Object> map){
        map.put("user", new UserInfo());
        map.put("title", "创建用户");
        return "users/form";
    }

    /**
     * 新增用户
     */
    @PostMapping
    public String addNewUser(UserInfo userInfo){
        UserInfo saveRet;
        log.info("userInfo={}", userInfo);
        if("".equals(userInfo.getUserId()) || userInfo.getUserId() == null)
            saveRet = userService.addUser(userInfo);
        else
            saveRet = userService.updateUser(userInfo);
        log.info("saveRet={}", saveRet);
        return "redirect:/users";
    }

    /**
     * 删除用户
     */
    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        userService.deleteUser(id);
        return new ModelAndView("redirect:/users");//重定向到list
    }

    /**
     * 修改用户
     */
    @GetMapping("modify/{id}")
    public String modify(@PathVariable("id") String id, Map<String, Object> map){
        UserInfo findRet = userService.getUserById(id);
        map.put("user", findRet);
        map.put("title", "修改用户");
        return "users/form";
    }
}