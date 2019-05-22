package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName: UserServiceImpl
 * @Description: 用户服务实现类
 * @Author wxh
 * @Date: 2019/5/22 11:20
 * @Version V1.0.0
 * @Since 1.8
 */
@Service("userService")
@CacheConfig(cacheNames = "userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    @Cacheable(value = "listUser")
    public List<User> listUser() {
        return userDao.findAll();
    }

    /**
     *  执行该函数时，将清除以 userService 的缓存 【cacheNames = "userService"】<br>
     *  也可指定清除的key 【@CacheEvict(value="abc")】
     */
    @Override
    @CacheEvict(value = "listUser")
    public void clearAllUserCache() {

    }

    /**
     *  key ="#p0" 表示以第1个参数作为 key
     * @param pId 参数ID
     * @return User 用户对象
     */
    @Override
    @Cacheable(value="user", key ="#p0")
    public User findById(Integer pId) {
        Optional<User> user = userDao.findById(pId);
        return Optional.ofNullable(user).get().orElse(null);
    }

    /**
     * key ="#p0" 表示以第1个参数作为 key
     * 清除第一个参数作为key的缓存
     * @param pId 参数ID
     */
    @Override
    @CacheEvict(value="user", key ="#p0")
    public void clear(Integer pId) {

    }

    @Override
    public void insert(User user) {
        userDao.save(user);
    }
}
