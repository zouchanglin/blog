package xpu.edu.blog.entity;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
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
     * 作者Id
     */
    private String authorId;

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
     * 内容（转为html后的内容）
     */
    private String blogHtmlContent;

    /**
     * 博客分类（总体分类）
     */
    private Integer blogCategory;

    /**
     * 博客分类（用户自己的分类）
     */
    private Integer blogUserCategory;

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
     * 博客创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    /**
     * 博客最后修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;

    public void setBlogContent(String blogContent){
        this.blogContent = blogContent;

        Parser parser = Parser.builder().build();
        Node document = parser.parse(blogContent);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        this.blogHtmlContent = renderer.render(document);
    }

    /**
     * 类型(原创、转载、翻译)
     */
    private Integer blogOriginal = 0;

    /**
     * 审核情况（未审核、审核通过、未通过）
     */
    private Integer blogAudit = 0;
}