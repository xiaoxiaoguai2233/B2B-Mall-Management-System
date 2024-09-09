package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.QueryPageParam;
import com.example.demo.common.Result;
import com.example.demo.entity.Promotion;
import com.example.demo.service.PromotionService;
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
 * @since 2023-06-10
 */
@RestController
@RequestMapping("/promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping("/list")
    public List<Promotion> list(){
        return promotionService.list();
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Promotion promotion){
        return promotionService.save(promotion)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Promotion promotion){
        return promotionService.updateById(promotion)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return promotionService.removeById(id)?Result.suc():Result.fail();
    }

    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Promotion promotion){
        return promotionService.updateById(promotion);
    }
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Promotion promotion){
        return promotionService.saveOrUpdate(promotion);
    }
    //删除
    @GetMapping("/delete")
    public boolean delete(Integer id){
        return promotionService.removeById(id);
    }
    //查询(模糊、匹配)
    @PostMapping("/listP")
    public Result listP(@RequestBody Promotion promotion){
        LambdaQueryWrapper<Promotion> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(promotion.getName())){
            lambdaQueryWrapper.like(Promotion::getName,promotion.getName());
        }
        return Result.suc(promotionService.list(lambdaQueryWrapper));

    }

    @PostMapping("/listPage")
    //public List<promotion> listPage(@RequestBody HashMap map){
    public List<Promotion> listPage(@RequestBody QueryPageParam query){
        System.out.println(query);

        //System.out.println("num==="+query.getPageNum());
        //System.out.println("size==="+query.getPageSize());
        HashMap param=query.getParam();
        String name=(String)param.get("name");
        //System.out.println("name==="+(String)param.get("name"));
//        System.out.println("no==="+(String)param.get("no"));


        /*LambdaQueryWrapper<promotion> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(promotion::getName,promotion.getName());
        return promotionService.list(lambdaQueryWrapper);*/


        Page<Promotion> page=new Page(query.getPageNum(),query.getPageSize());
        LambdaQueryWrapper<Promotion> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Promotion::getName,name);
        }


        IPage result=promotionService.page(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());

        return result.getRecords();
    }
    @PostMapping("/listPageC1")
    //public List<promotion> listPage(@RequestBody HashMap map){
    public Result listPageC1(@RequestBody QueryPageParam query){
        //System.out.println(query);

        //System.out.println("num==="+query.getPageNum());
        //System.out.println("size==="+query.getPageSize());
        HashMap param=query.getParam();
        String name=(String)param.get("name");

        Page<Promotion> page=new Page(query.getPageNum(),query.getPageSize());
        LambdaQueryWrapper<Promotion> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Promotion::getName,name);
        }
        //IPage result=promotionService.pageC(page);
        IPage result=promotionService.pageCC(page,lambdaQueryWrapper);

        return Result.suc(result.getRecords(),result.getTotal());
    }
}
