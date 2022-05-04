package com.xhd.offline.entity;

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
 * @since 2022-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Offline对象", description="")
public class Offline implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "发送人姓名")
    @TableId(value = "chat_id", type = IdType.ID_WORKER_STR)
    private String chatId;

    @ApiModelProperty(value = "发送人姓名")
    private String sendName;

    @ApiModelProperty(value = "接受人性名")
    private String acceptName;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "发送时间")
    private Date sendTime;

    @ApiModelProperty(value = "发送人逻辑删除")
    private String isdel_send;

    @ApiModelProperty(value = "离线消息")
    private Integer offlineMessage;
}
