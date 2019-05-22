package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: User
 * @Description: 用户实体类
 * @Author wxh
 * @Date: 2019/5/22 11:05
 * @Version V1.0.0
 * @Since 1.8
 */
@Table(name = "user")
@Entity
@Data
public class User implements Serializable {

    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",nullable = false)
    private Integer userId;

    /**
     *  用户姓名
     */
    @Column(name = "user_name",length = 20)
    private String userName;

    /**
     *  创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
}
