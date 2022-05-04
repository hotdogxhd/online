package com.xhd.attention.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.xhd.attention.entity.Attention;
import com.xhd.attention.mapper.AttentionMapper;
import com.xhd.attention.service.AttentionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhd.user.entity.User;
import com.xhd.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 徐浩东
 * @since 2022-04-17
 */
@Service
public class AttentionServiceImpl extends ServiceImpl<AttentionMapper, Attention> implements AttentionService {
    @Autowired
    UserService userService;


    /**
     * 查询关注表的用户信息
     * @param session
     * @return
     */
   public List<User> getUser(HttpSession session){
       User user =(User) session.getAttribute("user");
       QueryWrapper<Attention> ob = new QueryWrapper<>();
       ob.eq("username",user.getName());
       List<Attention> list = this.list(ob);
       List<User> objects = new ArrayList<>();
       for (Attention a:list ) {
           String username = a.getAname();
           QueryWrapper<User> oc = new QueryWrapper<>();
           oc.eq("name",username);
           User one = userService.getOne(oc);
           objects.add(one);
       }
       return objects;
   }
    public List<User> getFans(HttpSession session){
        User user =(User) session.getAttribute("user");
        QueryWrapper<Attention> ob = new QueryWrapper<>();
        ob.eq("aname",user.getName());
        List<Attention> list = this.list(ob);
        List<User> objects = new ArrayList<>();
        for (Attention a:list ) {
            String username = a.getUsername();
            QueryWrapper<User> oc = new QueryWrapper<>();
            oc.eq("name",username);
            User one = userService.getOne(oc);
            objects.add(one);
        }
        return objects;
    }
    /**
     * 是否关注
     * @param id=被拜访id
     * @param session
     * @return
     */
    public Boolean isAtt(String id,HttpSession session){
        User byId = userService.getById(id);
        User user =(User) session.getAttribute("user");
        QueryWrapper<Attention> ob = new QueryWrapper<>();
        ob.eq("aname",byId.getName());
        ob.eq("username",user.getName());
        Attention one = this.getOne(ob);
        if (one==null){
            //没有关注
            return false;
        }
        //已经关注
        return true;
    }

    /**
     * 添加关注
     * @param id
     * @param session
     * @return
     */
    public Boolean addAtt(String id,HttpSession session){
        User user =(User) session.getAttribute("user");
        User byId = userService.getById(id);
        Attention attention = new Attention();
        attention.setAname(byId.getName());
        attention.setUsername(user.getName());
        boolean save = this.save(attention);
        return save;
    }
    public Boolean deleteAtt(String id,HttpSession session){
        User user =(User) session.getAttribute("user");
        User byId = userService.getById(id);
        QueryWrapper<Attention> ob = new QueryWrapper<>();
        ob.eq("aname",byId.getName());
        ob.eq("username",user.getName());
        boolean remove = this.remove(ob);
        return remove;
    }

    /**
     * 查询某人是否关注
     * @param name
     * @return
     */
    public List<User> ajaxname(String name,HttpSession session){
        User user = (User) session.getAttribute("user");
        QueryWrapper<Attention> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.like("aname",name);
        objectQueryWrapper.eq("username",user.getName());
        List<Attention> list = this.list(objectQueryWrapper);
        List<User> objects = new ArrayList<>();
        for (Attention a:list){
            String username = a.getAname();
            QueryWrapper<User> oc = new QueryWrapper<>();
            oc.eq("name",username);
            User one = userService.getOne(oc);
            objects.add(one);
        }
        return objects;
    }

    /**
     * 查询是否有某粉丝
     * @param name
     * @param session
     * @return
     */
    public List<User> ajaxfans(String name,HttpSession session){
        User user = (User) session.getAttribute("user");
        QueryWrapper<Attention> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.like("aname",user.getName());
        objectQueryWrapper.eq("username",name);
        List<Attention> list = this.list(objectQueryWrapper);
        List<User> objects = new ArrayList<>();
        for (Attention a:list){
            String username = a.getUsername();
            QueryWrapper<User> oc = new QueryWrapper<>();
            oc.eq("name",username);
            User one = userService.getOne(oc);
            objects.add(one);
        }
        return objects;
    }
    public void sNum(HttpSession session){
        User user = (User) session.getAttribute("user");
        QueryWrapper<Attention> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("username",user.getName());
        int count = this.count(objectQueryWrapper);
        session.setAttribute("attention",count);
        QueryWrapper<Attention> ob = new QueryWrapper<>();
        ob.eq("aname",user.getName());
        count = this.count(ob);
        session.setAttribute("fans",count);
    }
}
