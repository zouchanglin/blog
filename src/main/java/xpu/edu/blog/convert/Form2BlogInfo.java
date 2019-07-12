package xpu.edu.blog.convert;

import org.springframework.beans.BeanUtils;
import xpu.edu.blog.entity.BlogInfo;
import xpu.edu.blog.form.BlogForm;

public class Form2BlogInfo {
    public static BlogInfo blogForm2BlogInfo(BlogForm blogForm){
        BlogInfo blogInfo = new BlogInfo();
        BeanUtils.copyProperties(blogForm, blogInfo);
        return blogInfo;
    }
}
