package xpu.edu.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xpu.edu.blog.entity.CommentInfo;

import java.util.List;

public interface CommentInfoRepository extends JpaRepository<CommentInfo, Integer> {
    List<CommentInfo> findAllByBlogId(String blogId);

    List<CommentInfo> findAllByReplyId(Integer replyId);

    List<CommentInfo> findAllByBlogIdAndReplyId(String blogId, Integer replyId);
}
