package xpu.edu.blog.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xpu.edu.blog.config.FileUpLoadConfig;
import xpu.edu.blog.convert.Form2BlogInfo;
import xpu.edu.blog.entity.BlogInfo;
import xpu.edu.blog.entity.CategoryInfo;
import xpu.edu.blog.entity.CategoryUser;
import xpu.edu.blog.entity.es.EsBlog;
import xpu.edu.blog.form.BlogForm;
import xpu.edu.blog.service.BlogService;
import xpu.edu.blog.service.CategoryInfoService;
import xpu.edu.blog.service.CategoryUserService;
import xpu.edu.blog.utils.KeyUtil;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/blog")
@Slf4j
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryInfoService categoryService;

    @Autowired
    private CategoryUserService categoryUserService;

    @Autowired
    private FileUpLoadConfig fileUpLoadConfig;

    @GetMapping("/my_list")
    public String myBlogList(@CookieValue(value = "userId", required = false) String userId, Map<String, Object> map){
        if(userId == null) return "redirect:/login";
        List<BlogInfo> myBlogList = blogService.getAllByUserId(userId);
        map.put("myBlogList", myBlogList);
        return "user/center/user_blog";
    }

    @ResponseBody
    @GetMapping("/find_list")
    public List<EsBlog> list(@RequestParam("title") String title,
                             @RequestParam("summary") String summary,
                             @RequestParam("context") String context,
                             @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        Page<EsBlog> blog = blogService.findBlog(title, summary, context, PageRequest.of(pageIndex, pageSize));
        return blog.getContent();
    }

    @ResponseBody
    @RequestMapping("/release")
    public String release(@CookieValue(value = "userId", required = false) String userId, BlogForm blogForm){
        log.info("【BlogController】release = {}", blogForm);
        if(userId == null) return fileUpLoadConfig.getBlogUrl() + "/404";

        BlogInfo blogInfo = Form2BlogInfo.blogForm2BlogInfo(blogForm);
        blogInfo.setAuthorId(userId);
        blogInfo.setBlogId(KeyUtil.genUniqueKey());

        BlogInfo saveBlog = blogService.addBlog(blogInfo);
        return fileUpLoadConfig.getBlogUrl() + "/blog/success?blogId=" + saveBlog.getBlogId();
    }

    @GetMapping("/success")
    public String showSuccess(String blogId, Map<String, Object> map){
        BlogInfo blogInfo = blogService.getById(blogId);
        map.put("blogInfo", blogInfo);
        return "user/release_success";
    }

    @RequestMapping("/save")
    public String save(BlogForm blogForm){
        log.info("【BlogController】save = {}", blogForm);
        return "/user/release_success";
    }


    @GetMapping("/edit")
    public String edit(@CookieValue(value = "userId",required = false) String userId, Map<String, Object> map){
        if(userId == null) return "redirect:/login";
        List<CategoryInfo> allCategory = categoryService.getAllCategory();
        //这里不是查找所有分类，而是查找该用户的所有分类
        List<CategoryUser> allCategoryUser = categoryUserService.getAllCategoryByUserId(userId);
        log.info("allCategoryUser={}", allCategoryUser);
        map.put("allCategory", allCategory);
        map.put("allCategoryUser", allCategoryUser);
        return "user/edit_blog";
    }
}
