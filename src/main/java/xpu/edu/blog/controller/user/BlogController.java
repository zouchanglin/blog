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
import xpu.edu.blog.enums.BlogAuditEnum;
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
    public String myBlogList(@CookieValue(value = "userId", required = false) String userId,
                             Map<String, Object> map,@RequestParam(value = "pageIndex",defaultValue = "0") Integer pageIndex){
        if(userId == null) return "redirect:/login";

        Page<BlogInfo> myBlogList = blogService.getAllByUserAndStatus(userId, BlogAuditEnum.RELEASE.getCode(), PageRequest.of(pageIndex, 10));
        Page<BlogInfo> myBlogList2 = blogService.getAllByUserAndStatus(userId, BlogAuditEnum.DRAFT.getCode(), PageRequest.of(pageIndex, 10));
        Page<BlogInfo> myBlogList3 = blogService.getAllByUserAndStatus(userId, BlogAuditEnum.RECYCLE.getCode(), PageRequest.of(pageIndex, 10));

        log.info("myBlogList={}", myBlogList.getContent());
        log.info("myBlogList2={}", myBlogList2.getContent());
        log.info("myBlogList3={}", myBlogList3.getContent());

        map.put("myBlogList", myBlogList.getContent());
        map.put("myBlogListPage", myBlogList.getTotalPages() >= 0 ? 1:myBlogList.getTotalPages());

        map.put("myBlogList2", myBlogList2.getContent());
        map.put("myBlogListPage2", myBlogList2.getTotalPages()>= 0 ? 1:myBlogList2.getTotalPages());

        map.put("myBlogList3", myBlogList3.getContent());
        map.put("myBlogListPage3", myBlogList3.getTotalPages()>= 0 ? 1:myBlogList3.getTotalPages());

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

    /**
     * 发布/修改博客
     */
    @ResponseBody
    @RequestMapping("/release")
    public String release(@CookieValue(value = "userId", required = false) String userId, BlogForm blogForm){
        log.info("【BlogController】release = {}", blogForm);
        //if(userId == null) return fileUpLoadConfig.getBlogUrl() + "/404";
        if(userId == null) return "redirect:/login";

        BlogInfo blogInfo = Form2BlogInfo.blogForm2BlogInfo(blogForm);
        blogInfo.setAuthorId(userId);
        if(blogInfo.getBlogId() == null || "".equals(blogInfo.getBlogId()))
            blogInfo.setBlogId(KeyUtil.genUniqueKey());
        //设置为发布版本
        blogInfo.setBlogAudit(BlogAuditEnum.RELEASE.getCode());
        BlogInfo saveBlog = blogService.addBlog(blogInfo);
        return fileUpLoadConfig.getBlogUrl() + "/blog/success?blogId=" + saveBlog.getBlogId();
    }

    /**
     * 发布成功
     */
    @GetMapping("/success")
    public String showSuccess(String blogId, Map<String, Object> map){
        BlogInfo blogInfo = blogService.getById(blogId);
        map.put("blogInfo", blogInfo);
        return "user/release_success";
    }

    /**
     * 保存为草稿
     */
    @ResponseBody
    @RequestMapping("/save")
    public String save(@CookieValue(value = "userId",required = false) String userId, BlogForm blogForm){
        if(userId == null) return "redirect:/login";
        log.info("【BlogController】save = {}", blogForm);
        BlogInfo blogInfo = Form2BlogInfo.blogForm2BlogInfo(blogForm);
        blogInfo.setAuthorId(userId);
        if(blogInfo.getBlogId() == null || "".equals(blogInfo.getBlogId()))
            blogInfo.setBlogId(KeyUtil.genUniqueKey());
        //设置为草稿版本
        blogInfo.setBlogAudit(BlogAuditEnum.DRAFT.getCode());
        blogService.addBlog(blogInfo);
        return "保存成功，请在草稿箱中查看";
    }

    /**
     * 发布的改为草稿
     */
    @RequestMapping("/releaseToSave")
    public String releaseToSave(@CookieValue(value = "userId",required = false) String userId,
                                @RequestParam("blogId") String blogId){
        if(userId == null) return "redirect:/login";
        log.info("【BlogController】blogId = {}", blogId);

        BlogInfo blogInfo = blogService.getById(blogId);
        //设置为草稿版本
        blogInfo.setBlogAudit(BlogAuditEnum.DRAFT.getCode());
        blogService.addBlog(blogInfo);
        return "redirect:/user/user_blog";
    }




    /**
     * 新建博客
     */
    @GetMapping("/edit")
    public String edit(@CookieValue(value = "userId",required = false) String userId, Map<String, Object> map){
        if(userId == null) return "redirect:/login";
        List<CategoryInfo> allCategory = categoryService.getAllCategory();
        //这里不是查找所有分类，而是查找该用户的所有分类
        List<CategoryUser> allCategoryUser = categoryUserService.getAllCategoryByUserId(userId);
        log.info("allCategoryUser={}", allCategoryUser);
        map.put("allCategory", allCategory);
        map.put("allCategoryUser", allCategoryUser);
        map.put("blogInfo", new BlogInfo());
        return "user/edit_blog";
    }

    /**
     * 修改博客
     */
    @GetMapping("/edit_update")
    public String edit_update(@CookieValue(value = "userId",required = false) String userId, Map<String, Object> map,
                       @RequestParam("blogId") String blogId){
        if(userId == null) return "redirect:/login";
        List<CategoryInfo> allCategory = categoryService.getAllCategory();
        List<CategoryUser> allCategoryUser = categoryUserService.getAllCategoryByUserId(userId);
        log.info("allCategoryUser={}", allCategoryUser);
        map.put("allCategory", allCategory);
        map.put("allCategoryUser", allCategoryUser);

        BlogInfo blogInfo = blogService.getById(blogId);
        map.put("blogInfo", blogInfo);
        return "user/edit_blog";
    }

    /**
     * 删除博客
     */
    @GetMapping("/delete")
    public String delete_blog(@CookieValue(value = "userId",required = false) String userId,
                       @RequestParam("blogId") String blogId){
        if(userId == null) return "redirect:/login";
        BlogInfo blogInfo = blogService.getById(blogId);
        blogInfo.setBlogAudit(BlogAuditEnum.RECYCLE.getCode());
        blogService.addBlog(blogInfo);
        //blogService.deleteBlog(blogId);
        return "redirect:/user/user_blog";
    }

    /**
     * 删除博客
     */
    @GetMapping("/rel_delete")
    public String rel_delete_blog(@CookieValue(value = "userId",required = false) String userId,
                              @RequestParam("blogId") String blogId){
        if(userId == null) return "redirect:/login";
        blogService.deleteBlog(blogId);
        return "redirect:/user/user_blog";
    }
}
