package com.xhd.chatlist.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xhd.chatlist.entity.Chatlist;
import com.xhd.chatlist.service.ChatlistService;
import com.xhd.offline.entity.Offline;
import com.xhd.offline.service.OfflineService;
import com.xhd.user.entity.User;
import com.xhd.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 徐浩东
 * @since 2022-04-07
 */
@Controller
@RequestMapping("/chatlist")
public class ChatlistController {
    @Autowired
    UserService userService;
    @Autowired
    ChatlistService chatlistService;
    @Autowired
    OfflineService offlineService;
    /**
     *  访问聊天页面 增加列表
     */
    @GetMapping("addlist")
    public String addlist(String vid, HttpSession session){
        User byId = userService.getById(vid);
        User user = (User) session.getAttribute("user");
        //处理聊天列表没有 但是发消息过来要显示在自己的列表里
        List<String> distinctmessagae = offlineService.distinctmessagae(user.getName());
        QueryWrapper<Chatlist> chatlist = new QueryWrapper<>();
        chatlist.eq("name",user.getName());
        List<Chatlist> list = chatlistService.list(chatlist);
        if (list.size()==0){
            for (String name:distinctmessagae){
            QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
            objectQueryWrapper.eq("name", name);
            User one = userService.getOne(objectQueryWrapper);
            Chatlist chatlist1 = new Chatlist();
            chatlist1.setVname(one.getName());
            chatlist1.setUid(one.getId());
            chatlist1.setAvatar(one.getTAvatar());
            chatlist1.setName(user.getName());
            chatlistService.save(chatlist1);
            }
        }
        int co=0;
        for (String name:distinctmessagae){
            for (Chatlist c:list) {
                if (c.getVname().equals(name)) {
                    break;
                }
                co++;
                if (co == distinctmessagae.size()) {
                    QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
                    objectQueryWrapper.eq("name", name);
                    User one = userService.getOne(objectQueryWrapper);
                    Chatlist chatlist1 = new Chatlist();
                    chatlist1.setVname(one.getName());
                    chatlist1.setUid(one.getId());
                    chatlist1.setAvatar(one.getTAvatar());
                    chatlist1.setName(user.getName());
                    chatlistService.save(chatlist1);
                }
            }
        }

        if (vid==""){
        //自己  查询列表回显
            QueryWrapper<Chatlist> objectQueryWrapper = new QueryWrapper<>();
            objectQueryWrapper.ne("vname",user.getName());
            objectQueryWrapper.eq("name",user.getName());
            List<Chatlist> list1 = chatlistService.list(objectQueryWrapper);
            session.setAttribute("chatList", list1);
            return "chat";
        }else {
        //私聊  别人 添加到自己列里 先判断有没有
            QueryWrapper<Chatlist> objectQueryWrapper = new QueryWrapper<>();
            objectQueryWrapper.eq("name",user.getName());
            List<Chatlist> list2 = chatlistService.list(objectQueryWrapper);
            if (list2.size()==0){
                Chatlist chatlist1 = new Chatlist();
                chatlist1.setUid(byId.getId());
                chatlist1.setAvatar(byId.getTAvatar());
                chatlist1.setName(user.getName());
                chatlist1.setVname(byId.getName());
                chatlistService.save(chatlist1);
            }
                 Chatlist chatlist1 = new Chatlist();
                 int i=0;
            for (Chatlist chatlist2:list){
                if (chatlist2.getVname().equals(byId.getName())){
                   break;
                }
                 i++;
                if (i==list.size()){
                    chatlist1.setVname(byId.getName());
                    chatlist1.setName(user.getName());
                    chatlist1.setAvatar(byId.getTAvatar());
                    chatlist1.setUid(byId.getId());
                    chatlistService.save(chatlist1);
                }
            }
        }
             List<Chatlist> chatlisttwo= chatlistService.chatlistquery(byId.getName(),user.getName());
             session.setAttribute("chatList", chatlisttwo);
            return "chat";
//        QueryWrapper<Chatlist> objectQueryWrapper = new QueryWrapper<>();
//        objectQueryWrapper.ne("vname",user.getName());
//        objectQueryWrapper.eq("name",user.getName());
//        List<Chatlist> list = chatlistService.list(objectQueryWrapper);
//        //自己空间点击私信
//        if (vid==""){
//            session.setAttribute("chatList", list);
//            return "chat";
//        }
//        //点别人的空间进来 已经添加过得
//        List<Chatlist> chatlistquery = chatlistService.chatlistquery(byId.getName(),user.getName());
//        for (Chatlist c: chatlistquery) {
//            if (c.getUid().equals(byId.getId())){
//                session.setAttribute("chatList", chatlistquery);
//                return "chat";
//            }
//        }
//        //点别人的空间进来 没有添加过得 要重新放入域里 才能更新列表
//        Chatlist chatlist = new Chatlist();
//        chatlist.setUid(byId.getId());
//        chatlist.setAvatar(byId.getTAvatar());
//        chatlist.setName(user.getName());
//        chatlist.setVname(byId.getName());
//        chatlistService.save(chatlist);
//        List<Chatlist> chatlisttwo= chatlistService.chatlistquery(byId.getName(),user.getName());
//        session.setAttribute("chatList", chatlisttwo);
//        return "chat";
    }
    /**
     * 删除好友列表
     */
    @DeleteMapping("deleteone")
    public void deleteone(String vname){
        QueryWrapper<Chatlist> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("vname" ,vname);
        chatlistService.remove(objectQueryWrapper);
//        return "chat ::chat_box_left_bottom";
    }
}

