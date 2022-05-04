package com.xhd.attention.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xhd.attention.entity.Attention;
import com.xhd.attention.service.AttentionService;
import com.xhd.user.entity.User;
import com.xhd.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 徐浩东
 * @since 2022-04-17
 */
@Controller
@RequestMapping("/attention")
public class AttentionController {

    @Autowired
    private AttentionService attentionService;

    /**
     * 获取关注列表
     * @param session
     * @return
     */
    @GetMapping("list")
    public String list(HttpSession session){
        List<User> users = attentionService.getUser(session);
        attentionService.sNum(session);
        session.setAttribute("attlist",users);
        return "attention";
    }
    @PutMapping("add")
    @ResponseBody
    public String add(String id,HttpSession session){
        Boolean aBoolean = attentionService.addAtt(id, session);
        if (aBoolean){
        return "已关注";
        }
        return "关注";
    }

    /**
     * 局部刷新
     * @param id
     * @param session
     * @return
     */
    @PutMapping("addd")
    public String addd(String id,HttpSession session){
        Boolean aBoolean = attentionService.addAtt(id, session);
        attentionService.sNum(session);
        return  "attention :: attentionNum";
    }

    /**
     * 局部刷新
     * @param id
     * @param session
     * @return
     */
    @DeleteMapping ("deletee")
    public String deletee(String id,HttpSession session){
        Boolean aBoolean = attentionService.deleteAtt(id, session);
        attentionService.sNum(session);
        return  "attention :: attentionNum";
    }
    @DeleteMapping ("delete")
    @ResponseBody
    public String delete(String id,HttpSession session){
        Boolean aBoolean = attentionService.deleteAtt(id, session);
        if (aBoolean){
            return "关注";
        }
        return "已关注";
    }
    @GetMapping("ajaxlist")
    public String ajaxlist(HttpSession session){
        List<User> users = attentionService.getUser(session);
        session.setAttribute("attlist",users);
        return "attention :: attlist";
    }
    @GetMapping("ajaxfanslist")
    public String ajaxfanslist(HttpSession session){
        List<User> users = attentionService.getFans(session);
        session.setAttribute("attlist",users);
        return "attention :: attlist";
    }
    /**
     * 模糊查询
     */
    @GetMapping("ajaxquery")
    public String ajaxquery(String name,HttpSession session){
        List<User> ajaxname = attentionService.ajaxname(name,session);
        session.setAttribute("attlist",ajaxname);
        return "attention :: attlist";
    }
    @GetMapping("ajaxfans")
    public String ajaxfans(String name,HttpSession session){
        List<User> ajaxname = attentionService.ajaxfans(name,session);
        session.setAttribute("attlist",ajaxname);
        return "attention :: attlist";
    }
}

