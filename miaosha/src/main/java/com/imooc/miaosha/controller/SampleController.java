package com.imooc.miaosha.controller;


import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.rabbitmq.MQSender;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.redis.UserKey;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class SampleController {

    final
    UserService userService;
    final
    RedisService redisServer;

    @Autowired
    MQSender sender;

    public SampleController(UserService userService, RedisService redisServer) {
        this.userService = userService;
        this.redisServer = redisServer;
    }

  /*  *//**
     * Rabbitmq四种交换机模式
     * *//*
    @RequestMapping("/mq/header")
    @ResponseBody
    public Result<String> header() {
		sender.sendHeader("hello,imooc");
        return Result.success("Hello，world");
    }

	@RequestMapping("/mq/fanout")
    @ResponseBody
    public Result<String> fanout() {
		sender.sendFanout("hello,imooc");
        return Result.success("Hello，world");
    }

	@RequestMapping("/mq/topic")
    @ResponseBody
    public Result<String> topic() {
		sender.sendTopic("hello,imooc");
        return Result.success("Hello，world");
    }

    @RequestMapping("/mq")
    @ResponseBody
    public Result <String> mq(){
        sender.send("hello,imooc");
        return Result.success("hello,imooc");
    }*/


    @RequestMapping("/thymeleaf")
    public  String thymeleaf(Model model){
        model.addAttribute("name","Joshua");
        return "hello";
    }
    //1.rest api json 输出   2.页面

    @RequestMapping("/hello")
    @ResponseBody
    public Result <String> hello(){
        return Result.success("hello,imooc");
    }

    @RequestMapping("/helloError")
    @ResponseBody
    public Result <String> helloError(){
        return Result.error(CodeMsg.SERVER_ERROR);
    }


    @RequestMapping("/db/get")
    @ResponseBody
    public Result <User> dbGet() {
        User user = userService.getById(1);
        return Result.success(user);

    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
      User user= redisServer.get(UserKey.getById,""+1,User.class);
      return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user  = new User();
        user.setId(1);
        user.setName("1111");
        redisServer.set(UserKey.getById,""+1,user);
        return Result.success(true);
    }

}
