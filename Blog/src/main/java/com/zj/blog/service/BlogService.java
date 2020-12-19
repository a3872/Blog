package com.zj.blog.service;

import com.zj.blog.pojo.Blog;
import com.zj.blog.pojo.anotherPojo.DetailBlog;
import com.zj.blog.pojo.anotherPojo.FirstPageBlog;

import java.util.List;

/**
 * @ClassName BlogService
 * @Description TODO
 * @Author 张杰
 * @Time 2020/12/1/15:39
 * @Version 1.0
 */
public interface BlogService {

    Blog getBlogById(Long id);

    List<Blog> listBlog();

    List<Blog> SearchBlog(Blog blog);

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    void deleteBlog(Long id);

    // 前端展示
    List<FirstPageBlog> listIndexBlog();

    List<Blog> listRecommendBlog();

    List<FirstPageBlog> listSearchBlog(String query, Long typeId);

    DetailBlog getDetailBlog(Long id);

    // 获取分类页面博客
    List<FirstPageBlog> listBlogByTypeId(Long typeId);
    // 获取标签页面博客
    List<FirstPageBlog> listBlogByTagId(Long tagId);
}
