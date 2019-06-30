package xpu.edu.blog.entity.es;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@Document(indexName="blog", type="blog")
public class EsBlog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 博客Id
     */
    @Id
    private String id;

    /**
     * 博客标题
     */
    private String title;

    /**
     * 博客摘要
     */
    private String summary;

    /**
     * 博客正文
     */
    private String content;
}