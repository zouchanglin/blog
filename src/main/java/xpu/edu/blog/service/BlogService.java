package xpu.edu.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xpu.edu.blog.entity.es.EsBlog;

public interface BlogService {

    Page<EsBlog> findBlog(String title, String summary, String content, Pageable pageable);

}
