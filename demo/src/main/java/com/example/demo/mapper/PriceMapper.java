package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.demo.entity.Commodity;
import com.example.demo.entity.Price;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author demo
 * @since 2023-06-09
 */
@Mapper
public interface PriceMapper extends BaseMapper<Price> {

    IPage pageC(IPage<Price> page);

    IPage pageCC(IPage<Price> page,@Param(Constants.WRAPPER) Wrapper wrapper);
}
