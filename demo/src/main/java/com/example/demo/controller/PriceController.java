package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.QueryPageParam;
import com.example.demo.common.Result;
import com.example.demo.entity.Price;
import com.example.demo.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author demo
 * @since 2023-06-09
 */
@RestController
@RequestMapping("/price")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/list")
    public List<Price> list(){
        return priceService.list();
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Price price){
        return priceService.save(price)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Price price){
        return priceService.updateById(price)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return priceService.removeById(id)?Result.suc():Result.fail();
    }

    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Price price){
        return priceService.updateById(price);
    }
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Price price){
        return priceService.saveOrUpdate(price);
    }
    //删除
    @GetMapping("/delete")
    public boolean delete(Integer id){
        return priceService.removeById(id);
    }
    //查询(模糊、匹配)
    @PostMapping("/listP")
    public Result listP(@RequestBody Price price){
        LambdaQueryWrapper<Price> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(price.getCode())){
            lambdaQueryWrapper.like(Price::getCode,price.getCode());
        }
        return Result.suc(priceService.list(lambdaQueryWrapper));

    }

    @PostMapping("/listPage")
    //public List<price> listPage(@RequestBody HashMap map){
    public List<Price> listPage(@RequestBody QueryPageParam query){
        System.out.println(query);

        //System.out.println("num==="+query.getPageNum());
        //System.out.println("size==="+query.getPageSize());
        HashMap param=query.getParam();
        String code=(String)param.get("code");
        //System.out.println("name==="+(String)param.get("name"));
//        System.out.println("no==="+(String)param.get("no"));


        /*LambdaQueryWrapper<price> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(price::getName,price.getName());
        return priceService.list(lambdaQueryWrapper);*/


        Page<Price> page=new Page(query.getPageNum(),query.getPageSize());
        LambdaQueryWrapper<Price> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(code) && !"null".equals(code)){
            lambdaQueryWrapper.like(Price::getCode,code);
        }


        IPage result=priceService.page(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());

        return result.getRecords();
    }
    @PostMapping("/listPageC1")
    //public List<price> listPage(@RequestBody HashMap map){
    public Result listPageC1(@RequestBody QueryPageParam query){
        //System.out.println(query);

        //System.out.println("num==="+query.getPageNum());
        //System.out.println("size==="+query.getPageSize());
        HashMap param=query.getParam();
        String code=(String)param.get("code");

        Page<Price> page=new Page(query.getPageNum(),query.getPageSize());
        LambdaQueryWrapper<Price> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(code) && !"null".equals(code)){
            lambdaQueryWrapper.like(Price::getCode,code);
        }
        //IPage result=priceService.pageC(page);
        IPage result=priceService.pageCC(page,lambdaQueryWrapper);

        return Result.suc(result.getRecords(),result.getTotal());
    }
}
