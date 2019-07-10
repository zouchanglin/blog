package xpu.edu.blog.convert;

import xpu.edu.blog.entity.BlogInfo;
import xpu.edu.blog.form.BlogForm;

public class Form2BlogInfo {
    public static BlogInfo blogForm2BlogInfo(BlogForm blogForm){
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setBlogId(blogForm.getBlogid());
        blogInfo.setBlogTitle(blogForm.getTitle());
        blogInfo.setBlogSummary(blogForm.getSummary());
        blogInfo.setBlogContent(blogForm.getContent());
        blogInfo.setBlogOriginal(blogForm.getOriginal());
        blogInfo.setBlogCategory(blogForm.getCategory());
        blogInfo.setBlogUserCategory(blogForm.getUser_category());
        return blogInfo;
    }
}
