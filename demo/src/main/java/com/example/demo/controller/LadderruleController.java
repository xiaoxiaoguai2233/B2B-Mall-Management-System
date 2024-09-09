package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.QueryPageParam;
import com.example.demo.common.Result;
import com.example.demo.entity.Ladderrule;
import com.example.demo.service.LadderruleService;
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
@RequestMapping("/ladderrule")
public class LadderruleController {


    @Autowired
    private LadderruleService ladderruleService;

    @GetMapping("/list")
    public List<Ladderrule> list(){
        return ladderruleService.list();
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Ladderrule ladderrule){
        return ladderruleService.save(ladderrule)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Ladderrule ladderrule){
        return ladderruleService.updateById(ladderrule)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return ladderruleService.removeById(id)?Result.suc():Result.fail();
    }

    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Ladderrule ladderrule){
        return ladderruleService.updateById(ladderrule);
    }
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Ladderrule ladderrule){
        return ladderruleService.saveOrUpdate(ladderrule);
    }
    //删除
    @GetMapping("/delete")
    public boolean delete(Integer id){
        return ladderruleService.removeById(id);
    }
    //查询(模糊、匹配)
    @PostMapping("/listP")
    public Result listP(@RequestBody Ladderrule ladderrule){
        LambdaQueryWrapper<Ladderrule> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(ladderrule.getCode())){
            lambdaQueryWrapper.like(Ladderrule::getCode,ladderrule.getCode());
        }
        return Result.suc(ladderruleService.list(lambdaQueryWrapper));

    }

    @PostMapping("/listPage")
    //public List<ladderrule> listPage(@RequestBody HashMap map){
    public List<Ladderrule> listPage(@RequestBody QueryPageParam query){
        System.out.println(query);

        //System.out.println("num==="+query.getPageNum());
        //System.out.println("size==="+query.getPageSize());
        HashMap param=query.getParam();
        String code=(String)param.get("code");
        //System.out.println("name==="+(String)param.get("name"));
//        System.out.println("no==="+(String)param.get("no"));


        /*LambdaQueryWrapper<ladderrule> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(ladderrule::getName,ladderrule.getName());
        return ladderruleService.list(lambdaQueryWrapper);*/


        Page<Ladderrule> page=new Page(query.getPageNum(),query.getPageSize());
        LambdaQueryWrapper<Ladderrule> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(code) && !"null".equals(code)){
            lambdaQueryWrapper.like(Ladderrule::getCode,code);
        }


        IPage result=ladderruleService.page(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());

        return result.getRecords();
    }
    @PostMapping("/listPageC1")
    //public List<ladderrule> listPage(@RequestBody HashMap map){
    public Result listPageC1(@RequestBody QueryPageParam query){
        //System.out.println(query);

        //System.out.println("num==="+query.getPageNum());
        //System.out.println("size==="+query.getPageSize());
        HashMap param=query.getParam();
        String code=(String)param.get("code");

        Page<Ladderrule> page=new Page(query.getPageNum(),query.getPageSize());
        LambdaQueryWrapper<Ladderrule> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(code) && !"null".equals(code)){
            lambdaQueryWrapper.like(Ladderrule::getCode,code);
        }
        //IPage result=ladderruleService.pageC(page);
        IPage result=ladderruleService.pageCC(page,lambdaQueryWrapper);

        return Result.suc(result.getRecords(),result.getTotal());
    }
}
