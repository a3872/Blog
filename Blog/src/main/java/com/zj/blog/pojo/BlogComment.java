package com.zj.blog.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName BlogComment
 * @Description TODO
 * @Author 张杰
 * @Time 2020/11/16/16:53
 * @Version 1.0
 */
public class BlogComment {
    private Long id;
    private String nickName;
    private String email;
    private String avatar;
    private String commentContent;
    private Date createTime;

    // 评论回复
    private Long blogId;
    private Long parentCommentId;
    private String parentNickname;

    // 实体间关系
    private Blog blog;  // 一条评论对应一篇博客
    // 内部关联
    private List<BlogComment> replyComment = new ArrayList<>(); // 一个评论对应多条回复
    private BlogComment parentComment;  // 一个回复对应一个评论
    private boolean adminComment;


    @Override
    public String toString() {
        return "BlogComment{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", blogId=" + blogId +
                ", parentCommentId=" + parentCommentId +
                ", parentNickname='" + parentNickname + '\'' +
                ", blog=" + blog +
                ", replyComment=" + replyComment +
                ", parentComment=" + parentComment +
                ", isAdminComment=" + adminComment +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getParentNickname() {
        return parentNickname;
    }

    public void setParentNickname(String parentNickname) {
        this.parentNickname = parentNickname;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public List<BlogComment> getReplyComment() {
        return replyComment;
    }

    public void setReplyComment(List<BlogComment> replyComment) {
        this.replyComment = replyComment;
    }

    public BlogComment getParentComment() {
        return parentComment;
    }

    public void setParentComment(BlogComment parentComment) {
        this.parentComment = parentComment;
    }

    public boolean isAdminComment() {
        return adminComment;
    }

    public void setAdminComment(boolean adminComment) {
        this.adminComment = adminComment;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public BlogComment(Long id, String nickName, String email, String avatar, String commentContent, Date createTime, Long commentCount, Long blogId, Long parentCommentId, String parentNickname, Blog blog, List<BlogComment> replyComment, BlogComment parentComment, boolean isAdminComment) {
        this.id = id;
        this.nickName = nickName;
        this.email = email;
        this.avatar = avatar;
        this.commentContent = commentContent;
        this.createTime = createTime;
        this.blogId = blogId;
        this.parentCommentId = parentCommentId;
        this.parentNickname = parentNickname;
        this.blog = blog;
        this.replyComment = replyComment;
        this.parentComment = parentComment;
        this.adminComment = isAdminComment;
    }

    public BlogComment() {
    }
}
