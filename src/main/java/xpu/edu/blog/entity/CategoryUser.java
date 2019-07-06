package xpu.edu.blog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 用户博客自定义分类
 */
@Entity
@Data
public class CategoryUser {
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

    /**
     * 分类是谁的
     */
    private String userId;
}
