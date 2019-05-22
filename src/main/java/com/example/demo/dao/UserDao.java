package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName: UserDao
 * @Description: 用户访问层接口
 * @Author wxh
 * @Date: 2019/5/22 11:15
 * @Version V1.0.0
 * @Since 1.8
 */
public interface UserDao extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User> {

}
