package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: IndexController
 * @Description: 首页控制器
 * @Author wxh
 * @Date: 2019/5/10 16:26
 * @Version V1.0.0
 * @Since 1.8
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     *  进入首页
     * @return String
     */
    @GetMapping("/")
    public String home(){
        return "Hello Spring Boot";
    }

    /**
     *  添加(String)
     */
    @GetMapping("/insertRedis")
    public void insertRedis(){
        stringRedisTemplate.opsForValue().set("a", "test");
    }

    /**
     * 获取(String)
     * @return String 字符串
     */
    @GetMapping("/getRedis")
    public String getRedis(){
        return stringRedisTemplate.opsForValue().get("a");
    }
}
