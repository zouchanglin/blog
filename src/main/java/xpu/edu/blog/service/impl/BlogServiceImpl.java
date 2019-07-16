package xpu.edu.blog.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xpu.edu.blog.entity.BlogInfo;
import xpu.edu.blog.entity.search.EsBlog;
import xpu.edu.blog.enums.BlogAuditEnum;
import xpu.edu.blog.enums.ResultEnum;
import xpu.edu.blog.exception.BlogException;
import xpu.edu.blog.repository.BlogInfoRepository;
import xpu.edu.blog.repository.search.EsBlogRepository;
import xpu.edu.blog.service.BlogService;
import xpu.edu.blog.service.CategoryDetailService;
import xpu.edu.blog.service.CategoryInfoService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogInfoRepository blogInfoRepository;

    @Autowired
    private EsBlogRepository esBlogRepository;

    @Autowired
    private CategoryInfoService categoryInfoService;

    @Autowired
    private CategoryDetailService categoryDetailService;


    @Override
    public Page<EsBlog> findBlog(String title, String summary, String content, Pageable pageable) {
        return esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(title, summary, content, pageable);
    }

    @Override
    public BlogInfo addBlog(BlogInfo blogInfo) {
        if(blogInfo.getBlogCategory() != null){

        }
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

    @Override
    public void blogAddLike(String blogId) {
        Optional<BlogInfo> findResult = blogInfoRepository.findById(blogId);
        if(findResult.isPresent()){
            BlogInfo blogInfo = findResult.get();
            blogInfo.setBlogLikes(blogInfo.getBlogLikes() + 1);
            blogInfoRepository.save(blogInfo);
        }else{
            throw new BlogException(ResultEnum.PARAM_ERROR);
        }
    }

    @Override
    public void blogAddComments(String blogId) {
        Optional<BlogInfo> findResult = blogInfoRepository.findById(blogId);
        if(findResult.isPresent()){
            BlogInfo blogInfo = findResult.get();
            blogInfo.setBlogComments(blogInfo.getBlogComments() + 1);
            blogInfoRepository.save(blogInfo);
        }else{
            throw new BlogException(ResultEnum.PARAM_ERROR);
        }
    }

    @Override
    public void blogAddReading(String blogId) {
        Optional<BlogInfo> findResult = blogInfoRepository.findById(blogId);
        if(findResult.isPresent()){
            BlogInfo blogInfo = findResult.get();
            blogInfo.setBlogReading(blogInfo.getBlogReading() + 1);
            blogInfoRepository.save(blogInfo);
        }else{
            throw new BlogException(ResultEnum.PARAM_ERROR);
        }
    }

    @Override
    public List<BlogInfo> findSomeBlogByThis(BlogInfo blogInfo) {
        Sort sort = new Sort(Sort.Direction.DESC, "blogReading");
        Pageable pageable = PageRequest.of(0, 8, sort);
        Page<BlogInfo> reading = blogInfoRepository.findAllByBlogCategoryAndBlogAuditAndBlogIdNot(blogInfo.getBlogCategory(),
                BlogAuditEnum.RELEASE.getCode(),blogInfo.getBlogId(), pageable);
        return reading.getContent();
    }

    @Override
    public List<BlogInfo> findSomeBlogLatest(BlogInfo blogInfo) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(0, 8, sort);
        Page<BlogInfo> createTime = blogInfoRepository.findAllByBlogAuditAndBlogIdIsNot(BlogAuditEnum.RELEASE.getCode(),blogInfo.getBlogId(), pageable);
        return createTime.getContent();
    }

    @Override
    public List<BlogInfo> findAllBlog() {
        return blogInfoRepository.findAll();
    }
}