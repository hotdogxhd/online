package com.xhd.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class MetaObjectHandle implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        /**
         * 类中的属性名
         */
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("alterTime", new Date(), metaObject);
        this.setFieldValByName("vtime", new Date(), metaObject);
    }
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("alterTime", new Date(), metaObject);
    }
}
