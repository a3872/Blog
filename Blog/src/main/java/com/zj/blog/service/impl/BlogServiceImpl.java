package com.zj.blog.service.impl;

import com.zj.blog.dao.BlogDaoMapper;
import com.zj.blog.handler.NotFoundException;
import com.zj.blog.pojo.Blog;
import com.zj.blog.pojo.BlogTags;
import com.zj.blog.pojo.anotherPojo.BlogAndTags;
import com.zj.blog.pojo.anotherPojo.DetailBlog;
import com.zj.blog.pojo.anotherPojo.FirstPageBlog;
import com.zj.blog.service.BlogService;
import com.zj.blog.util.MarkDownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName BlogServiceImpl
 * @Description TODO
 * @Author 张杰
 * @Time 2020/12/1/15:43
 * @Version 1.0
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogDaoMapper blogDaoMapper;

    @Transactional
    @Override
    public Blog getBlogById(Long id) {
        return blogDaoMapper.selectBlogById(id);
    }

    @Transactional
    @Override
    public List<Blog> listBlog() {
        return blogDaoMapper.selectAllBlog();
    }

    @Transactional
    @Override
    public List<Blog> SearchBlog(Blog blog) {
        return blogDaoMapper.listSelectBlogByTitleOrTypeOrRecommend(blog);
    }

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setCommentCount(0);
        //将标签的数据存到t_blogs_tag表中
        List<BlogTags> tags = blog.getTags();
        BlogAndTags blogAndTag = null;
        for (BlogTags tag : tags) {
            blogAndTag = new BlogAndTags(tag.getId(),blog.getId());
            blogDaoMapper.insertBlogAndTag(blogAndTag);
        }
        return blogDaoMapper.insertBlog(blog);
    }

    @Transactional
    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        //将标签的数据存到t_blogs_tag表中
        List<BlogTags> tags = blog.getTags();
        BlogAndTags blogAndTag = null;
        for (BlogTags tag : tags) {
            blogAndTag = new BlogAndTags(tag.getId(),blog.getId());
            blogDaoMapper.updateBlogAndTag(blogAndTag);
        }
        return blogDaoMapper.updateBlog(blog);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        // 删除
        BlogAndTags blogAndTag = new BlogAndTags(id);
        blogDaoMapper.deleteBlogAndTag(blogAndTag);
        blogDaoMapper.deleteBlog(id);
    }

    @Transactional
    @Override
    public List<FirstPageBlog> listIndexBlog() {
        return blogDaoMapper.selectIndexBlog();
    }

    @Transactional
    @Override
    public List<Blog> listRecommendBlog() {
        return blogDaoMapper.selectRecommendBlog();
    }

    @Transactional
    @Override
    public List<FirstPageBlog> listSearchBlog(String query, Long typeId) {
        return blogDaoMapper.selectSearchBlog(query, typeId);
    }

    @Transactional
    @Override
    public DetailBlog getDetailBlog(Long id) {
        System.out.println(id);
        DetailBlog detailBlog = blogDaoMapper.selectDetailBlog(id);
        if (detailBlog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = detailBlog.getContent();
        detailBlog.setContent(MarkDownUtils.markdownToHtmlExtensions(content));
        // 文章访问数量自增
        blogDaoMapper.updateViews(id);
        return detailBlog;
    }

    @Override
    public List<FirstPageBlog> listBlogByTypeId(Long typeId) {
        return blogDaoMapper.selectBlogByTypeId(typeId);
    }

    @Override
    public List<FirstPageBlog> listBlogByTagId(Long tagId) {
        return blogDaoMapper.selectBlogByTagId(tagId);
    }
}
