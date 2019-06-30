package xpu.edu.blog.repository.es;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import xpu.edu.blog.entity.es.EsBlog;

public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, String> {
    /**
     * 分页去重查询博客
     * @param title 标题
     * @param summary 摘要
     * @param content 内容
     */
    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(String title, String summary, String content, Pageable pageable);
}
