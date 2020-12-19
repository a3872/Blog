package com.zj.blog.controller;

import com.zj.blog.pojo.Blog;
import com.zj.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @ClassName TimeShowController
 * @Description TODO
 * @Author 张杰
 * @Time 2020/12/15/19:42
 * @Version 1.0
 */
@Controller
public class TimeShowController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/time")
    public String archive(Model model){
        List<Blog> blogs = blogService.listBlog();
        model.addAttribute("blogs", blogs);
        return "timeLine";
    }
}
