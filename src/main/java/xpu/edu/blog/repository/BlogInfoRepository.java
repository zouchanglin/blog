package xpu.edu.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import xpu.edu.blog.entity.BlogInfo;

public interface BlogInfoRepository extends JpaRepository<BlogInfo, String> {
    Page<BlogInfo> findAllByBlogAudit(Integer auditStatus, Pageable pageable);

    Page<BlogInfo> findAllByBlogCategoryAndBlogAuditAndBlogIdNot(Integer category, Integer audit, String blogId, Pageable pageable);

    Page<BlogInfo> findAllByBlogAuditAndBlogIdIsNot(Integer blogAudit, String blogId, Pageable pageable);
}
