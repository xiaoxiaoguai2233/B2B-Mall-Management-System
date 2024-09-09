package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author demo
 * @since 2023-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Commodity对象", description="")
public class Commodity implements Serializable {
    @TableId(type = IdType.AUTO)
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品编码")
    private String code;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "规格")
    private String sp;

    @ApiModelProperty(value = "净重")
    private String nw;

    @ApiModelProperty(value = "毛重")
    private String rw;

    @ApiModelProperty(value = "箱容")
    private Integer cp;


}
