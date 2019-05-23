package com.example.demo.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: DateUtils
 * @Description: 时间工具类
 * @Author wxh
 * @Date: 2019/5/22 16:29
 * @Version V1.0.0
 * @Since 1.8
 */
public class DateUtils {

    private DateUtils(){

    }

    /**
     *  需求：
     *  项目开发阶段，有一个关于下单发货的需求：
     *  如果今天下午 3 点前进行下单，那么发货时间是明天，
     *  如果今天下午 3 点后进行下单，那么发货时间是后天，
     *  如果被确定的时间是周日，那么在此时间上再加 1 天为发货时间。
     */

    /**
     *  定义一个下午3点的时间对象
     */
    static final  DateTime DISTRIBUTION_TIME_SPLIT_TIME = new DateTime().withTime(15,0,0,0);

    /**
     * 根据下单时间计算分布时间
     * @param orderCreateTime 下单时间
     * @return Date 时间对象
     */
    public static  Date calculateDistributionTimeByOrderCreateTime(Date orderCreateTime){
        DateTime orderCreateDateTime = new DateTime(orderCreateTime);
        Date tomorrow = orderCreateDateTime.plusDays(1).toDate();
        Date theDayAfterTomorrow = orderCreateDateTime.plusDays(2).toDate();
        return orderCreateDateTime.isAfter(DISTRIBUTION_TIME_SPLIT_TIME) ? wrapDistributionTime(theDayAfterTomorrow) : wrapDistributionTime(tomorrow);
    }

    /**
     * 封装分布时间
     * @param distributionTime 分布时间
     * @return Date 时间对象
     */
    public static  Date wrapDistributionTime(Date distributionTime){
        DateTime currentDistributionDateTime = new DateTime(distributionTime);
        DateTime plusOneDay = currentDistributionDateTime.plusDays(1);
        boolean isSunday = (DateTimeConstants.SUNDAY == currentDistributionDateTime.getDayOfWeek());
        return isSunday ? plusOneDay.toDate() : currentDistributionDateTime.toDate() ;
    }

    public static void main(String[] args) {
        DateUtils.calculateDistributionTimeByOrderCreateTime(Calendar.getInstance().getTime());
    }

}
