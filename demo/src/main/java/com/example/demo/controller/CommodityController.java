package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.QueryPageParam;
import com.example.demo.common.Result;
import com.example.demo.entity.Commodity;
import com.example.demo.service.CommodityService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @GetMapping("/list")
    public List<Commodity> list(){
        return commodityService.list();
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Commodity commodity){
        return commodityService.save(commodity)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Commodity commodity){
        return commodityService.updateById(commodity)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return commodityService.removeById(id)?Result.suc():Result.fail();
    }

    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Commodity commodity){
        return commodityService.updateById(commodity);
    }
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Commodity commodity){
        return commodityService.saveOrUpdate(commodity);
    }
    //删除
    @GetMapping("/delete")
    public boolean delete(Integer id){
        return commodityService.removeById(id);
    }
    //查询(模糊、匹配)
    @PostMapping("/listP")
    public Result listP(@RequestBody Commodity commodity){
        LambdaQueryWrapper<Commodity> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(commodity.getName())){
            lambdaQueryWrapper.like(Commodity::getName,commodity.getName());
        }
        return Result.suc(commodityService.list(lambdaQueryWrapper));

    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportExcel() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("data");

        // 创建表头
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("id");
        headerRow.createCell(1).setCellValue("code");
        headerRow.createCell(2).setCellValue("name");
        headerRow.createCell(3).setCellValue("sp");
        headerRow.createCell(4).setCellValue("nw");
        headerRow.createCell(5).setCellValue("rw");
        headerRow.createCell(6).setCellValue("cp");

        // 填充数据
        Row row = sheet.createRow(1);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("code");
        row.createCell(2).setCellValue("name");
        row.createCell(3).setCellValue("sp");
        row.createCell(4).setCellValue("nw");
        row.createCell(5).setCellValue("rw");
        row.createCell(6).setCellValue("cp");

        // 转换为字节数组
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        byte[] bytes = outputStream.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "data.xlsx");

        return ResponseEntity.ok().headers(headers).body(bytes);
    }


    @PostMapping("/import")
    public String importExcel(@RequestParam("file") MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        List<String> data = new ArrayList<>();

        for (Row row : sheet) {
            for (Cell cell : row) {
                data.add(cell.getStringCellValue());
            }
        }
        workbook.close();

        // 打印导入的数据
        data.forEach(System.out::println);

        return "Import successful!";
    }



    @PostMapping("/listPage")
    //public List<commodity> listPage(@RequestBody HashMap map){
    public List<Commodity> listPage(@RequestBody QueryPageParam query){
        System.out.println(query);

        //System.out.println("num==="+query.getPageNum());
        //System.out.println("size==="+query.getPageSize());
        HashMap param=query.getParam();
        String name=(String)param.get("name");
        //System.out.println("name==="+(String)param.get("name"));
//        System.out.println("no==="+(String)param.get("no"));


        /*LambdaQueryWrapper<commodity> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(commodity::getName,commodity.getName());
        return commodityService.list(lambdaQueryWrapper);*/


        Page<Commodity> page=new Page(query.getPageNum(),query.getPageSize());
        LambdaQueryWrapper<Commodity> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Commodity::getName,name);
        }


        IPage result=commodityService.page(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());

        return result.getRecords();
    }
    @PostMapping("/listPageC1")
    //public List<commodity> listPage(@RequestBody HashMap map){
    public Result listPageC1(@RequestBody QueryPageParam query){
        //System.out.println(query);

        //System.out.println("num==="+query.getPageNum());
        //System.out.println("size==="+query.getPageSize());
        HashMap param=query.getParam();
        String name=(String)param.get("name");

        Page<Commodity> page=new Page(query.getPageNum(),query.getPageSize());
        LambdaQueryWrapper<Commodity> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Commodity::getName,name);
        }
        //IPage result=commodityService.pageC(page);
        IPage result=commodityService.pageCC(page,lambdaQueryWrapper);

        return Result.suc(result.getRecords(),result.getTotal());
    }
}
