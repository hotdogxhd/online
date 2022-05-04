package com.xhd.attention.entity;

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
 * @since 2022-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Attention对象", description="")
public class Attention implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关注表的主键")
    @TableId(value = "aid", type = IdType.ID_WORKER_STR)
    private String aid;

    @ApiModelProperty(value = "被关注姓名")
    private String aname;

    @ApiModelProperty(value = "用户姓名")
    private String username;


}
