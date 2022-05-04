package com.xhd.message.service.impl;

import com.xhd.message.entity.Message;
import com.xhd.message.mapper.MessageMapper;
import com.xhd.message.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 徐浩东
 * @since 2022-03-30
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
