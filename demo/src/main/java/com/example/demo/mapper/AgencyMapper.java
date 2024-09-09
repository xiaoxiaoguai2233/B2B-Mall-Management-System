package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.demo.entity.Agency;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
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
public interface AgencyMapper extends BaseMapper<Agency> {
    IPage pageC(IPage<Agency> page);

    IPage pageCC(IPage<Agency> page,@Param(Constants.WRAPPER) Wrapper wrapper);
}
