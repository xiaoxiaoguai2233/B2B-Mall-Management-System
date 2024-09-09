package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.QueryPageParam;
import com.example.demo.common.Result;
import com.example.demo.entity.Agency;
import com.example.demo.entity.User;
import com.example.demo.service.AgencyService;
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
@RequestMapping("/agency")
public class AgencyController {
    @Autowired
    private AgencyService agencyService;

    @GetMapping("/list")
    public List<Agency> list(){
        return agencyService.list();
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Agency agency){
        return agencyService.save(agency)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Agency agency){
        return agencyService.updateById(agency)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return agencyService.removeById(id)?Result.suc():Result.fail();
    }

    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Agency agency){
        return agencyService.updateById(agency);
    }
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Agency agency){
        return agencyService.saveOrUpdate(agency);
    }
    //删除
    @GetMapping("/delete")
    public boolean delete(Integer id){
        return agencyService.removeById(id);
    }
    //查询(模糊、匹配)
    @PostMapping("/listP")
    public Result listP(@RequestBody Agency agency){
        LambdaQueryWrapper<Agency> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(agency.getName())){
            lambdaQueryWrapper.like(Agency::getName,agency.getName());
        }
        return Result.suc(agencyService.list(lambdaQueryWrapper));

    }

    @PostMapping("/listPage")
    //public List<agency> listPage(@RequestBody HashMap map){
    public List<Agency> listPage(@RequestBody QueryPageParam query){
        System.out.println(query);

        //System.out.println("num==="+query.getPageNum());
        //System.out.println("size==="+query.getPageSize());
        HashMap param=query.getParam();
        String name=(String)param.get("name");
        //System.out.println("name==="+(String)param.get("name"));
//        System.out.println("no==="+(String)param.get("no"));


        /*LambdaQueryWrapper<agency> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(agency::getName,agency.getName());
        return agencyService.list(lambdaQueryWrapper);*/


        Page<Agency> page=new Page(query.getPageNum(),query.getPageSize());
        LambdaQueryWrapper<Agency> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Agency::getName,name);
        }


        IPage result=agencyService.page(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());

        return result.getRecords();
    }
    @PostMapping("/listPageC1")
    //public List<agency> listPage(@RequestBody HashMap map){
    public Result listPageC1(@RequestBody QueryPageParam query){
        //System.out.println(query);

        //System.out.println("num==="+query.getPageNum());
        //System.out.println("size==="+query.getPageSize());
        HashMap param=query.getParam();
        String name=(String)param.get("name");

        Page<Agency> page=new Page(query.getPageNum(),query.getPageSize());
        LambdaQueryWrapper<Agency> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Agency::getName,name);
        }
        //IPage result=agencyService.pageC(page);
        IPage result=agencyService.pageCC(page,lambdaQueryWrapper);

        return Result.suc(result.getRecords(),result.getTotal());
    }

}
