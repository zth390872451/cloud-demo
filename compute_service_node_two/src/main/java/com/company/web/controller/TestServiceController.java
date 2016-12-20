package com.company.web.controller;

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
@RequestMapping("/service")
public class TestServiceController {
    private static final Logger logger = LoggerFactory.getLogger(TestServiceController.class);
    @Autowired
      private   RestTemplate restTemplate;
    /**
     * 选定的服务名称
     * @param
     * @return
     */
    @RequestMapping("/log02")
    public String sendHttp(String param){
        logger.info("log_02 receive param = {}",param);

        String url = "http://log_03/service/log03?param="+param;

        logger.info("log_02  call the url = {}",url);

        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        String body = forEntity.getBody();
        return body;
    }


}
