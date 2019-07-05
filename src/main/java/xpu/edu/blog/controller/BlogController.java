package xpu.edu.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xpu.edu.blog.entity.es.EsBlog;
import xpu.edu.blog.service.BlogService;

import java.util.List;

@Controller
@RequestMapping("/blog")
@Slf4j
public class BlogController {
    @Autowired
    private BlogService blogService;

    @ResponseBody
    @GetMapping("/list")
    public List<EsBlog> list(@RequestParam("title") String title,
                             @RequestParam("summary") String summary,
                             @RequestParam("context") String context,
                             @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        Page<EsBlog> blog = blogService.findBlog(title, summary, context, PageRequest.of(pageIndex, pageSize));
        return blog.getContent();
    }

    @RequestMapping("/release")
    public String release(@RequestParam("title") String title,
                       @RequestParam("content") String content){
        log.info("【BlogController】release title = {}, content={}", title, content);
        return "user/center";
    }

    @RequestMapping("/save")
    public String save(@RequestParam("title") String title,
                       @RequestParam("content") String content){
        log.info("【BlogController】save title = {}, content={}", title, content);
        return "user/center";
    }


    @GetMapping("/edit")
    public String edit(){
        return "user/edit_blog";
    }
}
