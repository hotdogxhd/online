package com.xhd.blog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.xhd.user.entity.User;
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
 * @since 2022-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Blog对象", description="")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章ID")
    @TableId(value = "bid", type = IdType.ID_WORKER_STR)
    private String bid;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "首图")
    @TableField("firstPicture")
    private String firstPicture;

    @ApiModelProperty(value = "创建时间")
    @TableField (fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date alterTime;

    @ApiModelProperty(value = "内容摘取")
    private String description;

    @ApiModelProperty(value = "作者id")
    private String id;

    @ApiModelProperty(value = "文章状态（1：置顶，2：原创，3：转载，0：首行）")
    private Integer status;

    @ApiModelProperty(value = "访问量")
    private Integer pageview;

    @ApiModelProperty(value = "分类")
    private String tieba;

    @ApiModelProperty(value = "是否发布")
    private String issue;

    @ApiModelProperty(value = "ip属地")
    private String IpAddress;

}
