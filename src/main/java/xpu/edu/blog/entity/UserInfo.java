package xpu.edu.blog.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

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
    private String userId;

    private String userName;

    private String userEmail;
}
