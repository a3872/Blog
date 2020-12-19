package com.zj.blog.controller.admin;

import com.zj.blog.pojo.BlogUser;
import com.zj.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginController
 * @Description 登录控制
 * @Author 张杰
 * @Time 2020/11/25/20:48
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    /**
     * 用户名密码验证
     * @param username
     * @param password
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam String userName,
                        @RequestParam String password,
                                      HttpSession session,
                                      RedirectAttributes attributes){
        BlogUser blogUser = userService.checkUser(userName, password);
        if(blogUser != null){
            blogUser.setPassword(null);
            session.setAttribute("user",blogUser);
            return "admin/admin_index";
        }else {
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @GetMapping("/loginOut")
    public String logOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}