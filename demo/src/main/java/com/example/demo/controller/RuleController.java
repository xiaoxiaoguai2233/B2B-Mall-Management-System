package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.QueryPageParam;
import com.example.demo.common.Result;
import com.example.demo.entity.Rule;
import com.example.demo.service.RuleService;
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
@RequestMapping("/rule")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @GetMapping("/list")
    public List<Rule> list(){
        return ruleService.list();
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Rule rule){
        return ruleService.save(rule)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Rule rule){
        return ruleService.updateById(rule)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return ruleService.removeById(id)?Result.suc():Result.fail();
    }

    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Rule rule){
        return ruleService.updateById(rule);
    }
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Rule rule){
        return ruleService.saveOrUpdate(rule);
    }
    //删除
    @GetMapping("/delete")
    public boolean delete(Integer id){
        return ruleService.removeById(id);
    }
    //查询(模糊、匹配)
    @PostMapping("/listP")
    public Result listP(@RequestBody Rule rule){
        LambdaQueryWrapper<Rule> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(rule.getCode())){
            lambdaQueryWrapper.like(Rule::getCode,rule.getCode());
        }
        return Result.suc(ruleService.list(lambdaQueryWrapper));

    }

    @PostMapping("/listPage")
    //public List<rule> listPage(@RequestBody HashMap map){
    public List<Rule> listPage(@RequestBody QueryPageParam query){
        System.out.println(query);

        //System.out.println("num==="+query.getPageNum());
        //System.out.println("size==="+query.getPageSize());
        HashMap param=query.getParam();
        String code=(String)param.get("code");
        //System.out.println("name==="+(String)param.get("name"));
//        System.out.println("no==="+(String)param.get("no"));


        /*LambdaQueryWrapper<rule> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(rule::getName,rule.getName());
        return ruleService.list(lambdaQueryWrapper);*/


        Page<Rule> page=new Page(query.getPageNum(),query.getPageSize());
        LambdaQueryWrapper<Rule> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(code) && !"null".equals(code)){
            lambdaQueryWrapper.like(Rule::getCode,code);
        }


        IPage result=ruleService.page(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());

        return result.getRecords();
    }
    @PostMapping("/listPageC1")
    //public List<rule> listPage(@RequestBody HashMap map){
    public Result listPageC1(@RequestBody QueryPageParam query){
        //System.out.println(query);

        //System.out.println("num==="+query.getPageNum());
        //System.out.println("size==="+query.getPageSize());
        HashMap param=query.getParam();
        String code=(String)param.get("code");

        Page<Rule> page=new Page(query.getPageNum(),query.getPageSize());
        LambdaQueryWrapper<Rule> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(code) && !"null".equals(code)){
            lambdaQueryWrapper.like(Rule::getCode,code);
        }
        //IPage result=ruleService.pageC(page);
        IPage result=ruleService.pageCC(page,lambdaQueryWrapper);

        return Result.suc(result.getRecords(),result.getTotal());
    }
}
