package com.zj.blog.service.impl;

import com.zj.blog.dao.BlogDaoMapper;
import com.zj.blog.dao.CommentDaoMapper;
import com.zj.blog.dao.MoviesDaoMapper;
import com.zj.blog.pojo.BlogComment;
import com.zj.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CommentServiceImpl
 * @Description TODO
 * @Author 张杰
 * @Time 2020/12/11/22:22
 * @Version 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDaoMapper commentDaoMapper;
    @Autowired
    private BlogDaoMapper blogDaoMapper;
    @Autowired
    MoviesDaoMapper moviesDaoMapper;
    // 存放迭代找出的所有子代的集合
    private List<BlogComment> tempReplys = new ArrayList<>();

    @Transactional
    @Override
    public List<BlogComment> listCommentByBlogId(Long blogId) {
        // 查询出父节点
        List<BlogComment> comments = commentDaoMapper.selectByBlogIdParentIdNull(blogId, Long.parseLong("-1"));
        for(BlogComment comment : comments){
            Long id = comment.getId();
            String parentNickname1 = comment.getNickName();
            List<BlogComment> childComments = commentDaoMapper.selectByBlogIdParentIdNotNull(blogId,id);
            // 查询出子评论
            combineChildren(blogId, childComments, parentNickname1);
            comment.setReplyComment(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return comments;
    }

    // 查找一级子评论
    private void combineChildren(Long blogId, List<BlogComment> childComments, String parentNickname1) {
        // 判断是否有一级子评论
        if(childComments.size() > 0){
            // 循环找出子评论的id
            for(BlogComment childComment : childComments){
                String parentNickname = childComment.getNickName();
                childComment.setParentNickname(parentNickname1);
                tempReplys.add(childComment);
                Long childId = childComment.getId();
                // 查询出子二级评论
                recursively(blogId, childId, parentNickname);
            }
        }
    }

    // 查找二级子评论
    private void recursively(Long blogId, Long childId, String parentNickname1) {
        //  根据子一级评论的id找到子二级评论
        List<BlogComment> replayComments = commentDaoMapper.selectByBlogIdAndReplayId(blogId,childId);
        if(replayComments.size() > 0){
            for(BlogComment replayComment : replayComments){
                String parentNickname = replayComment.getNickName();
                replayComment.setParentNickname(parentNickname1);
                Long replayId = replayComment.getId();
                tempReplys.add(replayComment);
                recursively(blogId,replayId,parentNickname);
            }
        }
    }

    @Transactional
    @Override
    public int saveComment(BlogComment comment) {
        comment.setCreateTime(new Date());
        int comments = commentDaoMapper.insertComment(comment);
        // 文章评论计数
        blogDaoMapper.selectCommentCountById(comment.getBlogId());
        // 文章评论计数
        moviesDaoMapper.selectCommentCountById(comment.getBlogId());
        return comments;
    }

    @Transactional
    @Override
    public void deleteComment(BlogComment comment, Long id) {
        commentDaoMapper.deleteComment(id);
        blogDaoMapper.selectCommentCountById(comment.getBlogId());
        moviesDaoMapper.selectCommentCountById(comment.getBlogId());
    }
}
