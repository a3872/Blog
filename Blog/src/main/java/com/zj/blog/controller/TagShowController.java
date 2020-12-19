package com.zj.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.blog.pojo.BlogTags;
import com.zj.blog.pojo.BlogTypes;
import com.zj.blog.pojo.anotherPojo.FirstPageBlog;
import com.zj.blog.service.BlogService;
import com.zj.blog.service.TagService;
import com.zj.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName TypeShowController
 * @Description TODO
 * @Author 张杰
 * @Time 2020/12/13/16:56
 * @Version 1.0
 */
@Controller
public class TagShowController {
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    //    分页查询分类
    @GetMapping("/tags/{id}")
    public String types(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, @PathVariable Long id, Model model) {
        List<BlogTags> tags = tagService.listAllTagsAndBlog();
        //-1表示从首页导航点进来的
        if (id == -1) {
            id = tags.get(0).getId();
        }

        List<FirstPageBlog> blogs = blogService.listBlogByTagId(id);
        PageHelper.startPage(pageNum, 5);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogs);

        model.addAttribute("tags", tags);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTagId", id);
        model.addAttribute("typeSearch", typeService.listType());
        return "tags";
    }
}
