package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.demo.entity.Ladderrule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Rule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author demo
 * @since 2023-06-11
 */
@Mapper
public interface LadderruleMapper extends BaseMapper<Ladderrule> {

    IPage pageCC(IPage<Ladderrule> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
