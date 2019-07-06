package xpu.edu.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xpu.edu.blog.entity.CategoryInfo;

public interface CategoryInfoRepository extends JpaRepository<CategoryInfo, Integer> {
}
