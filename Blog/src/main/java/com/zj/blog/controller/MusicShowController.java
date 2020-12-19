package com.zj.blog.controller;

import com.zj.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName AboutShowController
 * @Description TODO
 * @Author 张杰
 * @Time 2020/12/14/17:20
 * @Version 1.0
 */
@Controller
public class MusicShowController {
    @Autowired
    private TypeService typeService;
    @GetMapping("/music")
    public String about(Model model){
        model.addAttribute("typeSearch", typeService.listType());
        return "music";
    }
}
