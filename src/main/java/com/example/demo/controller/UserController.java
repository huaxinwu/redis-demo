package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

/**
 * @ClassName: UserController
 * @Description: 用户控制器
 * @Author wxh
 * @Date: 2019/5/22 11:22
 * @Version V1.0.0
 * @Since 1.8
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 查询所有用户列表信息
     * @return List<User> 用户集合
     */
    @GetMapping("/list")
    public List<User> listUser(){
        System.out.println("只有第一次才会打印sql语句");
        return userService.listUser();
    }

    /**
     * 新增用户信息
     */
    @GetMapping("/insert")
    public void insertUser(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("王五");
        userDTO.setCreateTime(Calendar.getInstance().getTime());
        User user = userDTO.convertToUser();
        userService.insert(user);
    }

    /**
     * 根据用户ID查询用户信息
     * @param userId 用户ID
     * @return User 用户对象
     */
    @GetMapping("/get")
    public User getUser(@RequestParam("userId") Integer userId){
        return userService.findById(userId);
    }

    /**
     *  清除所有缓存
     */
    @GetMapping("/clear")
    public void clear(){
        userService.clearAllUserCache();
    }

    /**
     * 根据用户ID清除第一个缓存
     * @param userId 用户ID
     */
    @GetMapping("/clearOne")
    public void clear(@RequestParam(name = "userId")Integer userId) {
        userService.clear(userId);
    }

}
