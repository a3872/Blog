package com.zj.blog.dao;

import com.zj.blog.pojo.Blog;
import com.zj.blog.pojo.anotherPojo.BlogAndTags;
import com.zj.blog.pojo.anotherPojo.DetailBlog;
import com.zj.blog.pojo.anotherPojo.FirstPageBlog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName BlogDaoMapper
 * @Description TODO
 * @Author 张杰
 * @Time 2020/12/1/15:44
 * @Version 1.0
 */
@Repository
@Mapper
public interface BlogDaoMapper {

    Blog selectBlogById(Long id);
    List<Blog> selectAllBlog();
    List<Blog> listSelectBlogByTitleOrTypeOrRecommend(Blog blog);
    int insertBlog(Blog blog);
    int insertBlogAndTag(BlogAndTags blogAndTags);
    int updateBlogAndTag(BlogAndTags blogAndTags);
    int deleteBlogAndTag(BlogAndTags blogAndTags);
    int updateBlog(Blog blog);
    void deleteBlog(Long id);

    List<FirstPageBlog> selectIndexBlog();
    List<Blog> selectRecommendBlog();
    List<FirstPageBlog> selectSearchBlog(String query, Long typeId);
    DetailBlog selectDetailBlog(Long id);

    // 根据博客id查询出评论数量
    int selectCommentCountById(Long id);
    void updateViews(Long id);

    // 根据分类查询博客
    List<FirstPageBlog> selectBlogByTypeId(Long typeId);
    // 根据标签查询博客
    List<FirstPageBlog> selectBlogByTagId(Long tagId);
}
