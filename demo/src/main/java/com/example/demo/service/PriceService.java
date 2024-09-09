package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.Price;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author demo
 * @since 2023-06-09
 */
public interface PriceService extends IService<Price> {
    IPage pageC(IPage<Price> page);

    IPage pageCC(IPage<Price> page, Wrapper wrapper);

}
