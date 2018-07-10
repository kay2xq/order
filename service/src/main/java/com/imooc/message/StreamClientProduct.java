package com.imooc.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by XuQin on 2018/7/9.
 */
public interface StreamClientProduct {

    String OUTPUT_MESSAGE = "Message";

    @Output(OUTPUT_MESSAGE)
    MessageChannel output();

}
