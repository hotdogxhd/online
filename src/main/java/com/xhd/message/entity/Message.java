package com.xhd.message.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
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
 * @since 2022-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Message对象", description="")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "说说id")
    @TableId(value = "did", type = IdType.ID_WORKER_STR)
    private String did;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date alterTime;

    @ApiModelProperty(value = "说说内容")
    private String content;

    @ApiModelProperty(value = "作者id")
    private String id;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer deleted;


}
