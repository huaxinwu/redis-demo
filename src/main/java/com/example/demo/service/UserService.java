package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

/**
 * @ClassName: UserService
 * @Description: 用户服务接口
 * @Author wxh
 * @Date: 2019/5/22 11:18
 * @Version V1.0.0
 * @Since 1.8
 */
public interface UserService {
    /**
     * 查询所有用户信息
     * @return List<User> 用户集合
     */
    List<User> listUser();

    /**
     *  清除所有缓存
     */
    void clearAllUserCache();

    /**
     *  根据参数ID查询用户信息
     * @param pId 参数ID
     * @return User 用户对象
     */
    User findById(Integer pId);

    /**
     * 根据参数ID清除用户信息
     * @param pId 参数ID
     */
    void clear(Integer pId);

    /**
     *  新增用户信息
     * @param user 用户对象
     */
    void insert(User user);
}
