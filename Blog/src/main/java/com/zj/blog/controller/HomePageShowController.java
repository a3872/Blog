package com.zj.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName HomePageShowController
 * @Description TODO
 * @Author 张杰
 * @Time 2020/12/12/20:16
 * @Version 1.0
 */
@Controller
public class HomePageShowController {
    @GetMapping("/")
    public String home(){
        return "HomePage";
    }
}
