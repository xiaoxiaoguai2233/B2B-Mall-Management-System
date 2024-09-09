package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.Agency;
import com.example.demo.entity.User;
import com.example.demo.mapper.AgencyMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.AgencyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author demo
 * @since 2023-06-09
 */
@Service
public class AgencyServiceImpl extends ServiceImpl<AgencyMapper, Agency> implements AgencyService {

    @Resource
    private AgencyMapper agencyMapper;

    @Override
    public IPage pageC(IPage<Agency> page) {
        return agencyMapper.pageC(page);
    }

    @Override
    public IPage pageCC(IPage<Agency> page, Wrapper wrapper) {
        return agencyMapper.pageCC(page,wrapper);
    }
}
