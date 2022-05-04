package com.xhd.blog.mapper;

import com.xhd.blog.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xhd.blog.entity.UserBlog;
import com.xhd.type.entity.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 徐浩东
 * @since 2022-03-27
 */
public interface BlogMapper extends BaseMapper<Blog> {
     /**
      * 更新访问量
      * @return
      */
     void updateView(Blog blog);

    /**
     * 查询热门前三文章
     * @return
     */
      List<UserBlog> hotContent();


     List<Type> selectTieba();
}
