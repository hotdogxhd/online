package com.xhd.visit.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 徐浩东
 * @since 2022-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Visit对象", description="")
public class Visit implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "访问表id")
    @TableId(value = "visitid", type = IdType.ID_WORKER_STR)
    private String visitid;

    @ApiModelProperty(value = "被访问用户id")
    private String id;

    @ApiModelProperty(value = "访客姓名")
    private String vname;

    @ApiModelProperty(value = "访客头像")
    private String vavatar;

    @ApiModelProperty(value = "访问用户id")
    private String vid;

    @ApiModelProperty(value = "访客数")
    private Integer vnum=0;

    @ApiModelProperty(value = "访问时间")
    @TableField(fill = FieldFill.INSERT)
    private Date vtime;
}
