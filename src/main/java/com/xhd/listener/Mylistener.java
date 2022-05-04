package com.xhd.listener;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.xhd.banner.entity.Banner;
import com.xhd.banner.service.BannerService;
import com.xhd.type.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;

import org.springframework.context.ApplicationListener;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import javax.servlet.ServletContext;
import java.util.List;

//监听器
@Component
public class Mylistener   implements ApplicationListener<ApplicationStartedEvent> {

     @Autowired
     TypeService typeService;
     @Autowired
     BannerService bannerService;
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext();
        System.out.println("监听器工作：将所有的文章类型放到作用域中");
        // 将 ApplicationContext 转化为 WebApplicationContext
        WebApplicationContext webApplicationContext = (WebApplicationContext) event.getApplicationContext();
        // 从 webApplicationContext中获取servletContext
        ServletContext servletContext = webApplicationContext.getServletContext();
        servletContext.setAttribute("types", typeService.list(null));// servletContext设置值
        QueryWrapper<Banner> bq = new QueryWrapper<>();
        bq.eq("showpage","1");
        bq.eq("place","1");
        List<Banner> list = bannerService.list(bq);
        servletContext.setAttribute("bannnerblog",list);// servletContext设置值
    }
}
