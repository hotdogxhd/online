package com.xhd.visit.mapper;

import com.xhd.visit.entity.Visit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 徐浩东
 * @since 2022-04-02
 */
public interface VisitMapper extends BaseMapper<Visit> {
     void addVnum(String id);
}
