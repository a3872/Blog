package com.zj.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.blog.pojo.BlogMovies;
import com.zj.blog.pojo.BlogUser;
import com.zj.blog.service.MoviesService;
import com.zj.blog.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName MoviesController
 * @Description TODO
 * @Author 张杰
 * @Time 2020/12/16/18:34
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin")
public class MoviesController {

    @Autowired
    private MoviesService moviesService;

    @GetMapping("/movies")
    public String showMovies(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        // 按照排序字段 倒序 排序
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,3,orderBy);
        List<BlogMovies> listMovies = moviesService.listMovies();
        PageInfo<BlogMovies> pageInfo = new PageInfo<BlogMovies>(listMovies);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/myMovies";
    }
    //跳转博客新增页面
    @GetMapping("/movies/input")
    public String input(Model model) {
        model.addAttribute("movies", new BlogMovies());
        return "admin/myMovies-inputs";
    }
    // 博客新增
    @PostMapping("/saveMovies")
    public String saveMovies(BlogMovies movies, @RequestParam("video") MultipartFile video, RedirectAttributes attributes, HttpSession session) throws IOException {
        // 设置用户
        movies.setUser((BlogUser) session.getAttribute("user"));
        // 设置用户id
        movies.setUserId(movies.getUser().getId());
        // 文件上传并保存路径
        String fileName = video.getOriginalFilename();
        String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\movies\\";
        FileUtils.fileupload(video.getBytes(), filePath, fileName);

        String fictitiousPath = "/movies/";
        movies.setVideoPath(fictitiousPath+fileName);

        int b = moviesService.saveMovies(movies);
        if(b == 0){
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/movies";
    }
    // 删除博客
    @GetMapping("/movies/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        moviesService.deleteMovies(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/movies";
    }
    // 跳转编辑修改文章
    @GetMapping("/movies/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        BlogMovies movies = moviesService.getById(id);
        model.addAttribute("movies", movies);
        return "admin/myMovies-inputs";
    }
    // 博客新增
    @PostMapping("/saveMovies/{id}")
    public String updateMovies(BlogMovies movies, @RequestParam("video") MultipartFile video, RedirectAttributes attributes, HttpSession session) throws IOException {
        // 设置用户
        movies.setUser((BlogUser) session.getAttribute("user"));
        //设置用户id
        movies.setUserId(movies.getUser().getId());
        // 文件上传并保存路径
        String fileName = video.getOriginalFilename();
        // 定义文件保存的真实路径
        String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\movies\\";
        FileUtils.fileupload(video.getBytes(), filePath, fileName);
        // 定义文件保存的虚拟路径
        String fictitiousPath = "/movies/";
        movies.setVideoPath(fictitiousPath+fileName);

        int b = moviesService.updateMovies(movies);
        if(b == 0){
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/movies";
    }
    // 搜索博客
    @PostMapping("/movies/search")
    public String search(Model model, BlogMovies movies,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        List<BlogMovies> searchMovies = moviesService.SearchMovies(movies.getTitle());
        PageHelper.startPage(pageNum, 10);
        PageInfo<BlogMovies> pageInfo = new PageInfo<BlogMovies>(searchMovies);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/myMovies :: moviesList";
    }
}
