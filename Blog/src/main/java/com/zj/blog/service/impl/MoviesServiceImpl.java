package com.zj.blog.service.impl;

import com.zj.blog.dao.MoviesDaoMapper;
import com.zj.blog.handler.NotFoundException;
import com.zj.blog.pojo.Blog;
import com.zj.blog.pojo.BlogMovies;
import com.zj.blog.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName MoviesServiceImpl
 * @Description TODO
 * @Author 张杰
 * @Time 2020/12/16/18:02
 * @Version 1.0
 */
@Service
public class MoviesServiceImpl implements MoviesService {
    @Autowired
    private MoviesDaoMapper moviesDaoMapper;

    @Override
    public BlogMovies getDetailMovieById(Long id) {
        BlogMovies blogMovies = moviesDaoMapper.selectMovieById(id);
        if(blogMovies == null){
            throw new NotFoundException("该博客不存在");
        }
        moviesDaoMapper.updateViews(id);
        return blogMovies;
    }

    @Override
    public BlogMovies getById(Long id) {
        return moviesDaoMapper.getById(id);
    }

    @Override
    public List<BlogMovies> listMovies() {
        return moviesDaoMapper.selectAllMovies();
    }

    @Override
    public List<BlogMovies> SearchMovies(String title) {
        return moviesDaoMapper.SearchMovies(title);
    }

    @Override
    public int saveMovies(BlogMovies movies) {
        movies.setViews(0);
        movies.setCreateTime(new Date());
        movies.setUpdateTime(new Date());
        movies.setCommentCount(0);
        return moviesDaoMapper.insertMovies(movies);
    }

    @Override
    public int updateMovies(BlogMovies movies) {
        movies.setUpdateTime(new Date());
        return moviesDaoMapper.updateMovies(movies);
    }

    @Override
    public void deleteMovies(Long id) {
        moviesDaoMapper.deleteMovies(id);
    }
}
