package com.zj.blog.controller;

import com.zj.blog.pojo.BlogComment;
import com.zj.blog.pojo.BlogUser;
import com.zj.blog.pojo.anotherPojo.DetailBlog;
import com.zj.blog.service.BlogService;
import com.zj.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName CommentController
 * @Description TODO
 * @Author 张杰
 * @Time 2020/12/11/17:46
 * @Version 1.0
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    // 查询评论列表
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        List<BlogComment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }

    // 新增评论
    @PostMapping("/comments")
    public String saveComments(BlogComment comment, HttpSession session, Model model) {
        Long blogId = comment.getBlogId();
        // 判断是否为管理员
        BlogUser user = (BlogUser) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
            comment.setNickName(user.getNickName());
        } else {
            //设置头像
            comment.setAvatar(avatar);
        }
        if (comment.getParentComment().getId() != null) {
            comment.setParentCommentId(comment.getParentComment().getId());
        }
        commentService.saveComment(comment);
        List<BlogComment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }

    // 删除评论
    @GetMapping("/comment/{blogId}/{id}/delete")
    public String delete(@PathVariable Long blogId, @PathVariable Long id, BlogComment comment, RedirectAttributes attributes, Model model){
        commentService.deleteComment(comment,id);
        DetailBlog detailedBlog = blogService.getDetailBlog(blogId);
        List<BlogComment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("blog", detailedBlog);
        model.addAttribute("comments", comments);
        return "blog";
    }
}
