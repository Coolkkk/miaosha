package com.imooc.miaosha.util;

import java.util.UUID;

public class UUIDUitl {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
