package com.xhd.chitchat.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="服务端给浏览器的数据对象", description="")
public class ResultMessage {
    @ApiModelProperty(value = "是否是系统消息")
    private Boolean isStstem;

    @ApiModelProperty(value = "用户名")
    private String fromName;

    @ApiModelProperty(value = "消息体")
    private Object message;

    @ApiModelProperty(value = "头像地址")
    private String avataraddress;
}
