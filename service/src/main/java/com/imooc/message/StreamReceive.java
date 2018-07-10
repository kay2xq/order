package com.imooc.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * Created by XuQin on 2018/7/9.
 */
@Component
@EnableBinding(StreamClientConsumer.class)
@Slf4j
public class StreamReceive {

    @StreamListener(value = StreamClientConsumer.INPUT_MESSAGE)
    //@SendTo("")   消息消费之后的返回
    public void process(Object message){
        System.out.println("进入到StreamReceive 中 StreamListener myMessage process方法了");
        //todo 这里获得的message不是controller中传递过来的值啊？？？？？ why
        log.info("stream message {}", message);
    }
}
