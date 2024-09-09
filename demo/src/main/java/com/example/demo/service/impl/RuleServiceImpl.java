package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.Rule;
import com.example.demo.entity.User;
import com.example.demo.mapper.RuleMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.RuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author demo
 * @since 2023-06-10
 */
@Service
public class RuleServiceImpl extends ServiceImpl<RuleMapper, Rule> implements RuleService {

    @Resource
    private RuleMapper ruleMapper;

    @Override
    public IPage pageCC(IPage<Rule> page, Wrapper wrapper) {
        return ruleMapper.pageCC(page,wrapper);
    }
}
