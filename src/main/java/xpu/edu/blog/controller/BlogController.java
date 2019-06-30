package xpu.edu.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xpu.edu.blog.entity.es.EsBlog;
import xpu.edu.blog.service.BlogService;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/list")
    public List<EsBlog> list(@RequestParam("title") String title,
                             @RequestParam("summary") String summary,
                             @RequestParam("context") String context,
                             @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        Page<EsBlog> blog = blogService.findBlog(title, summary, context, PageRequest.of(pageIndex, pageSize));
        return blog.getContent();
    }
}
