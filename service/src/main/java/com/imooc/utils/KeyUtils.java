package com.imooc.utils;

import java.util.Random;

/**
 * Created by XuQin on 2018/7/5.
 */
public class KeyUtils {

    public static synchronized String getUniqueId(String table){
        Random random = new Random();
        int number = random.nextInt(900000);
        return table + number + String.valueOf(System.currentTimeMillis());
    }
}
