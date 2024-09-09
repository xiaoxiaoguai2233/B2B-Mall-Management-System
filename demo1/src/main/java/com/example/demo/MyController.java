package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RestController
@RequestMapping("/api")
public class MyController {
    public String result;


    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public MyController() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();

    }



    @PostMapping("/intellClick")
    public ResponseEntity<String> intellClick() {
        String apiUrl = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions_pro?access_token=24.ed43597c8389177a68bc1711030dce80.2592000.1721293967.282335-45306393";
        String content1 = "1+1=100";
        String standad = "1+1=2";
        String content = content1 +"请对这些题目进行批阅。"+"评分标准为"+standad;

        try {
            // 构建请求体
            Map<String, Object> requestBody = new HashMap<>();
            Map<String, String> messageContent = new HashMap<>();
            messageContent.put("role","user");
            messageContent.put("content", content);
            requestBody.put("messages", new Map[]{messageContent});

            // 将请求体转换为 JSON 字符串
            String jsonBody = objectMapper.writeValueAsString(requestBody);
            // 设置请求头
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");

            // 创建请求实例
            org.springframework.http.HttpEntity<String> requestEntity = new org.springframework.http.HttpEntity<>(jsonBody, headers);
            // 发送 POST 请求
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, String.class);


            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                // 处理服务器的响应结果

                return ResponseEntity.ok(responseEntity.getBody());

            } else {
                return ResponseEntity.status(responseEntity.getStatusCode()).body("请求失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("内部服务器错误");
        }
    }

}


