package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.demo.entity.Agency;
import com.example.demo.entity.Commodity;
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
public interface CommodityMapper extends BaseMapper<Commodity> {

    IPage pageC(IPage<Commodity> page);

    IPage pageCC(IPage<Commodity> page,@Param(Constants.WRAPPER) Wrapper wrapper);
}
