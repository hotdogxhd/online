package com.xhd.chatlist.mapper;

import com.xhd.chatlist.entity.Chatlist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 徐浩东
 * @since 2022-04-07
 */
public interface ChatlistMapper extends BaseMapper<Chatlist> {
    List<Chatlist> chatlistquery(@Param("vname") String vname,@Param("name") String name);
}
