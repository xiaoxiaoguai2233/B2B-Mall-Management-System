package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.QueryPageParam;
import com.example.demo.common.Result;
import com.example.demo.entity.Sell;
import com.example.demo.service.SellService;
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
 * @since 2023-06-11
 */
@RestController
@RequestMapping("/sell")
public class SellController {

    @Autowired
    private SellService sellService;

    @GetMapping("/list")
    public List<Sell> list(){
        return sellService.list();
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Sell sell){
        return sellService.save(sell)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Sell sell){
        return sellService.updateById(sell)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return sellService.removeById(id)?Result.suc():Result.fail();
    }

    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Sell sell){
        return sellService.updateById(sell);
    }
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Sell sell){
        return sellService.saveOrUpdate(sell);
    }
    //删除
    @GetMapping("/delete")
    public boolean delete(Integer id){
        return sellService.removeById(id);
    }
    //查询(模糊、匹配)
    @PostMapping("/listP")
    public Result listP(@RequestBody Sell sell){
        LambdaQueryWrapper<Sell> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(sell.getName())){
            lambdaQueryWrapper.like(Sell::getName,sell.getName());
        }
        return Result.suc(sellService.list(lambdaQueryWrapper));

    }

    @PostMapping("/listPage")
    //public List<sell> listPage(@RequestBody HashMap map){
    public List<Sell> listPage(@RequestBody QueryPageParam query){
        System.out.println(query);

        //System.out.println("num==="+query.getPageNum());
        //System.out.println("size==="+query.getPageSize());
        HashMap param=query.getParam();
        String name=(String)param.get("id");
        //System.out.println("name==="+(String)param.get("name"));
//        System.out.println("no==="+(String)param.get("no"));


        /*LambdaQueryWrapper<sell> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(sell::getName,sell.getName());
        return sellService.list(lambdaQueryWrapper);*/


        Page<Sell> page=new Page(query.getPageNum(),query.getPageSize());
        LambdaQueryWrapper<Sell> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Sell::getName,name);
        }


        IPage result=sellService.page(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());

        return result.getRecords();
    }
    @PostMapping("/listPageC1")
    //public List<sell> listPage(@RequestBody HashMap map){
    public Result listPageC1(@RequestBody QueryPageParam query){
        //System.out.println(query);

        //System.out.println("num==="+query.getPageNum());
        //System.out.println("size==="+query.getPageSize());
        HashMap param=query.getParam();
        String id=(String)param.get("id");

        Page<Sell> page=new Page(query.getPageNum(),query.getPageSize());
        LambdaQueryWrapper<Sell> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(id) && !"null".equals(id)){
            lambdaQueryWrapper.like(Sell::getCode,id);
        }
        //IPage result=sellService.pageC(page);
        IPage result=sellService.pageCC(page,lambdaQueryWrapper);
        return Result.suc(result.getRecords(),result.getTotal());
    }
}
