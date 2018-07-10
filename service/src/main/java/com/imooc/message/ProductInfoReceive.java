package com.imooc.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.imooc.product.ProductInfoOutput;
import com.imooc.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by XuQin on 2018/7/10.
 */
@Component
@Slf4j
public class ProductInfoReceive {

    @RabbitListener(queuesToDeclare = @Queue("product_stock"))
    public void process(String message){
        List<ProductInfoOutput> result = (List<ProductInfoOutput>)JsonUtils.JsonToObject(message,
                new TypeReference<List<ProductInfoOutput>>() {});
        log.info("从队列中获取消息: {}", result);

    }
}
