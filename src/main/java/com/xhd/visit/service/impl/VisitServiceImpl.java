package com.xhd.visit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xhd.user.entity.User;
import com.xhd.visit.entity.Visit;
import com.xhd.visit.mapper.VisitMapper;
import com.xhd.visit.service.VisitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 徐浩东
 * @since 2022-04-02
 */
@Service
public class VisitServiceImpl extends ServiceImpl<VisitMapper, Visit> implements VisitService {
    @Autowired
    VisitMapper visitMapper;


    @Override
    public void addVnum(String id) {
        visitMapper.addVnum(id);
    }
    /**
     * 存访问者信息给用户回显
     * @param session
     */
    public void visitList(HttpSession session, String id){
        //获取7天内的访客
        Date date = DayNum(7);
        QueryWrapper<Visit> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("id",id);
        objectQueryWrapper.between("vtime",date,new Date());
        List<Visit> list = this.list(objectQueryWrapper);
        session.setAttribute("visitlist",list);
    }
    /**
     * 返回访客对象
     * @param session
     * @param id id=被拜访者id
     * @return
     */
    public Visit visit(HttpSession session,String id){
        Visit visit = new Visit();
        User user = (User)session.getAttribute("user");
        visit.setId(id);
        visit.setVname(user.getName());
        visit.setVavatar(user.getTAvatar());
        visit.setVid(user.getId());
        return visit;
    }
    //判断最近一天有没有添加过該访问用户
    public Boolean  updatevnum(String vid) {
        QueryWrapper<Visit> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("vid",vid);
        objectQueryWrapper.between("vtime",DayNum(1),new Date());
        Visit one = this.getOne(objectQueryWrapper);
        if (one ==null){
            return false;
        }
        return true;
    }

    //返回一天前时间
    public  Date DayNum(int day){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        //过去1天
        c.setTime(new Date());
        c.add(Calendar.DATE, - day);
        Date d = c.getTime();
        return d;
    }
}
