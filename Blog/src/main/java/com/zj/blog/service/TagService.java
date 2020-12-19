package com.zj.blog.service;

import com.zj.blog.pojo.BlogTags;
import com.zj.blog.pojo.BlogTypes;

import java.util.List;

/**
 * @ClassName TypeService
 * @Description TODO
 * @Author 张杰
 * @Time 2020/11/27/15:42
 * @Version 1.0
 */
public interface TagService {
    int saveTag(BlogTags tags);
    BlogTags getTag(Long id);
    List<BlogTags> listTag();
    List<BlogTags> listAllTags();
    List<BlogTags> listTagsByIds(String ids);
    int updateTag(BlogTags tags);
    void deleteTag(Long id);
    BlogTags getTagByName(String tagName);
    List<BlogTags> listAllTagsAndBlog();

}