package com.zj.blog.dao;

import com.zj.blog.pojo.Blog;
import com.zj.blog.pojo.BlogMovies;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName MoviesDaoMapper
 * @Description TODO
 * @Author 张杰
 * @Time 2020/12/16/18:14
 * @Version 1.0
 */
@Repository
@Mapper
public interface MoviesDaoMapper {
    BlogMovies selectMovieById(Long id);

    BlogMovies getById(Long id);

    List<BlogMovies> selectAllMovies();

    List<BlogMovies> SearchMovies(String title);

    int insertMovies(BlogMovies movies);

    int updateMovies(BlogMovies movies);

    void deleteMovies(Long id);

    // 根据博客id查询出评论数量
    int selectCommentCountById(Long id);
    void updateViews(Long id);
}
