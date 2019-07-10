package xpu.edu.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import xpu.edu.blog.entity.BlogInfo;

import java.util.List;

public interface BlogInfoRepository extends JpaRepository<BlogInfo, String> {
    List<BlogInfo> findAllByAuthorId(String authorId);

    Page<BlogInfo> findAllByAuthorIdAndBlogAudit(String authorId, Integer auditStatus, Pageable pageable);
}
