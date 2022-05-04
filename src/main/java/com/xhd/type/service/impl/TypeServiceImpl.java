package com.xhd.type.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xhd.blog.entity.Blog;
import com.xhd.type.entity.Type;
import com.xhd.type.mapper.TypeMapper;
import com.xhd.type.service.TypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 徐浩东
 * @since 2022-04-01
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {


}
