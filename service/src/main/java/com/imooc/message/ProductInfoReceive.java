package com.imooc.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.imooc.product.ProductInfoOutput;
import com.imooc.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by XuQin on 2018/7/10.
 */
@Component
@Slf4j
public class ProductInfoReceive {

    private final static String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("product_stock"))
    public void process(String message){
        List<ProductInfoOutput> result = (List<ProductInfoOutput>)JsonUtils.JsonToObject(message,
                new TypeReference<List<ProductInfoOutput>>() {});

        log.info("从队列中获取消息: {}", result);

        //将库存信息存储到redis中
        for (ProductInfoOutput p: result){
            redisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, p.getProductId()),
                    String.valueOf(p.getProductStock()));
        }

    }
}
