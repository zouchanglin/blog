package xpu.edu.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import xpu.edu.blog.entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, String> {

}
