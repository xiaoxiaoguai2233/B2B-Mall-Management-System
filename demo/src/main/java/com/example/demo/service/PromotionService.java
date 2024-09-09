package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.Promotion;
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
public interface PromotionService extends IService<Promotion> {
    IPage pageC(IPage<Promotion> page);

    IPage pageCC(IPage<Promotion> page, Wrapper wrapper);

}
