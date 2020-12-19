package com.zj.blog.dao;

import com.zj.blog.pojo.BlogTypes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName TypeDap
 * @Description TODO
 * @Author 张杰
 * @Time 2020/11/27/15:56
 * @Version 1.0
 */
@Repository
@Mapper
public interface TypeDaoMapper {
    int insertTypes(BlogTypes types);

    BlogTypes selectTypes(Long id);

    BlogTypes selectTypeByName(String typeName);

    List<BlogTypes> selectAllTypes();

    int updateTypes(BlogTypes types);

    void deleteTypes(Long id);

    // 分类页面查询
    List<BlogTypes> selectAllTypeAndBlog();

}
