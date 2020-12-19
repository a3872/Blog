package com.zj.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.blog.pojo.Blog;
import com.zj.blog.pojo.BlogTags;
import com.zj.blog.pojo.BlogTypes;
import com.zj.blog.pojo.anotherPojo.DetailBlog;
import com.zj.blog.pojo.anotherPojo.FirstPageBlog;
import com.zj.blog.service.BlogService;
import com.zj.blog.service.TagService;
import com.zj.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName IndexShowController
 * @Description TODO
 * @Author 张杰
 * @Time 2020/12/6/16:40
 * @Version 1.0
 */
@Controller
public class IndexShowController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/index")
    public String showBlogs(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        // 按照排序字段 倒序 排序
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,5,orderBy);
        List<FirstPageBlog> listIndexBlog = blogService.listIndexBlog();
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<FirstPageBlog>(listIndexBlog);
        List<Blog> listRecommendBlog = blogService.listRecommendBlog();
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("listRecommendBlog",listRecommendBlog);
        model.addAttribute("typeSearch",typeService.listType());
        return "index";
    }

    // 全局搜索
    @PostMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam String query, @RequestParam Long typeId) {
        PageHelper.startPage(pageNum, 1000);
        List<FirstPageBlog> searchBlog = blogService.listSearchBlog(query, typeId);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        model.addAttribute("typeSearch",typeService.listType());
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String detailBlog(@PathVariable Long id, Model model){
        DetailBlog blogById = blogService.getDetailBlog(id);
        String tagIds = blogService.getBlogById(id).getTagIds();
        List<BlogTags> blogTags = tagService.listTagsByIds(tagIds);
        model.addAttribute("typeSearch",typeService.listType());
        model.addAttribute("blog",blogById);
        model.addAttribute("tags",blogTags);
        return "blog";
    }
}
