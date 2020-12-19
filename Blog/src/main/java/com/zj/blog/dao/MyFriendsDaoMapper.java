package com.zj.blog.dao;

import com.zj.blog.pojo.BlogFriendLink;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName MyFriendsDao
 * @Description TODO
 * @Author 张杰
 * @Time 2020/11/30/16:32
 * @Version 1.0
 */
@Repository
@Mapper
public interface MyFriendsDaoMapper {
    List<BlogFriendLink> selectAllFriendsLink();
    BlogFriendLink selectFriendsLinkByAddress(String blogAddress);
    int insertFriendsLink(BlogFriendLink blogFriendLink);
    BlogFriendLink selectFriendsLinkById(Long id);
    int updateFriendsLink(BlogFriendLink blogFriendLink);
    int deleteFriendsLink(Long id);
}
