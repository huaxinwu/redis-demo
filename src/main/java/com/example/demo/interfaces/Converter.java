package com.example.demo.interfaces;

/**
 * @ClassName: ConvertDTO
 * @Description: 数据传输对象转换类
 * @Author wxh
 * @Date: 2019/5/22 15:27
 * @Version V1.0.0
 * @Since 1.8
 */
public interface Converter<S,T> {
    /**
     *  将一个类转换成另一个类(正向转换)
     * @param s 源类
     * @return T 目标类
     */
    T doForward(S s);

    /**
     *  将一个类转换成另一个类(反向转换)
     * @param t 源类
     * @return s 目标类
     */
    S doBackward(T t);

}
