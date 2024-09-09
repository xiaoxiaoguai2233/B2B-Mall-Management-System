package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private final MyController myController;

    @Autowired
    public ApplicationRunner(MyController myController) {
        this.myController = myController;
    }

    @Override
    public void run(String... args) throws Exception {
        // 自动调用 intellClickInternal 方法
        ResponseEntity<String> response = myController.intellClick();
        // 打印结果到控制台
        System.out.println("Response: " + processResponse(response.getBody()));
    }

    public String processResponse(String responseBody) {
        try {
            // 将响应字符串解析为 JSON 对象
            JsonNode jsonNode = new ObjectMapper().readTree(responseBody);
            // 提取 "result" 字段
            String result = jsonNode.path("result").asText();
            System.out.println(result);
            // 返回提取的结果
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "处理响应时出错";
        }
    }
}
