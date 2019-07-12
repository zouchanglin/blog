package xpu.edu.blog.controller.show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xpu.edu.blog.entity.BlogInfo;
import xpu.edu.blog.service.BlogService;

import java.util.Map;

@Controller
@RequestMapping("/show")
public class ShowBlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/blog/{blogid}")
    public String showOneBlog(@PathVariable("blogid") String blogid,
                              Map<String, Object> map){
        BlogInfo blogInfo = blogService.getById(blogid);
        map.put("blog_info", blogInfo);
        return "show/show_one_blog";
    }
}
