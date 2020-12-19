package com.zj.blog.pojo;

import java.util.Date;

/**
 * @ClassName BlogFriendLink
 * @Description TODO
 * @Author 张杰
 * @Time 2020/11/16/17:12
 * @Version 1.0
 */
public class BlogFriendLink{

    private Long id;
    private String blogName;
    private String blogAddress;
    private String pictureAddress;
    private Date createTime;

    @Override
    public String toString() {
        return "BlogFriendLink{" +
                "id=" + id +
                ", blogName='" + blogName + '\'' +
                ", blogAddress='" + blogAddress + '\'' +
                ", pictureAddress='" + pictureAddress + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getBlogAddress() {
        return blogAddress;
    }

    public void setBlogAddress(String blogAddress) {
        this.blogAddress = blogAddress;
    }

    public String getPictureAddress() {
        return pictureAddress;
    }

    public void setPictureAddress(String pictureAddress) {
        this.pictureAddress = pictureAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BlogFriendLink() {
    }
}
