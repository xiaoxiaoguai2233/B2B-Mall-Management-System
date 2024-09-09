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
 * @since 2023-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Promotion对象", description="")
public class Promotion implements Serializable {
    @TableId(type = IdType.AUTO)
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "活动编码")
    private String code;

    @ApiModelProperty(value = "活动名称")
    private String name;

    @ApiModelProperty(value = "活动内容")
    private String content;

    @ApiModelProperty(value = "开始时间")
    private LocalDate start;

    @ApiModelProperty(value = "结束时间")
    private LocalDate ending;

    @ApiModelProperty(value = "经销商列表")
    private String commodity;

}
