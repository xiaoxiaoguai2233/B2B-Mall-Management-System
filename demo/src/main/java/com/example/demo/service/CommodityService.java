package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.Agency;
import com.example.demo.entity.Commodity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author demo
 * @since 2023-06-09
 */
public interface CommodityService extends IService<Commodity> {

    IPage pageC(IPage<Commodity> page);

    IPage pageCC(IPage<Commodity> page, Wrapper wrapper);
}
