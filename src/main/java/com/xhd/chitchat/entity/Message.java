package com.xhd.chitchat.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 浏览器给服务端的数据
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="浏览器给服务端的数据对象", description="")
public class Message {
    @ApiModelProperty(value = "用户名")
    private String toName;

    @ApiModelProperty(value = "消息体")
    private String message;

    @ApiModelProperty(value = "头像地址")
    private String avataraddress;


}
