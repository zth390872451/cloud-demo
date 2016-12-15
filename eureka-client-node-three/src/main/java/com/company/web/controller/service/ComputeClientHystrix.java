package com.company.web.controller.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class ComputeClientHystrix implements ComputeClient {
    @Override
    public String add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
        return "Feign Hystrix 提示：服务调用失败";
    }
}