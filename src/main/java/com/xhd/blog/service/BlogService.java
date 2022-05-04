package com.xhd.blog.service;

import com.xhd.blog.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xhd.blog.entity.UserBlog;
import com.xhd.type.entity.Type;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 徐浩东
 * @since 2022-03-27
 */
public interface BlogService extends IService<Blog> {
    void updateView(Blog blog);
//    保存前三热门文章
    List<UserBlog> hotConent();

    /**
     * 查询前五的type
     */
    List<Type> selectTieba();

     void readme(String blogid, Model model, HttpSession session);

    /**
     * 获取ip地址属地
     * @param hsp
     * @return
     * @throws Exception
     */
    public String setIP(HttpServletRequest hsp) throws Exception ;
}
