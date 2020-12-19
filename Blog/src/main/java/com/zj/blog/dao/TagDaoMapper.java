package com.zj.blog.dao;

import com.zj.blog.pojo.BlogTags;
import com.zj.blog.pojo.BlogTypes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName TypeDap
 * @Description TODO
 * @Author 张杰
 * @Time 2020/11/27/15:56
 * @Version 1.0
 */
@Repository
@Mapper
public interface TagDaoMapper {
    int insertTag(BlogTags blogTags);

    BlogTags selectTag(Long id);

    BlogTags selectTagByName(String tagName);

    List<BlogTags> selectAllTags();

    List<BlogTags> selectAll();

    BlogTags selectTagsByIds(Long id);

    int updateTags(BlogTags blogTags);

    void deleteTags(Long id);

    List<BlogTags> selectAllTagsAndBlog();
}
