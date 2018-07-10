package com.imooc.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by XuQin on 2018/7/9.
 */
@Component
@Slf4j
public class MyReceive {

    @RabbitListener(queuesToDeclare = @Queue("myquene"))
    public void getMessage(String message){
        log.info("my Receive Message {}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("computerOrder")
    ))
    public void computerOrder(String message){
        log.info("computer Order Info {}", message);
    }


    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "fruit",
            value = @Queue("fruitOrder")
    ))
    public void fruitOrder(String message){
        log.info("fruit OrderInfo {}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "vegetable",
            value = @Queue("vegetableOrder")
    ))
    public void VegetableOrder(String message){
        log.info("vegetable Info {}", message);
    }
}
