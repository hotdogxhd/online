package com.xhd.chatlist.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Chatlist对象", description="")
public class Chatlist implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "cid", type = IdType.ID_WORKER_STR)
    private String cid;

    @ApiModelProperty(value = "聊天列表用户id")
    private String uid;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户姓名")
    private String name;

    @ApiModelProperty(value = "列表集合姓名")
    private String vname;


}
