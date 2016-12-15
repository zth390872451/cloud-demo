package com.company.web.controller.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2016/12/5.
 */
@RestController
@RequestMapping("/selectService")
public class TestServiceController {
    private static final Logger logger = LoggerFactory.getLogger(TestServiceController.class);
    @Autowired
      private   RestTemplate restTemplate;
    /**
     * 选定的服务名称
     * @param service_name
     * @return
     */
    @RequestMapping("/{service_name}/{mappings}")
    public String sendHttp(@PathVariable("service_name")String service_name,@PathVariable("mappings")String mappings){
        String url = "http://" + service_name + "/" + mappings + "?a=10&b=20";
        logger.info("url = {}",url);
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        String body = forEntity.getBody();
        return "service_name:"+service_name+" ; mappings :"+mappings+body;
    }



}
