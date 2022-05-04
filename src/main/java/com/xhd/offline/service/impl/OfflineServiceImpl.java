package com.xhd.offline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhd.offline.entity.Offline;
import com.xhd.offline.service.OfflineService;
import com.xhd.offline.mapper.OfflineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 徐浩东
 * @since 2022-04-06
 */
@Service
public class OfflineServiceImpl extends ServiceImpl<OfflineMapper, Offline> implements OfflineService {

    @Autowired
    OfflineMapper offlineMapper;
    @Override
    public void notlikequery(String query) {
       offlineMapper.notlikequery(query);
    }

    @Override
    public List<String> offinemessage(String name) {
        List<String> offinemessage = offlineMapper.offinemessage(name);
        return offinemessage;
    }

    @Override
    public List<String> distinctmessagae(String accept_name) {
        List<String> distinctmessagae = offlineMapper.distinctmessagae(accept_name);
        return distinctmessagae;
    }
}
