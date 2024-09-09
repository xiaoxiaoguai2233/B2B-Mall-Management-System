package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.Agency;
import com.example.demo.entity.Commodity;
import com.example.demo.mapper.AgencyMapper;
import com.example.demo.mapper.CommodityMapper;
import com.example.demo.service.CommodityService;
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
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements CommodityService {

    @Resource
    private CommodityMapper commodityMapper;

    @Override
    public IPage pageC(IPage<Commodity> page) {
        return commodityMapper.pageC(page);
    }

    @Override
    public IPage pageCC(IPage<Commodity> page, Wrapper wrapper) {
        return commodityMapper.pageCC(page,wrapper);
    }
}
