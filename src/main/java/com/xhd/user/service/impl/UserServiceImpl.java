package com.xhd.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xhd.blog.entity.Blog;
import com.xhd.blog.mapper.BlogMapper;
import com.xhd.blog.service.BlogService;
import com.xhd.user.entity.User;
import com.xhd.user.mapper.UserMapper;
import com.xhd.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    BlogService blogService;
    @Autowired
    BlogMapper blogMapper;

    public IPage<Blog> getList(Integer PAGE_NUM,Integer PAGE){
        Page<Blog> page = new Page<>(PAGE_NUM, PAGE);
        QueryWrapper<Blog> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.orderByDesc("alter_time");
        objectQueryWrapper.ne("status",4);
        IPage<Blog> blogIPage = blogMapper.selectPage(page,objectQueryWrapper);
        return blogIPage;
    }

}
