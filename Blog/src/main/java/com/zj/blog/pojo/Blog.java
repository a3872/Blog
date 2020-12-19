package com.zj.blog.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Blog
 * @Description 博客实体类
 * @Author 张杰
 * @Time 2020/11/16/16:19
 * @Version 1.0
 */
public class Blog {

    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String description;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentable;
    private boolean published;
    private boolean recommend;
    private Date createTime;
    private Date updateTime;
    private String tagIds;
    private Long typeId;
    private Long userId;
    private Integer commentCount;
    /** 实体间关系 */
    private BlogTypes type; // 一篇博客对应一个分类
    private List<BlogTags> tags = new ArrayList<>();    // 一篇博客对应多个标签
    private BlogUser user;  // 一篇博客对应一个用户
    private List<BlogComment> comments = new ArrayList<>(); // 一篇博客对应多条评论

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentable() {
        return commentable;
    }

    public void setCommentable(boolean commentable) {
        this.commentable = commentable;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BlogTypes getType() {
        return type;
    }

    public void setType(BlogTypes type) {
        this.type = type;
    }

    public List<BlogTags> getTags() {
        return tags;
    }

    public void setTags(List<BlogTags> tags) {
        this.tags = tags;
    }

    public BlogUser getUser() {
        return user;
    }

    public void setUser(BlogUser user) {
        this.user = user;
    }

    public List<BlogComment> getComments() {
        return comments;
    }

    public void setComments(List<BlogComment> comments) {
        this.comments = comments;
    }

    public Blog() {
    }

    public void init() {
        this.tagIds = tagsToIds(this.getTags());
    }

    private String tagsToIds(List<BlogTags> tags) {
        if (!tags.isEmpty()) {
            StringBuilder ids = new StringBuilder();
            boolean flag = false;
            for (BlogTags tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }
}
