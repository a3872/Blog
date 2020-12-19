package com.zj.blog.controller;

import com.zj.blog.service.MyFriendsService;
import com.zj.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName FriendsShowController
 * @Description TODO
 * @Author 张杰
 * @Time 2020/12/14/17:26
 * @Version 1.0
 */
@Controller
public class FriendsShowController {
    @Autowired
    private MyFriendsService friendLinkService;
    @Autowired
    private TypeService typeService;
    @GetMapping("/friends")
    public String friends(Model model) {
        model.addAttribute("typeSearch", typeService.listType());
        model.addAttribute("friendLinks",friendLinkService.listFriendsLink());
        return "friends";
    }
}
