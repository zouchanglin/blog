package xpu.edu.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xpu.edu.blog.entity.BlogInfo;

import java.util.List;

public interface BlogInfoRepository extends JpaRepository<BlogInfo, String> {
    List<BlogInfo> findAllByAuthorId(String authorId);
}
