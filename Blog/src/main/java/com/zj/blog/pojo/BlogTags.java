package com.zj.blog.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BlogTags
 * @Description TODO
 * @Author 张杰
 * @Time 2020/11/16/16:49
 * @Version 1.0
 */
public class BlogTags {
    private Long id;
    private String tagName;

    // 实体间关系
    List<Blog> blogs = new ArrayList<>();   // 一个标签对应多篇博客


    public BlogTags() {
    }

    @Override
    public String toString() {
        return "BlogTags{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

}
