package com.xhd.offline.mapper;

import com.xhd.offline.entity.Offline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 徐浩东
 * @since 2022-04-06
 */
public interface OfflineMapper extends BaseMapper<Offline> {

    void  notlikequery( String query);

    List<String> offinemessage(String name);

    List<String> distinctmessagae(String accept_name);
}
