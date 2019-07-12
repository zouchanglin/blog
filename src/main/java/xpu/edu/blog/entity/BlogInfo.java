package xpu.edu.blog.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class BlogInfo implements Serializable {
    /**
     * 博客主键
     */
    @Id
    private String blogId;

    /**
     * 标题
     */
    private String blogTitle;

    /**
     * 摘要
     */
    private String blogSummary;

    /**
     * 内容（markdown原始内容）
     */
    private String blogContent;

    /**
     * 博客分类（总体分类）
     */
    private Integer blogCategory;

    /**
     * 访问量、阅读量
     */
    private Integer blogReading = 0;

    /**
     * 评论量
     */
    private Integer blogComments = 0;

    /**
     * 点赞量
     */
    private Integer blogLikes = 0;

    /**
     * 类型(原创、转载、翻译)
     */
    private Integer blogOriginal = 0;

    /**
     * 发布情况（草稿、已经发布、已删除）
     */
    private Integer blogAudit = 0;

    /**
     * 博客创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    /**
     * 博客最后修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;
}