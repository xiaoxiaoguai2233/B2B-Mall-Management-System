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
@ApiModel(value="Agency对象", description="")
public class Agency implements Serializable {
    @TableId(type = IdType.AUTO)
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "经销商编码")
    private String code;

    @ApiModelProperty(value = "经销商名称")
    private String name;

    @ApiModelProperty(value = "联系方式")
    private String connection;


}
