package com.xhd.message.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xhd.message.entity.Message;
import com.xhd.message.service.MessageService;
import com.xhd.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
 * @since 2022-03-30
 */
@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * 保存message
     * @param message
     * @return
     */
    @PostMapping("sendmessage")
    public String sendmessage(Message message){
        messageService.save(message);
        return "diary :: diarycontent";
    }
    /**
     * 获取最近一个月的message 给页面
     * @param model
     * @return
     */
    @GetMapping("diary")
    public String  diary(HttpSession session,String id){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = format.format(m);
        //m是一个月前
        QueryWrapper<Message> obj = new QueryWrapper<>();
        obj.eq("id",id);
        obj.between("create_time",mon,new Date());
        obj.orderByDesc("alter_time");
        List<Message> list = messageService.list(obj);
        session.setAttribute("message",list);
        return "diary";
    }

    /**
     * 删除message
     * @return
     */
    @GetMapping("delete")
    public String delete(String did,HttpSession session){
        messageService.removeById(did);
        User user = (User)session.getAttribute("user");
        return "redirect:/message/diary?id="+user.getId();
    }
    /**
     * 修改message
     * @return
     */
    @PutMapping("updatemessage")
    public String updateMessage(String did,String content){
        Message message = new Message();
        message.setDid(did);
        message.setContent(content);
        messageService.updateById(message);
        return "diary :: diarycontent";
    }
}

