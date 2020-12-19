package com.zj.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.blog.pojo.BlogFriendLink;
import com.zj.blog.pojo.BlogTypes;
import com.zj.blog.service.MyFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

/**
 * @ClassName MyFriendsController
 * @Description TODO
 * @Author 张杰
 * @Time 2020/11/30/16:19
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin")
public class MyFriendsController {
    @Autowired
    private MyFriendsService friendsService;

    // 分页查询友链列表
    @GetMapping("/friends")
    public String myFriends(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        // 按照排序字段 倒序 排序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,5,orderBy);
        List<BlogFriendLink> blogFriendLinks = friendsService.listFriendsLink();
        PageInfo<BlogFriendLink> pageInfo = new PageInfo<BlogFriendLink>(blogFriendLinks);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/myFriends";
    }

    // 跳转友链输入页面
    @GetMapping("/friends/input")
    public String input(Model model) {
        model.addAttribute("friends", new BlogFriendLink());
        return "admin/myFriends-inputs";
    }

    // 添加友链
    @PostMapping("/saveFriends")
    public String saveFriends(@Validated BlogFriendLink friendLink, RedirectAttributes attributes) {
        BlogFriendLink friendLinkByAddress = friendsService.findFriendLinkByAddress(friendLink.getBlogAddress());
        if(friendLinkByAddress != null){
            attributes.addFlashAttribute("message","不可添加重复友链！");
            return "redirect:/admin/friends/input";
        }
        friendLink.setCreateTime(new Date());
        int f = friendsService.saveFriendsLink(friendLink);
        if(f == 0){
            attributes.addFlashAttribute("message","插入失败！");
        }else {
            attributes.addFlashAttribute("message","新增成功！");
        }
        return "redirect:/admin/friends";
    }

    // 跳转修改页面
    @GetMapping("/friends/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("friends",friendsService.findFriendLinkById(id));
        return "admin/myFriends-inputs";
    }
    // 修改友链
    @PostMapping("/saveFriends/{id}")
    public String updateFriendsLink(@Validated BlogFriendLink friendLink, RedirectAttributes attributes){
        BlogFriendLink friendLinkByAddress = friendsService.findFriendLinkByAddress(friendLink.getBlogAddress());
        if(friendLinkByAddress != null){
            attributes.addFlashAttribute("message","不可添加重复友链！");
            return "redirect:/admin/friends/{id}/input";
        }
        friendLink.setCreateTime(new Date());
        int f = friendsService.updateFriendsLink(friendLink);
        if(f == 0){
            attributes.addFlashAttribute("message","插入失败！");
        }else {
            attributes.addFlashAttribute("message","修改成功！");
        }
        return "redirect:/admin/friends";
    }
    // 删除友链
    @GetMapping("/friends/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        friendsService.deleteFriendsLink(id);
        attributes.addFlashAttribute("message", "删除成功！");
        return "redirect:/admin/friends";
    }
}
