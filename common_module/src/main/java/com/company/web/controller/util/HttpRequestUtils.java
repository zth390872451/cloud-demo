package com.company.web.controller.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/28.
 */
public class HttpRequestUtils {

    /**
     * 直接返回客户端 结果:map
     * @param response
     * @param map
     * @throws JsonProcessingException
     */
    public static void responseData(HttpServletResponse response, Map<String,Object> map) throws JsonProcessingException {
        //将实体对象转换为jackson Object转换
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(map);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
