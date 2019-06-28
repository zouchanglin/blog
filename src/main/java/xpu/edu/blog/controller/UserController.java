package xpu.edu.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import xpu.edu.blog.domain.UserInfo;
import xpu.edu.blog.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    /**
     * 展示所有用户
     */
    @GetMapping
    public ModelAndView list(Model model){
        model.addAttribute("userList", userService.listUsers());
        model.addAttribute("title", "用户管理");
        return new ModelAndView("users/list", "userModel", model);
    }

    /**
     * 查找用户
     */
    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") String id, Model model){
        UserInfo userInfo = userService.getUserById(id);
        model.addAttribute("user", userInfo);
        model.addAttribute("title", "查看用户");
        return new ModelAndView("users/view", "userModel", model);
    }

    /**
     * 创建表单页面
     */
    @GetMapping("/form")
    public ModelAndView createForm(Model model){
        model.addAttribute("user", new UserInfo());
        model.addAttribute("title", "创建用户");
        return new ModelAndView("users/form", "userModel", model);
    }

    /**
     * 新增用户
     */
    @PostMapping
    public ModelAndView addNewUser(UserInfo userInfo){
        userInfo = userService.addUser(userInfo);
        return new ModelAndView("redirect:/users");//重定向到list
    }

    /**
     * 删除用户
     */
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        userService.deleteUser(id);
        return new ModelAndView("redirect:/users");//重定向到list
    }

    /**
     * 修改用户
     */
    @GetMapping("/modify/{id}")
    public ModelAndView modify(@PathVariable("id") String id, Model model){
        UserInfo findRet = userService.getUserById(id);
        model.addAttribute("user", findRet);
        model.addAttribute("title", "修改用户");
        return new ModelAndView("users/form", "userModel", model);
    }
}