package com.zj.blog.pojo.anotherPojo;

/**
 * @ClassName BlogAndTags
 * @Description TODO
 * @Author 张杰
 * @Time 2020/12/14/21:58
 * @Version 1.0
 */
public class BlogAndTags {

    private Long tagId;
    private Long blogId;

    public BlogAndTags(Long tagId, Long blogId) {
        this.tagId = tagId;
        this.blogId = blogId;
    }

    public BlogAndTags(Long blogId) {
        this.blogId = blogId;
    }

    public BlogAndTags() {
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
}
