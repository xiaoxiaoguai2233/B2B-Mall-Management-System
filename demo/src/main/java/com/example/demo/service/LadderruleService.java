package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.Ladderrule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Rule;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author demo
 * @since 2023-06-11
 */
public interface LadderruleService extends IService<Ladderrule> {

    IPage pageCC(IPage<Ladderrule> page, Wrapper wrapper);
}
