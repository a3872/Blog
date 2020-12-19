package com.zj.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.blog.pojo.BlogTags;
import com.zj.blog.pojo.anotherPojo.FirstPageBlog;
import com.zj.blog.service.BlogService;
import com.zj.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @ClassName TypeController
 * @Description TODO
 * @Author 张杰
 * @Time 2020/11/27/16:55
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;
    // 分页查询分类列表
    @GetMapping("/tags")
    public String types(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        // 按照排序字段 倒序 排序
        String orderBy = "t.id desc";
        PageHelper.startPage(pageNum,5,orderBy);
        List<BlogTags> list = tagService.listTag();
        PageInfo<BlogTags> pageInfo = new PageInfo<BlogTags>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/myTags";
    }

    // 跳转标签输入页面
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tags", new BlogTags());
        return "admin/myTags-inputs";
    }

    // 添加分类
    @PostMapping("/saveTags")
    public String saveTypes(@Validated BlogTags tags, RedirectAttributes attributes){
        BlogTags tagByName = tagService.getTagByName(tags.getTagName());
        if(tagByName != null){
            attributes.addFlashAttribute("message", "不能添加重复的标签");
            return "redirect:/admin/tags/input";
        }

        int t = tagService.saveTag(tags);
        if(t == 0){
            attributes.addFlashAttribute("message","添加失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/tags";
    }

    // 跳转修改页面
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tags",tagService.getTag(id));
        return "admin/myTags-inputs";
    }

    // 修改分类
    @PostMapping("/saveTags/{id}")
    public String updateType(@Validated BlogTags tags, RedirectAttributes attributes){
        BlogTags tagByName = tagService.getTagByName(tags.getTagName());
        if(tagByName != null){
            attributes.addFlashAttribute("message", "不能添加重复的标签");
            return "redirect:/admin/tags/{id}/input";
        }

        int t = this.tagService.updateTag(tags);
        if(t == 0){
            attributes.addFlashAttribute("message","添加失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/tags";
    }

    // 删除分类
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        List<FirstPageBlog> firstPageBlogs = blogService.listBlogByTagId(id);
        if(firstPageBlogs != null){
            attributes.addFlashAttribute("message","该标签下有博客，请先删除博客");
            return "redirect:/admin/tags";
        }else {
            tagService.deleteTag(id);
            attributes.addFlashAttribute("message","删除成功");
            return "redirect:/admin/tags";
        }
    }
}
