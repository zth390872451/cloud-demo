package com.company.web.controller.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 标志位此接口为客户端，spring aop动态代理生成代理类。
 * 熔断机制：当错误发生时，将调用 ComputeClientHystrix 类的响应的错误处理方法(类似与spring mvc的 @ExceptionHandler)
 */
@FeignClient(value = "eureka-client-node", fallback = ComputeClientHystrix.class)
public interface ComputeClient {
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    String add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);


}