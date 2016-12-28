package com.company.web.controller.service;

import com.company.web.controller.annotation.ApiLimit;
import com.company.web.controller.util.ApplicationSupport;
import com.company.web.controller.util.HttpRequestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/28.
 */
public class ApiLimitInterceptorService {

    /**
     * 接口访问次数限制处理类
     * @param request
     * @param response
     * @param handler
     * @return
     */
    public static boolean ApiLimitHandler(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            Class<?> clazz = hm.getBeanType();
            Method m = hm.getMethod();
            String clazzName = clazz.getName();
            String methodName = m.getName();
            if (clazz != null && m != null) {
                //下：判断当前注解 在类上、接口上是否存在
                boolean isClzAnnotation = clazz.isAnnotationPresent(ApiLimit.class);
                boolean isMethodAnnotation = m.isAnnotationPresent(ApiLimit.class);
                if (isClzAnnotation || isMethodAnnotation) {
                    ApiLimit apiLimit = null;
                    //如果方法和类中同时声明这个注解，那么方法中的注解会覆盖类中的设定
                    if (isMethodAnnotation) {
                        apiLimit = m.getAnnotation(ApiLimit.class);
                    }else if (isClzAnnotation) {
                        apiLimit = clazz.getAnnotation(ApiLimit.class);
                    }

                    String limitKey = null;
                    int visitedTimesMax = apiLimit.times_max();//该接口最大访问次数
                    long  timeToLive = apiLimit.timeToLive();//存活时间
                    //限制级别
                    if (apiLimit.limitLevel()== ApiLimit.LimitLevel.API_LEVEL){//接口级别
                        limitKey = request.getServletPath();
                    }else {//用户级别
                        limitKey = request.getParameter("accessToken");
                    }
                    if (StringUtils.isEmpty(limitKey))
                        return false;

                    /*----------------------- 校验是否频次太高 -----------------------*/
                    if (isTooFrequent(limitKey, visitedTimesMax, timeToLive))
                    {
                        System.out.println(" 频率过快，请稍后再试");
                        Map<String,Object> map = new HashMap<String, Object>();
                        map.put("result","频率过快，请稍后再试");
                        try {
                            HttpRequestUtils.responseData(response,map);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();//需要使用 log4j 打印 否则 只会在 控制台输出
                        }
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     *  频次超出指定大小
     * true:超出
     */
    public static boolean isTooFrequent(String api_key, int visitedTimesMax, long timeToLive) {
        RedisService redisService = (RedisService) ApplicationSupport.getBean("redisServiceImpl");

        Integer  visitedTimes = (Integer) redisService.get(api_key);//访问次数
        if (visitedTimes==null){//在该时间段是第一次访问
            visitedTimes = 1;
            redisService.set(api_key,visitedTimes,timeToLive);//存入redis，在 timeToLive 之后失效
            return false;
        }else {
            visitedTimes++;
            if (visitedTimes>=visitedTimesMax){//超出
                return true;
            }else {
                redisService.set(api_key,visitedTimes,timeToLive);//存入redis，在 timeToLive 之后失效
                return false;
            }
        }
    }

}
