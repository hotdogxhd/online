package com.xhd.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 徐浩东
 * @since 2022-03-23
 */
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "用户姓名")
    private String name;

    @ApiModelProperty(value = "用户简介")
    private String intro;

    @ApiModelProperty(value = "用户头像")
    private String tAvatar;

    @ApiModelProperty(value = "排序")
    private Integer tSort;

    @ApiModelProperty(value = "逻辑删除")
    private Integer isDeleted;
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "访客表ID")
    private String fid;
    @TableId(value = "bid", type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "博客表ID")
    private String bid;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户邮箱")
    private String email;


}
