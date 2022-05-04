package com.xhd.type.entity;

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
 * @since 2022-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Type对象", description="")
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类ID")
    private String typeid;

    @ApiModelProperty(value = "分类名")
    private String tieba;

    @ApiModelProperty(value = "分类id")
    private String bid;

}
