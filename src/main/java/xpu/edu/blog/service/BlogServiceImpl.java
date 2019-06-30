package xpu.edu.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xpu.edu.blog.entity.es.EsBlog;
import xpu.edu.blog.repository.es.EsBlogRepository;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private EsBlogRepository repository;


    @Override
    public Page<EsBlog> findBlog(String title, String summary, String content, Pageable pageable) {
        return repository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(title, summary, content, pageable);
    }
}
