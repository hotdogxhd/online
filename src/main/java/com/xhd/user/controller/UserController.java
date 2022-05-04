package com.xhd.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xhd.blog.entity.Blog;
import com.xhd.blog.entity.UserBlog;
import com.xhd.blog.service.BlogService;
import com.xhd.type.entity.Type;
import com.xhd.user.entity.User;
import com.xhd.user.service.OssService;
import com.xhd.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-23
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    OssService ossService;
    @Autowired
    BlogService blogService;
    /**
     * 跳转编辑页面
     * @return
     */
    @GetMapping("edit")
    public String edit(){
        return "edit";
    }
    /**
     * 返回个人空间首页
     */
    @GetMapping("article")
    public String article(){
        return "article";
    }
    /**
     * 更新个人信息
     * @param
     * @return
     */
    @PostMapping("updateMessage")
    public String  updateMessage(MultipartFile tAvatar,String name, String password,
                                 String email, String intro, Model model,String id){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name",name);
        User one = userService.getOne(queryWrapper);
        if (one==null){
            User user = new User();
            String upfile = ossService.upfile(tAvatar);
            user.setId(id);
            user.setName(name);
            user.setPassword(password);
            user.setEmail(email);
            user.setIntro(intro);
            user.setTAvatar(upfile);
            boolean save = userService.updateById(user);
            if (save){
                model.addAttribute("return","更新成功，请重新登录");
            }else {
                model.addAttribute("return","更新失败");
            }
        }else {
            model.addAttribute("return","用户名重复");
        }
         return "message";
    }
    /**
     * 跳转个content页面
     */
    @GetMapping("content")
    public String content(HttpSession session){
        List<UserBlog> blogs = blogService.hotConent();
        session.setAttribute("hotContent",blogs);
        List<Type> types = blogService.selectTieba();
        session.setAttribute("types",types);
        IPage<Blog> list = userService.getList( 1, 8);
        session.setAttribute("IPage",list);
        QueryWrapper<Blog> obj = new QueryWrapper<>();
        obj.eq("status",4);
        Blog one = blogService.getOne(obj);
        session.setAttribute("firstBlog",one);
        return "content";
    }

}

