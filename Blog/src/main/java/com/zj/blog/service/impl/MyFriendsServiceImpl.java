package com.zj.blog.service.impl;

import com.zj.blog.dao.MyFriendsDaoMapper;
import com.zj.blog.pojo.BlogFriendLink;
import com.zj.blog.service.MyFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName MyFriendsServiceImpl
 * @Description TODO
 * @Author 张杰
 * @Time 2020/11/30/16:27
 * @Version 1.0
 */
@Service
public class MyFriendsServiceImpl implements MyFriendsService {
    @Autowired
    private MyFriendsDaoMapper myFriendsDaoMapper;

    @Transactional
    @Override
    public List<BlogFriendLink> listFriendsLink() {
        return myFriendsDaoMapper.selectAllFriendsLink();
    }

    @Transactional
    @Override
    public BlogFriendLink findFriendLinkByAddress(String blogAddress) {
        return myFriendsDaoMapper.selectFriendsLinkByAddress(blogAddress);
    }

    @Transactional
    @Override
    public int saveFriendsLink(BlogFriendLink blogFriendLink) {
        return myFriendsDaoMapper.insertFriendsLink(blogFriendLink);
    }

    @Transactional
    @Override
    public BlogFriendLink findFriendLinkById(Long id) {
        return myFriendsDaoMapper.selectFriendsLinkById(id);
    }

    @Transactional
    @Override
    public int updateFriendsLink(BlogFriendLink blogFriendLink) {
        return myFriendsDaoMapper.updateFriendsLink(blogFriendLink);
    }

    @Transactional
    @Override
    public int deleteFriendsLink(Long id) {
        return myFriendsDaoMapper.deleteFriendsLink(id);
    }
}
