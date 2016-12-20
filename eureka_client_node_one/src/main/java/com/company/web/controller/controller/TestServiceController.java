package com.company.web.controller.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2016/12/5.
 */
@RestController
@RequestMapping("/test")
public class TestServiceController {
    private static final Logger logger = LoggerFactory.getLogger(TestServiceController.class);
    @Autowired
      private   RestTemplate restTemplate;
    @Autowired
    private Tracer tracer;
    /**
     * 测试日志收集
     * @param
     * @return
     */
    @RequestMapping("/log01")
    public String sendHttp(String param){
        logger.info("feign-server  receive param = {}",param);
        Span currentSpan = tracer.getCurrentSpan();
        System.out.println("currentSpan Name = " + currentSpan.getName());
        System.out.println("currentSpan  = " + currentSpan.toString());
        String url = "http://eureka-client-node-02/test/log02?param="+param;

        logger.info("feign-server  call the url = {}",url);

        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        String body = forEntity.getBody();
        return body;
    }



}
