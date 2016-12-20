package com.company.web.controller.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    /**
     * 测试日志收集
     * @param
     * @return
     */
    @RequestMapping("/log03")
    public String sendHttp(String param){
        logger.info("eureka-client-node-03  receive param = {}",param);

      /*  String url = "http://eureka-client-node03/test/log03?param="+param;

        logger.info("eureka-client-node-03  call the url = {}",url);

        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        String body = forEntity.getBody();*/
        return "node-03 body";
    }



}
