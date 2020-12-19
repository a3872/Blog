package com.zj.blog.service;

import com.zj.blog.pojo.BlogTypes;

import java.util.List;

/**
 * @ClassName TypeService
 * @Description TODO
 * @Author 张杰
 * @Time 2020/11/27/15:42
 * @Version 1.0
 */
public interface TypeService {
    int saveType(BlogTypes types);
    BlogTypes getType(Long id);
    List<BlogTypes> listType();
    int updateType(BlogTypes types);
    void deleteType(Long id);
    BlogTypes getTypeByName(String typeName);
    List<BlogTypes> listAllTypeAndBlog();
}