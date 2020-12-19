package com.zj.blog.service.impl;

import com.zj.blog.dao.TagDaoMapper;
import com.zj.blog.pojo.BlogTags;
import com.zj.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TypeServiceImpl
 * @Description TODO
 * @Author 张杰
 * @Time 2020/11/27/15:55
 * @Version 1.0
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDaoMapper TagDaoMapper;

    @Transactional
    @Override
    public int saveTag(BlogTags tags) {
        return this.TagDaoMapper.insertTag(tags);
    }

    @Transactional
    @Override
    public BlogTags getTag(Long id) {
        return this.TagDaoMapper.selectTag(id);
    }

    @Transactional
    @Override
    public List<BlogTags> listTag() {
        return TagDaoMapper.selectAllTags();
    }

    @Transactional
    @Override
    public List<BlogTags> listAllTags() {
        return TagDaoMapper.selectAll();
    }

    @Transactional
    @Override
    public List<BlogTags> listTagsByIds(String ids) {
        List<BlogTags> tags = new ArrayList<>();
        List<Long> longs = convertToList(ids);
        for (Long aLong : longs) {
            tags.add(TagDaoMapper.selectTagsByIds(aLong));
        }
        return tags;
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (String s : idarray) {
                list.add(Long.valueOf(s));
            }
        }
        return list;
    }

    @Transactional
    @Override
    public int updateTag(BlogTags tags) {
        return TagDaoMapper.updateTags(tags);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        TagDaoMapper.deleteTags(id);
    }

    @Transactional
    @Override
    public BlogTags getTagByName(String tagName) {
        return TagDaoMapper.selectTagByName(tagName);
    }

    @Override
    public List<BlogTags> listAllTagsAndBlog() {
        return TagDaoMapper.selectAllTagsAndBlog();
    }

}
