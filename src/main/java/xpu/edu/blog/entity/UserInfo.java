package xpu.edu.blog.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户实体类
 * @author Tim
 * @since 2019.6.28
 * @version 1.0
 */
@Data
@Entity
@DynamicUpdate
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 文章数
     */
    private Integer userPages;

    /**
     * 评论数
     */
    private Integer userComment;

    private Date createTime;

    private Date updateTime;
}