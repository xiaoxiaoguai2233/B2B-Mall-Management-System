package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.Rule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author demo
 * @since 2023-06-10
 */
public interface RuleService extends IService<Rule> {

    IPage pageCC(IPage<Rule> page, Wrapper wrapper);
}
