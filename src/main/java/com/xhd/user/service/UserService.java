package com.xhd.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xhd.blog.entity.Blog;
import com.xhd.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-23
 */
public interface UserService extends IService<User> {
    IPage<Blog> getList( Integer PAGE_NUM, Integer PAGE);

}
