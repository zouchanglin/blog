package xpu.edu.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xpu.edu.blog.entity.BlogInfo;
import xpu.edu.blog.entity.es.EsBlog;
import xpu.edu.blog.repository.BlogInfoRepository;
import xpu.edu.blog.repository.es.EsBlogRepository;
import xpu.edu.blog.service.BlogService;
import xpu.edu.blog.service.CategoryInfoService;
import xpu.edu.blog.service.CategoryUserService;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogInfoRepository blogInfoRepository;

    @Autowired
    private EsBlogRepository esBlogRepository;

    @Autowired
    private CategoryInfoService categoryInfoService;

    @Autowired
    private CategoryUserService categoryUserService;


    @Override
    public Page<EsBlog> findBlog(String title, String summary, String content, Pageable pageable) {
        return esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(title, summary, content, pageable);
    }

    @Override
    public BlogInfo addBlog(BlogInfo blogInfo) {
        if(blogInfo.getBlogCategory() != null)
            categoryInfoService.addOneCategoryNum(blogInfo.getBlogCategory());

        if(blogInfo.getBlogUserCategory() != null)
            categoryUserService.addOneCategoryNum(blogInfo.getBlogUserCategory(), blogInfo.getAuthorId());
        return blogInfoRepository.save(blogInfo);
    }

    @Override
    public BlogInfo getById(String blogId) {
        return blogInfoRepository.findById(blogId).orElse(null);
    }

    @Override
    public List<BlogInfo> getAllByUserId(String userId) {
        return blogInfoRepository.findAllByAuthorId(userId);
    }

    @Override
    public Page<BlogInfo> getAllByUserAndStatus(String userId, Integer auditStatus, Pageable pageable) {
        return blogInfoRepository.findAllByAuthorIdAndBlogAudit(userId, auditStatus, pageable);
    }

    @Override
    public void deleteBlog(String blogId) {
        blogInfoRepository.deleteById(blogId);
    }
}
