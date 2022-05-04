package com.xhd.banner.entity;

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
@ApiModel(value="Banner对象", description="")
public class Banner implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "轮播图id")
    @TableId(value = "bannerid", type = IdType.ID_WORKER)
    private String bannerid;

    @ApiModelProperty(value = "轮播图地址")
    private String bimg;

    @ApiModelProperty(value = "描述1")
    private String desone;

    @ApiModelProperty(value = "描述2")
    private String destwo;

    @ApiModelProperty(value = "前台展示（0：不显示 1：显示）")
    private Integer showpage;

    @ApiModelProperty(value = "展示位置（1：首页，2：博客页）")
    private Integer place;


}
