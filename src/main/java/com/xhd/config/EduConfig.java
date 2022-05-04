package com.xhd.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


@Configuration
@MapperScan({"com.xhd.banner.mapper","com.xhd.blog.mapper","com.xhd.message.mapper",
        "com.xhd.type.mapper","com.xhd.user.mapper","com.xhd.visit.mapper","com.xhd.offline.mapper","com.xhd.chatlist.mapper","com.xhd.attention.mapper"})
public class EduConfig {
    /*
     * 逻辑删除的插件
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     *  注册@ServerEndpoint所在类
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
