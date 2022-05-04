package com.xhd.blog.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xhd.attention.service.AttentionService;
import com.xhd.blog.entity.Blog;
import com.xhd.blog.mapper.BlogMapper;
import com.xhd.blog.service.BlogService;
import com.xhd.user.entity.User;
import com.xhd.user.service.UserService;
import com.xhd.user.util.MarkdownUtils;
import com.xhd.visit.entity.Visit;
import com.xhd.visit.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 徐浩东
 * @since 2022-03-27
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
    //每页的页数
    private static Integer PAGE=8;
    //当前页
    private static Integer PAGE_NUM=1;

    @Autowired
    BlogService blogService;
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    UserService userService;
    @Autowired
    VisitService visitService;
    @Autowired
    AttentionService attentionService;

    /**
     *  博客的发布或保存
     * @param blog
     * @return
     */
    @PostMapping("issue")
    public String issue(Blog blog, HttpServletRequest hsp) throws Exception {
        if (blog.getTieba()==null){
            blog.setTieba("其他");
        }
        String s = blogService.setIP(hsp);
        blog.setIpAddress(s);
        Blog issuereturn = issuereturn(blog);
        blogService.save(issuereturn);
        return "redirect:/blog/page";
    }
    /**
     *  返回首页的分页
     * @param
     * @return
     */
    @GetMapping("page")
    public String pageindex(HttpSession session){
        User user = new User();
        if (session.getAttribute("visitorId")==null){
            user = (User)session.getAttribute("user");
        }else {
        User  visitor= userService.getById((String) session.getAttribute("visitorId"));
        session.setAttribute("visitor",visitor);
        user=visitor;
        }
        Page<Blog> page = new Page<>(PAGE_NUM, PAGE);
        QueryWrapper<Blog> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.orderByAsc("status");
        objectQueryWrapper.orderByDesc("alter_time");
        objectQueryWrapper.eq("id",user.getId());
        IPage<Blog> blogIPage = blogMapper.selectPage(page,objectQueryWrapper);
        session.setAttribute("blogIPage",blogIPage);
        return "article";
    }
    /**
     *   翻页分页
     * @param
     * @return
     */
    @GetMapping("pagesplit")
    public String  pagesplit(String title, @RequestParam(defaultValue = "1")Integer currentpage ,
                             HttpSession session){
        User user = new User();
        QueryWrapper<Blog> obj = new QueryWrapper<>();
        //判断是不是访客
        if (session.getAttribute("visitor")==null){
         user = (User)session.getAttribute("user");
        }else {
           user=(User) session.getAttribute("user");
        }
        obj.eq("id",user.getId());
        obj.like("title",title);
        obj.or();
        obj.like("tieba",title);
        obj.orderByAsc("status");
        obj.orderByDesc("alter_time");
        if (currentpage==null) currentpage=PAGE_NUM;
        Page<Blog> page = new Page<>(currentpage, PAGE);
        IPage<Blog> blogIPage = blogMapper.selectPage(page, obj);
        session.setAttribute("blogIPage",blogIPage);
        return "article :: LAY_bloglist";
    }

    /**
     * 广场文章的分页
     * @param title
     * @param currentpage
     * @param session
     * @return
     */
    @GetMapping("pagesplitplus")
    public String  pagesplitplus(String title, @RequestParam(defaultValue = "1")Integer currentpage ,
                             HttpSession session){
        QueryWrapper<Blog> obj = new QueryWrapper<>();
        obj.ne("status",4).and(blogQueryWrapper -> blogQueryWrapper.like("title", title).or().like("tieba", title));
        //  WHERE status <> ? AND ( title LIKE ? OR tieba LIKE ? ) ORDER BY alter_time DESC LIMIT 0,8
        obj.orderByDesc("alter_time");
        if (currentpage==null) currentpage=PAGE_NUM;
        Page<Blog> page = new Page<>(currentpage, PAGE);
        IPage<Blog> blogIPage = blogMapper.selectPage(page, obj);
        session.setAttribute("IPage",blogIPage);
        return "content :: bloglist";
    }
    /**
     *
     * 跳转更新文章页面
     * @param
     * @return
     */
    @GetMapping("updatablog")
    public String updatablog(String blogid,Model model){
        Blog byId = blogService.getById(blogid);
        model.addAttribute("blog",byId);
        return "edit";
    }

    /**
     * 更新文章
     * @param
     * @return
     */
    @PostMapping("upblog")
    public String upblog(Blog blog,HttpServletRequest hsp) throws Exception {
        String s = blogService.setIP(hsp);
        blog.setIpAddress(s);
        blogService.updateById(blog);
        return "redirect:/blog/page";
    }

    /**
     * 查看文章页面
     * @param
     * @return
     */
    @GetMapping("check")
    public String check(String blogid,Model model,HttpSession session){
        //MarkdownUtils会把内容改为html格式的
        blogService.readme(blogid,model,session);
        Blog byId = blogService.getById(blogid);
        Blog blog = new Blog();
        blog=byId;
        String contentHtml = MarkdownUtils.markdownToHtmlExtensions(blog.getContent());
        blog.setContent(contentHtml);
        //阅读量加1
        blogService.updateView(byId);
        User byId1 = userService.getById(byId.getId());
        model.addAttribute("author",byId1.getName());
        model.addAttribute("blog",blog);
        return "read";
    }

    /**
     * 删除文章页面
     * @param
     * @return
     */
    @GetMapping("delete")
    public String delete(String blogid){
        blogService.removeById(blogid);
        return "redirect:/blog/page";
    }

    /**
     * 说说页面
     * @param
     * @return
     */
    @GetMapping("diary")
    public String diary(){
        return "diary";
    }

    /**
     * 修改个人信息页面
     * @param
     * @return
     */
    @GetMapping("message")
    public String message(){
        return "message";
    }

    /**
     *
     * @param
     * @return
     */
    @PostMapping("sendmessage")
    public String sendmessage(String message){
        return "/blog/diary";
    }

    /**
     * 回首页轮播图
     * @param
     * @return
     */
    @GetMapping("index")
    public String index(HttpSession session){
        //session访客清空
        session.setAttribute("visitorId",null);
        session.setAttribute("visit",null);
       return "index";
    }
    public Blog issuereturn(Blog log){
        log.setPageview(0);
        if ((log.getIssue()).equals("保存")){
            log.setIssue("1");
            return log;
        }else {
            log.setIssue("2");
            return log;
        }
    }

    /**
     * 判断是不是访客
     * @param id=被拜访者id
     * @return
     */
    @GetMapping("visitor")
    public String visitor(String id, HttpSession session){
        User byId = userService.getById(id);
        User user = (User)session.getAttribute("user");
        if (byId.getId().equals(user.getId())) {
            session.setAttribute("visit",false);
        }else {
            if (!visitService.updatevnum(user.getId())){
                    visitService.save(visitService.visit(session,id));
                    //        访客量加1
                    visitService.addVnum(id);
            }
            session.setAttribute("visit",true);
            //存被访问者id 用来回显被访问者数据
            session.setAttribute("visitorId",id);
            Boolean att = attentionService.isAtt(id, session);
            if (att){
                session.setAttribute("attuser","已关注");
            }else {
            session.setAttribute("attuser","关注");
            }
        }
        return "redirect:/blog/page";
    }
}


