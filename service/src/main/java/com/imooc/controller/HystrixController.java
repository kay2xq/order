package com.imooc.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;

/**
 * Created by XuQin on 2018/7/13.
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    //错误返回方法
    //@HystrixCommand(fallbackMethod = "fallback")

    //超时配置
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "40")
//    })
    @HystrixCommand
    @GetMapping("/getProductInfo")
    public String getProductInfo() {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject("http://192.168.56.1:8082/service/product/findByProductIdIn", Arrays.asList("1"), String.class);
        return result;

        //throw new RuntimeException();
    }


    // todo 熔断现象未成功呈现
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "40")
//    }
//    @GetMapping("/getProductInfo")
//    public String getProductInfo(@RequestParam("number") Integer number) {
//        if (number % 2 == 0){
//            return "success~~~~~~~~~~~~";
//        }
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.postForObject("http://192.168.56.1:8082/service/product/findByProductIdIn", Arrays.asList("1"), String.class);
//        return result;
//    }

    public String fallback() {
        return "请求太多，请稍后再试~~~~~~~~~";
    }

    public String defaultFallback() {
        return "默认回复：请求太多，请稍后再试~~~~~~~~~";
    }
}