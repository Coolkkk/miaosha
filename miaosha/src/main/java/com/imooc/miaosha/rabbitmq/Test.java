package com.imooc.miaosha.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.imooc.miaosha.domain.Stu;
import com.imooc.miaosha.redis.RedisService;

public class Test {
    public static void main(String[] args) {
        Stu stu = new Stu("zhangsan",12);
        System.out.println(RedisService.beanToString(stu));
//        String s="{name:'zhangsan', age:12}";
//        Stu test = JSON.toJavaObject(JSON.parseObject(s), Stu.class);
//        System.out.println(test.getAge());
    }
}
