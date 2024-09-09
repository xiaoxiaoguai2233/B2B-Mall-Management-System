package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.Price;
import com.example.demo.entity.Promotion;
import com.example.demo.mapper.PriceMapper;
import com.example.demo.mapper.PromotionMapper;
import com.example.demo.service.PromotionService;
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
public class PromotionServiceImpl extends ServiceImpl<PromotionMapper, Promotion> implements PromotionService {
    @Resource
    private PromotionMapper promotionMapper;

    @Override
    public IPage pageC(IPage<Promotion> page) {
        return promotionMapper.pageC(page);
    }

    @Override
    public IPage pageCC(IPage<Promotion> page, Wrapper wrapper) {
        return promotionMapper.pageCC(page,wrapper);
    }

}
