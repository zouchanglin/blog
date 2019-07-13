package xpu.edu.blog.service;

import xpu.edu.blog.entity.CommentInfo;

import java.util.List;

public interface CommentService {

    /**
     * 增加/回复一条评论
     * @param commentInfo 评论内容
     */
    void addOneComment(CommentInfo commentInfo);


    /**
     * 获取博客的所有评论列表
     * @param blogId 博客Id
     * @return 评论信息列表
     */
    List<CommentInfo> getCommentsByBlog(String blogId);

    /**
     * 获取一条评论的回复评论
     * @param commentId 评论的Id
     * @return 回复评论的列表
     */
    List<CommentInfo> getCommentsByReplay(Integer commentId);

    /**
     * 获取博客的主评论列表
     * @param blogId 博客Id
     * @return 评论信息列表
     */
    List<CommentInfo> getMainCommentsByBlog(String blogId);


    /**
     * 根据评论Id获取评论信息
     * @param commentId 评论Id
     * @return 评论信息
     */
    CommentInfo getById(Integer commentId);


    /**
     * 获取一篇博客的评论量
     * @param blogId 博客Id
     * @return 此博客的评论量
     */
    Integer getCommentNumByBlog(String blogId);
}
