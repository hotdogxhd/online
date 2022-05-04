package com.xhd.attention.service;

import com.xhd.attention.entity.Attention;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xhd.user.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 徐浩东
 * @since 2022-04-17
 */
public interface AttentionService extends IService<Attention> {
    List<User> getUser(HttpSession session);
    Boolean isAtt(String id,HttpSession session);
    Boolean addAtt(String id,HttpSession session);
    Boolean deleteAtt(String id,HttpSession session);
    List<User> getFans(HttpSession session);
    List<User> ajaxname(String name,HttpSession session);
    List<User> ajaxfans(String name,HttpSession session);
    void sNum(HttpSession session);
}
