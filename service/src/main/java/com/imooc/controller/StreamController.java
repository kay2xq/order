package com.imooc.controller;

import com.imooc.message.StreamClientProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by XuQin on 2018/7/9.
 */
@RestController
@Slf4j
@RequestMapping("/stream")
//@EnableBinding(StreamClientProduct.class)
public class StreamController {

//    @Autowired
//    private StreamClientProduct streamClient;

    /*@GetMapping("/sendMessage")
    public void sendMessage(){
        String message = "now : " + new Date();
        streamClient.output().send(MessageBuilder.withPayload(message).build());
    }*/
}
