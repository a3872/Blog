package com.zj.blog.service;

import com.zj.blog.pojo.BlogFriendLink;

import java.util.List;

/**
 * @ClassName MyFriendsService
 * @Description TODO
 * @Author 张杰
 * @Time 2020/11/30/16:26
 * @Version 1.0
 */
public interface MyFriendsService {
    List<BlogFriendLink> listFriendsLink();
    BlogFriendLink findFriendLinkByAddress(String blogAdderss);
    int saveFriendsLink(BlogFriendLink blogFriendLink);
    BlogFriendLink findFriendLinkById(Long id);
    int updateFriendsLink(BlogFriendLink blogFriendLink);
    int deleteFriendsLink(Long id);
}
