package com.zj.blog.service;

import com.zj.blog.pojo.BlogUser;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author 张杰
 * @Time 2020/11/25/20:14
 * @Version 1.0
 */
public interface UserService {
    BlogUser checkUser(String userName, String password);
}
