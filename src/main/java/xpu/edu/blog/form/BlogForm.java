package xpu.edu.blog.form;

import lombok.Data;

/**
 * 前台提交的博客表单
 */
@Data
public class BlogForm {
    /**
     * 文章Id
     */
    private String blogId;
    /**
     * 文章标题
     */
    private String blogTitle;

    /*
     * 文章摘要
     */
    private String blogSummary;

    /**
     * 文章正文
     */
    private String blogContent;

    /**
     * 博客整体分类
     */
    private Integer blogCategory;

    /**
     * 原创、翻译、转载
     */
    private Integer blogOriginal;
}
