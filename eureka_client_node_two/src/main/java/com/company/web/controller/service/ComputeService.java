package com.company.web.controller.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ComputeService {
    @Autowired
    RestTemplate restTemplate;

    /**
     * HystrixCommand:熔断器(保护机制)，当发生错误(如 服务宕机或500异常)的时候，将调用 addServiceFallback 方法
     * @return
     */
    @HystrixCommand(fallbackMethod = "addServiceFallback")
    public String computeService() {
        String body = restTemplate.getForEntity("http://eureka-client-node/add?a=1&b=2", String.class).getBody();
        // int a =1/0; //断路器调试验证
        return body;
    }

    /**
     * 熔断机制：调用失败返回结果
     * @return
     */
    public String addServiceFallback() {
        return "eureka-client-node invoke failed,maybe you should make sure you can connect the admin!";
    }
}