package com.imooc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by XuQin on 2018/7/6.
 */
@RestController
@Slf4j
@RequestMapping("/template")
public class ClientControllerByTemplate {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

//    @Autowired
//    private RestTemplate restTemplate;

    @GetMapping("/getProductMsg")
    public String getProductMsg(){

        // 方法一(直接使用RestTemplate， url写死)
//        RestTemplate restTemplate = new RestTemplate();
//        String msg = restTemplate.getForObject("http://localhost:8080/msg", String.class);

        //方法二(利用loadBalancerClient，通过应用名获取url， 然后再使用restTemplate)
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance serviceInstance = loadBalancerClient.choose("product");
        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort() + "/msg");
        String msg = restTemplate.getForObject(url, String.class);

        //方法三 @LoadBalanced注解
//        String msg = restTemplate.getForObject("http://product/msg", String.class);

        log.info("response={}", msg);
        return msg;
    }
}
