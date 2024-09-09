package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.Commodity;
import com.example.demo.entity.Price;
import com.example.demo.mapper.CommodityMapper;
import com.example.demo.mapper.PriceMapper;
import com.example.demo.service.PriceService;
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
public class PriceServiceImpl extends ServiceImpl<PriceMapper, Price> implements PriceService {
    @Resource
    private PriceMapper priceMapper;

    @Override
    public IPage pageC(IPage<Price> page) {
        return priceMapper.pageC(page);
    }

    @Override
    public IPage pageCC(IPage<Price> page, Wrapper wrapper) {
        return priceMapper.pageCC(page,wrapper);
    }
}
