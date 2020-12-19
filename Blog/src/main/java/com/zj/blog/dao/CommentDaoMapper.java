package com.zj.blog.dao;

import com.zj.blog.pojo.BlogComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName CommentDaoMapper
 * @Description TODO
 * @Author 张杰
 * @Time 2020/12/11/22:23
 * @Version 1.0
 */
@Repository
@Mapper
public interface CommentDaoMapper {
    // 根据创建时间倒序来排
    List<BlogComment> selectByBlogIdParentIdNull(@Param("blogId") Long blogId, @Param("blogParentId") Long blogParentId);

    //查询一级回复
    List<BlogComment> selectByBlogIdParentIdNotNull(@Param("blogId") Long blogId, @Param("id") Long id);

    //查询二级回复
    List<BlogComment> selectByBlogIdAndReplayId(@Param("blogId") Long blogId,@Param("childId") Long childId);

    //添加一个评论
    int insertComment(BlogComment comment);

    //删除评论
    void deleteComment(Long id);
}
