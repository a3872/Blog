package com.zj.blog.service.impl;

import com.zj.blog.dao.UserDaoMapper;
import com.zj.blog.pojo.BlogUser;
import com.zj.blog.service.UserService;
import com.zj.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author 张杰
 * @Time 2020/11/25/20:16
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDaoMapper userDaoMapper;

    @Transactional
    @Override
    public BlogUser checkUser(String userName, String password) {
        return userDaoMapper.findByUsernameAndPassword(userName, MD5Utils.code(password));
    }
}
