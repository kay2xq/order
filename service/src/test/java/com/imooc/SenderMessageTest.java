package com.imooc;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by XuQin on 2018/7/9.
 */
@Component
public class SenderMessageTest extends OrderApplicationTests{

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void sendMessage(){
        amqpTemplate.convertAndSend("myquene", "now " + new Date());
    }

    @Test
    public void sentOrder(){
        amqpTemplate.convertAndSend("myOrder", "computer", "now " + new Date());
        amqpTemplate.convertAndSend("myOrder", "fruit", "now " + new Date());
        amqpTemplate.convertAndSend("myOrder", "vegetable", "now " + new Date());
    }
}
