package xpu.edu.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xpu.edu.blog.entity.BlogInfo;
import xpu.edu.blog.entity.search.EsBlog;

import java.util.List;

public interface BlogService {

    /**
     * 根据文章标题、摘要、内容进行查找
     * @param title 文章标题
     * @param summary 文章摘要
     * @param content 文章内容
     * @param pageable 分页信息
     * @return 文章信息
     */
    Page<EsBlog> findBlog(String title, String summary, String content, Pageable pageable);

    /**
     * 增加一篇博客
     * @param blogInfo 博客信息
     * @return 保存后的博客信息
     */
    BlogInfo addBlog(BlogInfo blogInfo);

    /**
     * 获取一篇博客信息
     * @param blogId 博客Id
     * @return 博客信息
     */
    BlogInfo getById(String blogId);

    /**
     * 根据博客状态（草稿、已发布、已删除）查找博客
     * @param auditStatus 博客状态
     * @param pageable 分页参数
     * @return 博客分页信息
     */
    Page<BlogInfo> getAllByStatus(Integer auditStatus, Pageable pageable);

    /**
     * 删除一篇博客
     * @param blogId 要删除的博客Id
     */
    void deleteBlog(String blogId);

    /**
     * 博客增加一个点赞量
     * @param blogId 要点赞的博客
     */
    void blogAddLike(String blogId);

    /**
     * 博客增加一个评论数
     * @param blogId 博客Id
     */
    void blogAddComments(String blogId);

    /**
     * 博客增加一个阅读数
     * @param blogId 博客Id
     */
    void blogAddReading(String blogId);

    /**
     * 根据一篇博客查找出同类别的博客
     * @param blogInfo 已知博客信息
     */
    List<BlogInfo> findSomeBlogByThis(BlogInfo blogInfo);

    /**
     * 获取最新博客列表
     * @return 最新博客列表
     */
    List<BlogInfo> findSomeBlogLatest(BlogInfo blogInfo);
}
