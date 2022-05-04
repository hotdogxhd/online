package com.xhd.visit.service;

import com.xhd.visit.entity.Visit;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 徐浩东
 * @since 2022-04-02
 */
public interface VisitService extends IService<Visit> {
    void addVnum(String id);
    void visitList(HttpSession session, String id);
    Boolean  updatevnum(String vid);
    Date DayNum(int day);
    Visit visit(HttpSession session,String id);
}
