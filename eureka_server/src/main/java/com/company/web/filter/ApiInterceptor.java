package com.company.web.filter;

import com.company.web.controller.annotation.ApiLimit;
import com.company.web.controller.service.ApiLimitInterceptorService;
import com.company.web.controller.service.RedisService;
import com.company.web.controller.util.ApplicationSupport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ApiInterceptor extends HandlerInterceptorAdapter{


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!ApiLimitInterceptorService.ApiLimitHandler(request,response, handler)) return false;
        return true;
    }

}
