package com.zj.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.blog.pojo.BlogMovies;
import com.zj.blog.service.MoviesService;
import com.zj.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName MoviesShowController
 * @Description TODO
 * @Author 张杰
 * @Time 2020/12/16/16:54
 * @Version 1.0
 */
@Controller
public class MoviesShowController {
    @Autowired
    private MoviesService moviesService;
    @Autowired
    private TypeService typeService;
    @GetMapping("/movies")
    public String showMovies(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        // 按照排序字段 倒序 排序
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,5,orderBy);
        List<BlogMovies> listMovies = moviesService.listMovies();
        PageInfo<BlogMovies> pageInfo = new PageInfo<BlogMovies>(listMovies);
        model.addAttribute("typeSearch", typeService.listType());
        model.addAttribute("pageInfo",pageInfo);
        return "movies";
    }
    @GetMapping("/movies/{id}")
    public String detailMovie(@PathVariable Long id, Model model){
        BlogMovies movieById = moviesService.getDetailMovieById(id);
        model.addAttribute("movies",movieById);
        model.addAttribute("typeSearch", typeService.listType());
        return "movies_show";
    }

}
