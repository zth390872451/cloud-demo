package com.company.web.controller.annotation;

import java.lang.annotation.*;

/**
 * 接口访问限制注解:针对于用户级别
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface ApiLimit {
    /**
     * 单个用户每秒最多访问次数,默认10
     * @return
     */
    int times_max() default 10;

    /**
     * 存活时间(多长时间后失效),默认 1000ms
     * @return
     */
    long timeToLive() default 1000;
    /**
     * 限制级别:
     * 0：用户级别
     * 1：方法级别
     * 根据限制级别，来决定当前接口限流拦截逻辑：
     *  key的来源：
     *      如果 limitLevel=0：用户级别，那么key = 用户标识或者AccessToken
     *      如果 limitLevel=1：方法级别，那么key = 类名+方法名
     *
     *      默认接口级别
     */
    LimitLevel limitLevel() default LimitLevel.API_LEVEL;

    public enum  LimitLevel {
        API_LEVEL, //方法级别
        USER_LEVEL //用户级别

    }


}
