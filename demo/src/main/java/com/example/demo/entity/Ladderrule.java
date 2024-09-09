package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
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
 * @since 2023-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Ladderrule对象", description="")
public class Ladderrule implements Serializable {
    @TableId(type = IdType.AUTO)
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品编码")
    private String code;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "类型")
    private Integer kind;

    @ApiModelProperty(value = "原价")
    private Integer origin;

    @ApiModelProperty(value = "促销价")
    private String promotional;

    @ApiModelProperty(value = "折扣率")
    private Float deposit;

    @ApiModelProperty(value = "阶梯折扣率")
    private Float depositstep;

    @ApiModelProperty(value = "开始时间")
    private LocalDate start;

    @ApiModelProperty(value = "结束时间")
    private LocalDate ending;


}
