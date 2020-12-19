package com.zj.blog.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BlogTypes
 * @Description TODO
 * @Author 张杰
 * @Time 2020/11/16/16:49
 * @Version 1.0
 */
public class BlogTypes {
    private Long id;
    private String typeName;

    // 实体间关系
    private List<Blog> blogs = new ArrayList<>();   // 一个分类对应多篇博客

    @Override
    public String toString() {
        return "BlogTypes{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public BlogTypes() {
    }
}
