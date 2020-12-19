package com.zj.blog.dao;

import com.zj.blog.pojo.BlogUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserDaoMapper
 * @Description TODO
 * @Author 张杰
 * @Time 2020/11/25/20:18
 * @Version 1.0
 */
@Repository
@Mapper
public interface UserDaoMapper {
    BlogUser findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
