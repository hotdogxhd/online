package com.xhd.chatlist.service;

import com.xhd.chatlist.entity.Chatlist;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 徐浩东
 * @since 2022-04-07
 */
public interface ChatlistService extends IService<Chatlist> {
    List<Chatlist> chatlistquery(String vname,String name);
}
