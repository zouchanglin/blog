package xpu.edu.blog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 博客系统全部分类
 */
@Entity
@Data
public class CategoryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 此分类下的文章数
     */
    private Integer categoryNum = 0;
}
