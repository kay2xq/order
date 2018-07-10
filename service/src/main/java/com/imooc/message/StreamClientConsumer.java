package com.imooc.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by XuQin on 2018/7/10.
 */
public interface StreamClientConsumer {

    String INPUT_MESSAGE = "Message";

    @Input(INPUT_MESSAGE)
    SubscribableChannel input();

}
