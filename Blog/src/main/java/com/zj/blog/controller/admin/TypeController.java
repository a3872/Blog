package com.zj.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.blog.pojo.BlogTypes;
import com.zj.blog.pojo.anotherPojo.FirstPageBlog;
import com.zj.blog.service.BlogService;
import com.zj.blog.service.TypeService;
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
public class TypeController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;

    // 分页查询分类列表
    @GetMapping("/types")
    public String types(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        // 按照排序字段 倒序 排序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,5,orderBy);
        List<BlogTypes> list = typeService.listType();
        PageInfo<BlogTypes> pageInfo = new PageInfo<BlogTypes>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/myTypes";
    }

    // 跳转分类输入页面
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type", new BlogTypes());
        return "admin/myTypes-inputs";
    }

    // 添加分类
    @PostMapping("/saveTypes")
    public String saveTypes(@Validated BlogTypes types, RedirectAttributes attributes){
        BlogTypes typeByName = typeService.getTypeByName(types.getTypeName());
        if(typeByName != null){
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        int t = typeService.saveType(types);
        if(t == 0){
            attributes.addFlashAttribute("message","添加失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }



    // 修改分类
    @PostMapping("/saveTypes/{id}")
    public String updateType(@Validated BlogTypes types, RedirectAttributes attributes){
        BlogTypes typeByName = typeService.getTypeByName(types.getTypeName());
        if(typeByName != null){
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/types/{id}/input";
        }
        int t = this.typeService.updateType(types);
        if(t == 0){
            attributes.addFlashAttribute("message","添加失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }

    // 跳转修改页面
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/myTypes-inputs";
    }

    // 删除分类
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        List<FirstPageBlog> firstPageBlogs = blogService.listBlogByTypeId(id);
        if(firstPageBlogs != null){
            attributes.addFlashAttribute("message","该分类下有博客，请先删除博客");
            return "redirect:/admin/types";
        }else {
            typeService.deleteType(id);
            attributes.addFlashAttribute("message","删除成功");
            return "redirect:/admin/types";
        }
    }
}
