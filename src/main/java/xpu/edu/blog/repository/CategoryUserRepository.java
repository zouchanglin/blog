package xpu.edu.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xpu.edu.blog.entity.CategoryUser;

import java.util.List;

public interface CategoryUserRepository extends JpaRepository<CategoryUser, Integer> {
    List<CategoryUser> findAllByUserId(String userId);
}
