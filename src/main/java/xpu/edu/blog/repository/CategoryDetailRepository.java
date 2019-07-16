package xpu.edu.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xpu.edu.blog.entity.CategoryDetail;

import java.util.List;

public interface CategoryDetailRepository extends JpaRepository<CategoryDetail, Integer> {
    List<CategoryDetail> findAllByDetailMaster(Integer detailMaster);
}
