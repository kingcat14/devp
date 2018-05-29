package com.yunkang.saas.common.util;

import java.util.UUID;

public class IDGenerator {

    public static String get(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }
}
