package xpu.edu.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xpu.edu.blog.entity.BlogInfo;
import xpu.edu.blog.entity.es.EsBlog;

import java.util.List;

public interface BlogService {

    Page<EsBlog> findBlog(String title, String summary, String content, Pageable pageable);

    BlogInfo addBlog(BlogInfo blogInfo);

    BlogInfo getById(String blogId);

    List<BlogInfo> getAllByUserId(String userId);

    Page<BlogInfo> getAllByUserAndStatus(String userId, Integer auditStatus, Pageable pageable);

    void deleteBlog(String blogId);
}
