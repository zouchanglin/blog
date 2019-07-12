package xpu.edu.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xpu.edu.blog.entity.BlogInfo;
import xpu.edu.blog.entity.search.EsBlog;
import xpu.edu.blog.repository.BlogInfoRepository;
import xpu.edu.blog.repository.search.EsBlogRepository;
import xpu.edu.blog.service.BlogService;
import xpu.edu.blog.service.CategoryInfoService;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogInfoRepository blogInfoRepository;

    @Autowired
    private EsBlogRepository esBlogRepository;

    @Autowired
    private CategoryInfoService categoryInfoService;


    @Override
    public Page<EsBlog> findBlog(String title, String summary, String content, Pageable pageable) {
        return esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(title, summary, content, pageable);
    }

    @Override
    public BlogInfo addBlog(BlogInfo blogInfo) {
        if(blogInfo.getBlogCategory() != null)
            categoryInfoService.addOneCategoryNum(blogInfo.getBlogCategory());
        return blogInfoRepository.save(blogInfo);
    }

    @Override
    public BlogInfo getById(String blogId) {
        return blogInfoRepository.findById(blogId).orElse(null);
    }

    @Override
    public Page<BlogInfo> getAllByStatus(Integer auditStatus, Pageable pageable) {
         return blogInfoRepository.findAllByBlogAudit(auditStatus, pageable);
    }

    @Override
    public void deleteBlog(String blogId) {
        blogInfoRepository.deleteById(blogId);
    }
}
