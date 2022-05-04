package com.xhd.chatlist.service.impl;

import com.xhd.chatlist.entity.Chatlist;
import com.xhd.chatlist.mapper.ChatlistMapper;
import com.xhd.chatlist.service.ChatlistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 徐浩东
 * @since 2022-04-07
 */
@Service
public class ChatlistServiceImpl extends ServiceImpl<ChatlistMapper, Chatlist> implements ChatlistService {
    @Autowired
    private ChatlistMapper chatlistMapper;


    @Override
    public List<Chatlist> chatlistquery(String vname,String name) {
        List<Chatlist> chatlistquery = chatlistMapper.chatlistquery(vname,name);
        return chatlistquery;
    }
}