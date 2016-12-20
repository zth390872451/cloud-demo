package com.company.web.controller.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/9/23.
 */
@RestController
public class AddController {
    private final Logger logger = Logger.getLogger(getClass());
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/first",method = RequestMethod.GET)
    public String first(){
        //获取本地服务实例
        ServiceInstance localServiceInstance = discoveryClient.getLocalServiceInstance();
//        localServiceInstance.getHost()
        logger.info("indexController first from host:"+localServiceInstance.getHost()
                    +"port :"+localServiceInstance.getPort());
        return "index:first";
    }

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        Integer r = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return r;
    }


}
