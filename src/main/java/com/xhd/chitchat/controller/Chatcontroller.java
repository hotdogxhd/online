package com.xhd.chitchat.controller;

import com.xhd.user.entity.User;
import com.xhd.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller()
@RequestMapping("/chat")
public class Chatcontroller {
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("chatindex")
//    public String charindex(HttpSession session,@RequestParam(value = "vid",required = false) String vid) {
//        User user = null;
//        if (vid != null || vid != "") {
//            User byId = userService.getById(vid);
//            List<User> users = new ArrayList<>();
//            users.add(byId);
//            session.setAttribute("chatList", users);
//        }
//        return "chat";
//    }
}
