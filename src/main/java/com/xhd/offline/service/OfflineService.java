package com.xhd.offline.service;

import com.xhd.offline.entity.Offline;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 徐浩东
 * @since 2022-04-06
 */
public interface OfflineService extends IService<Offline> {
   void  notlikequery(String query);
   List<String> offinemessage(String name);
   List<String> distinctmessagae(String accept_name);
}
