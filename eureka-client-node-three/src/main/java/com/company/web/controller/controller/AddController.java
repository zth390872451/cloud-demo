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
 *
 *
 */
@RestController
public class AddController {
    private final Logger logger = Logger.getLogger(getClass());
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 计算服务
     * @param a
     * @param b
     * @return
     */
    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public String add(@RequestParam Integer a, @RequestParam Integer b) {
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        Integer result = a + b;
        logger.info("host:" + instance.getHost() + ",端口"+instance.getPort()+" service_id:" + instance.getServiceId() + ", result:" + result+",实例元数据："+instance.getMetadata());
        String infos = "当前响应的服务实例主机："+instance.getHost()+",端口"+instance.getPort()+", 实例的ID："+instance.getServiceId()+",实例元数据："+instance.getMetadata()
                +" 计算结果："+result;
        return infos ;
    }



}
