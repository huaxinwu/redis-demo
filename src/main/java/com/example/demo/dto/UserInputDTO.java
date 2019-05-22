package com.example.demo.dto;

import com.example.demo.entity.User;
import com.example.demo.interfaces.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: UserInputDTO
 * @Description:
 * @Author wxh
 * @Date: 2019/5/22 15:33
 * @Version V1.0.0
 * @Since 1.8
 */
@Data
public class UserInputDTO implements Serializable {
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     *  用户姓名
     */
    private String userName;
    /**
     *  创建时间
     */
    private Date createTime;

    /**
     * 转换为用户对象
     * @return User 用户对象
     */
    public User converToUser(){
        UserInputConvertDTO userInputDTOConvert = new UserInputConvertDTO();
        User convert = userInputDTOConvert.doForward(this);
        return convert;
    }

    /**
     *  内部私有化类
     */
    private static class UserInputConvertDTO implements Converter<UserInputDTO,User> {
        @Override
        public User doForward(UserInputDTO userInputDTO) {
            User user = new User();
            BeanUtils.copyProperties(userInputDTO,user);
            return user;
        }

        @Override
        public UserInputDTO doBackward(User user) {
            UserInputDTO userInputDTO = new UserInputDTO();
            BeanUtils.copyProperties(user,userInputDTO);
            return userInputDTO;
        }

        @Override
        public String toString() {
            return "UserInputConvertDTO{}";
        }
    }
}
