package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.Ladderrule;
import com.example.demo.entity.Rule;
import com.example.demo.mapper.LadderruleMapper;
import com.example.demo.mapper.RuleMapper;
import com.example.demo.service.LadderruleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author demo
 * @since 2023-06-11
 */
@Service
public class LadderruleServiceImpl extends ServiceImpl<LadderruleMapper, Ladderrule> implements LadderruleService {

    @Resource
    private LadderruleMapper ladderruleMapper;

    @Override
    public IPage pageCC(IPage<Ladderrule> page, Wrapper wrapper) {
        return ladderruleMapper.pageCC(page,wrapper);
    }
}
