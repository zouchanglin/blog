package xpu.edu.blog.controller.show;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xpu.edu.blog.entity.BlogInfo;
import xpu.edu.blog.entity.CommentInfo;
import xpu.edu.blog.service.BlogService;
import xpu.edu.blog.service.CommentService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/show")
@Slf4j
public class ShowBlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;


    @GetMapping("/blog/{blogid}")
    public String showOneBlog(@PathVariable("blogid") String blogid,
                              Map<String, Object> map){
        BlogInfo blogInfo = blogService.getById(blogid);
        if(blogInfo == null) return "error/404";
        blogService.blogAddReading(blogid);

        map.put("blog_info", blogInfo);
        List<CommentInfo> comment_info = commentService.getCommentsByBlog(blogid);
        map.put("comment_info", comment_info);
        return "show/show_one_blog";
    }

    @ResponseBody
    @GetMapping("/like")
    public String likeOneBlog(@RequestParam("blogId") String blogId){
        blogService.blogAddLike(blogId);
        return String.valueOf(blogService.getById(blogId).getBlogLikes());
    }

    @ResponseBody
    @PostMapping("/comment")
    public String comment(CommentInfo commentInfo){
        log.info("commentInfo={}", commentInfo);
        if(commentInfo.getReplyId() == null){
            commentInfo.setReplyId(0);
        }else{
            String replyName = commentService.getById(commentInfo.getReplyId()).getCommentName();
            commentInfo.setCommentContent("@" + replyName + ": " + commentInfo.getCommentContent());
            blogService.blogAddComments(commentInfo.getBlogId());
        }
        commentService.addOneComment(commentInfo);
        return "success";
    }
}