package com.xhd.login;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xhd.blog.service.BlogService;
import com.xhd.offline.service.OfflineService;
import com.xhd.user.entity.User;
import com.xhd.user.service.OssService;
import com.xhd.user.service.UserService;
import com.xhd.visit.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/login")
public class loginControler {
    
    @Autowired
    OssService ossService;
    @Autowired
    UserService userService;
    @Autowired
    VisitService visitService;
    @Autowired
    OfflineService offlineService;
    @Autowired
    BlogService blogService ;
    /**
     * 访问首页,设置session为空
     * @return
     */
    @GetMapping
    public String indexHt(HttpSession session,HttpServletRequest hsr){
        session.setAttribute("user",null);

        return "index";
    }
    /**
     *
     * @param session
     * @return
     */
    @GetMapping("info")
    public String logininfo(HttpSession session){
        if (session.getAttribute("user")==null){
            return "login/login";
        }else {
          return "article";
        }
    }
    /**
     * 跳转注册页面
     * @return
     */
    @GetMapping("register")
    public String register(){
        return "login/login";
    }


    /**
     * 接收头像上传到阿里云oss ，并保存数据库
     * @param
     * @return
     */
    @PostMapping("avataross")
    public String avatar(MultipartFile tAvatar,String isavatar,String name,String password,
                       String email,String intro,HttpSession session){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name",name);
        User one = userService.getOne(queryWrapper);
        if (one==null){
            User user = new User();
            if (!"1".equals(isavatar)) {
               user.setTAvatar("https://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/ea817f16d3e74edc8832c3408048c2caavatar.jpg");
            }else {
            String upfile = ossService.upfile(tAvatar);
            user.setTAvatar(upfile);
            }
            user.setName(name);
            user.setPassword(password);
            user.setEmail(email);
            user.setIntro(intro);
            boolean save = userService.save(user);
            if (save){
                session.setAttribute("return","注册成功");
                return "redirect:/login/return";
            }else {
                session.setAttribute("return","注册失败");
            }
        }else {
            session.setAttribute("return","用户名重复");
        }
        return "login/login";
    }
    /**
     *注册页面返回首页
     */
    @GetMapping("return")
    public String retu(){
        return "index";
    }

    /**
     * 登录请求
     */
    @PostMapping("/login")
    @ResponseBody
    public Boolean login(User u,HttpSession session){
         QueryWrapper queryWrapper = new QueryWrapper();
         Map map = new LinkedHashMap();
         map.put("name",u.getName());
         map.put("password",u.getPassword());
         queryWrapper.allEq(map);
         User one = userService.getOne(queryWrapper);
         if (one !=null){
             session.setAttribute("user",one);
             //获取访客信息并存储
             visitService.visitList(session,one.getId());
             //获取离线发送信息用户
             List<String> offinemessage = offlineService.offinemessage(u.getName());
             if (offinemessage.size()!=0){
                 String message="";
                 for (String name:offinemessage){
                     message=message+name+"  ";
                 }
                 session.setAttribute("offlinmessage",message);
             }
             return true;
         }
        return  false;
     }

     /**
     * 跳转个人空间
     * @return
     */
     @GetMapping("userinfo")
     public String userinfo(HttpSession session, HttpServletRequest hsr){
         String s = null;
         try {
             s = blogService.setIP(hsr);
         } catch (Exception exception) {
             exception.printStackTrace();
         }
         session.setAttribute("ipadress",s);
         return "redirect:/blog/page";
     }
}
