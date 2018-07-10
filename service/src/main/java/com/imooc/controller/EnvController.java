package com.imooc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by XuQin on 2018/7/8.
 */
@RestController
@RequestMapping("/env")
public class EnvController {

    @Value("${env}")
    private String env;

    @RequestMapping("/currentEnv")
    public String getEnv(){
        return env;
    }
}
