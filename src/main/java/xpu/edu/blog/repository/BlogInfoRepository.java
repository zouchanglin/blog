package xpu.edu.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xpu.edu.blog.entity.BlogInfo;

public interface BlogInfoRepository extends JpaRepository<BlogInfo, String> {
}
