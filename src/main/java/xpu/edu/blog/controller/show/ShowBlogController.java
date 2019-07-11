package xpu.edu.blog.controller.show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xpu.edu.blog.entity.BlogInfo;
import xpu.edu.blog.entity.CategoryUser;
import xpu.edu.blog.entity.UserInfo;
import xpu.edu.blog.service.BlogService;
import xpu.edu.blog.service.CategoryUserService;
import xpu.edu.blog.service.UserService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/show")
public class ShowBlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryUserService categoryUserService;

    @GetMapping("/{userid}/blog/{blogid}")
    public String showOneBlog(@CookieValue(value = "userId", required = false) String userId,
                              @PathVariable("userid") String userid,
                              @PathVariable("blogid") String blogid,
                              Map<String, Object> map){

        map.put("userId", userId);

        UserInfo user = userService.getUserById(userid);
        String userName = user.getUserName();
        Integer userPages = user.getUserPages();
        Integer userFans = user.getUserFans();
        Integer userRead = user.getUserRead();

        List<CategoryUser> allCategoryUser = categoryUserService.getAllCategoryUser();
        map.put("allCategoryUser", allCategoryUser);
        BlogInfo blogInfo = blogService.getById(blogid);
        map.put("blog_info", blogInfo);
        map.put("user_data", user);
        return "show/show_one_blog";
    }
}
