package com.zj.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.blog.pojo.Blog;
import com.zj.blog.pojo.BlogTypes;
import com.zj.blog.pojo.BlogUser;
import com.zj.blog.service.BlogService;
import com.zj.blog.service.TagService;
import com.zj.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName BlogController
 * @Description TODO
 * @Author 张杰
 * @Time 2020/12/1/18:40
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String showBlogs(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        // 按照排序字段 倒序 排序
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,3,orderBy);
        List<Blog> listBlogs = blogService.listBlog();
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(listBlogs);
        model.addAttribute("types",typeService.listType());
        model.addAttribute("pageInfo",pageInfo);
        return "admin/myBlogs";
    }

    //跳转博客新增页面
    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listAllTags());
        model.addAttribute("blog", new Blog());
        return "admin/myBlogs-inputs";
    }
    // 博客新增
    @PostMapping("/saveBlogs")
    public String saveBlogs(Blog blog, RedirectAttributes attributes, HttpSession session) throws IOException {
        // 设置用户
        blog.setUser((BlogUser) session.getAttribute("user"));

        // 设置blog的type
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTypeId(blog.getType().getId());

        // 给blog中的List<Tag>赋值
        blog.setTags(tagService.listTagsByIds(blog.getTagIds()));

        //设置用户id
        blog.setUserId(blog.getUser().getId());

        int b = blogService.saveBlog(blog);
        if(b == 0){
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/blogs";
    }
    // 删除博客
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }

    // 跳转编辑修改文章
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        Blog blog = blogService.getBlogById(id);
        blog.init();
        model.addAttribute("blog", blog);
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags",tagService.listAllTags());
        return "admin/myBlogs-update";
    }

    //    编辑修改文章
    @PostMapping("/saveBlogs/{id}")
    public String editPost(@Valid Blog blog, RedirectAttributes attributes,HttpSession session) {
        // 设置用户
        blog.setUser((BlogUser) session.getAttribute("user"));

        //设置blog的type
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTypeId(blog.getType().getId());

        // 给blog中的List<Tag>赋值
        blog.setTags(tagService.listTagsByIds(blog.getTagIds()));

        //设置用户id
        blog.setUserId(blog.getUser().getId());

        int b = blogService.updateBlog(blog);
        if(b ==0){
            attributes.addFlashAttribute("message", "修改失败");
        }else {
            attributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/blogs";
    }

    //    搜索博客
    @PostMapping("/blogs/search")
    public String search(Blog blog, Model model,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        List<Blog> searchBlog = blogService.SearchBlog(blog);
        PageHelper.startPage(pageNum, 10);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/myBlogs :: blogList";
    }
}
