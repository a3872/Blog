package com.zj.blog.service;

import com.zj.blog.pojo.Blog;
import com.zj.blog.pojo.BlogMovies;

import java.util.List;

/**
 * @ClassName MoviesService
 * @Description TODO
 * @Author 张杰
 * @Time 2020/12/16/16:59
 * @Version 1.0
 */
public interface MoviesService {
    BlogMovies getDetailMovieById(Long id);

    BlogMovies getById(Long id);

    List<BlogMovies> listMovies();

    List<BlogMovies> SearchMovies(String title);

    int saveMovies(BlogMovies movies);

    int updateMovies(BlogMovies movies);

    void deleteMovies(Long id);
}
