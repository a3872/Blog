package com.zj.blog.service;

import com.zj.blog.pojo.BlogComment;

import java.util.List;

/**
 * @ClassName CommentService
 * @Description TODO
 * @Author 张杰
 * @Time 2020/12/11/22:20
 * @Version 1.0
 */
public interface CommentService {
    List<BlogComment> listCommentByBlogId(Long blogId);

    int saveComment(BlogComment comment);

    //删除评论
    void deleteComment(BlogComment comment,Long id);
}
