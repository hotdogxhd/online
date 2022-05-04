package com.xhd.offline.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xhd.offline.entity.Offline;
import com.xhd.offline.service.OfflineService;
import com.xhd.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 * @author 徐浩东
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/offline")
public class OfflineController {
    @Autowired
    private OfflineService offlineService;

    @GetMapping("acceptChat")
    @ResponseBody
    public List<Offline> acceptChat(String acceptname, HttpSession session){
        User user = (User) session.getAttribute("user");
        //移除消息提示
        String offlinmessage = (String) session.getAttribute("offlinmessage");
        if (offlinmessage!=null) {
            String trim = offlinmessage.trim();
            if (trim.equals(acceptname)) {
                Offline offline = new Offline();
                offline.setAcceptName(user.getName());
                offline.setSendName(acceptname);
                offline.setOfflineMessage(0);
                offlineService.update(offline, null);
                session.removeAttribute("offlinmessage");
            }
        }
        //回显聊天记录
        QueryWrapper<Offline> off = new QueryWrapper<>();
        off.in("send_name",user.getName(),acceptname);
        off.in("accept_name",user.getName(),acceptname);
        off.notLike("isdel_send",user.getName());
        List<Offline> list = offlineService.list(off);
        return  list;
    }

    @GetMapping("delete")
    public String delete(String acceptname,HttpSession session){
        User user = (User) session.getAttribute("user");
        QueryWrapper<Offline> off = new QueryWrapper<>();
        off.in("send_name",user.getName(),acceptname);
        off.in("accept_name",user.getName(),acceptname);
        off.like("isdel_send",user.getName());
//        off.or();
//        off.like("isdel_send",acceptname);
        List<Offline> list = offlineService.list(off);
        if (list.size()==0){
        offlineService.notlikequery(user.getName());
        }
        return null;
    }
}

