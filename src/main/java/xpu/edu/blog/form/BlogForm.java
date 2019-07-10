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
    private String blogid;
    /**
     * 文章标题
     */
    private String title;

    /*
     * 文章摘要
     */
    private String summary;

    /**
     * 文章正文
     */
    private String content;

    /**
     * 博客整体分类
     */
    private Integer category;

    /**
     * 用户的自定义分类
     */
    private Integer user_category;

    /**
     * 原创、翻译、转载
     */
    private Integer original;
}
