package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.Sell;
import com.example.demo.entity.User;
import com.example.demo.mapper.SellMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.SellService;
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
public class SellServiceImpl extends ServiceImpl<SellMapper, Sell> implements SellService {

    @Resource
    private SellMapper sellMapper;

    @Override
    public IPage pageCC(IPage<Sell> page, Wrapper wrapper) {
        return sellMapper.pageCC(page,wrapper);
    }

}
