package xpu.edu.blog.service.impl;

import org.springframework.stereotype.Service;
import xpu.edu.blog.entity.CommentInfo;
import xpu.edu.blog.repository.CommentInfoRepository;
import xpu.edu.blog.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentInfoRepository repository;

    public CommentServiceImpl(CommentInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addOneComment(CommentInfo commentInfo) {
        repository.save(commentInfo);
    }

    @Override
    public List<CommentInfo> getCommentsByBlog(String blogId) {
        return repository.findAllByBlogId(blogId);
    }

    @Override
    public List<CommentInfo> getCommentsByReplay(Integer replayId) {
        return repository.findAllByReplyId(replayId);
    }

    @Override
    public List<CommentInfo> getMainCommentsByBlog(String blogId) {
        return repository.findAllByBlogIdAndReplyId(blogId, 0);
    }

    @Override
    public CommentInfo getById(Integer commentId) {
        return repository.findById(commentId).orElse(null);
    }

    @Override
    public Integer getCommentNumByBlog(String blogId) {
        return repository.findAllByBlogId(blogId).size();
    }
}
