package com.xhd.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xhd.attention.mapper.AttentionMapper;
import com.xhd.blog.entity.Blog;
import com.xhd.blog.entity.UserBlog;
import com.xhd.blog.mapper.BlogMapper;
import com.xhd.blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhd.chitchat.util.IPUtils;
import com.xhd.type.entity.Type;
import com.xhd.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 徐浩东
 * @since 2022-03-27
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    BlogMapper blogMapper;


    @Override
    public void  updateView(Blog blog) {
       blogMapper.updateView(blog);
    }

    @Override
    public  List<UserBlog> hotConent() {
        List<UserBlog> blogs = blogMapper.hotContent();
        return blogs;
    }

    @Override
    public List<Type> selectTieba() {
        List<Type> types = blogMapper.selectTieba();
        return types;
    }
    /**
     * 区分大厅阅读和个人空间阅读
     */
    public void readme(String blogid, Model model, HttpSession session){
        Blog byId = this.getById(blogid);
        User user = (User)session.getAttribute("user");
        //判断user null=未登录访问
        if (user!=null){
            //判断阅读者和作者是不是同一个人，是就添加1
            if (byId.getId().equals(user.getId())){
                session.setAttribute("reader",1);
            }
        }
    }

    public String setIP(HttpServletRequest hsp) throws Exception {
        String ipAddr = IPUtils.getIpAddr(hsp);
        String address = IPUtils.getAddress(ipAddr);
        return address;
    }
}
