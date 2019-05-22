package com.example.demo.dto;

import com.example.demo.entity.User;
import com.example.demo.interfaces.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: UserDTO
 * @Description: 用户数据传输对象
 * @Author wxh
 * @Date: 2019/5/22 16:17
 * @Version V1.0.0
 * @Since 1.8
 */
@Data
public class UserDTO implements Serializable {

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

    public User convertToUser(){
        UserConvertDTO userConvertDTO = new UserConvertDTO();
        User convert = userConvertDTO.doForward(this);
        return convert;
    }

    public UserDTO convertToUserDTO(User user){
        UserConvertDTO userConvertDTO = new UserConvertDTO();
        UserDTO convert = userConvertDTO.doBackward(user);
        return convert;
    }

    private static class UserConvertDTO implements Converter<UserDTO, User> {
        @Override
        public User doForward(UserDTO userDTO) {
            User user = new User();
            BeanUtils.copyProperties(userDTO,user);
            return user;
        }

        @Override
        public UserDTO doBackward(User user) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user,userDTO);
            return userDTO;
        }

        @Override
        public String toString() {
            return "UserConvertDTO{}";
        }
    }
}
