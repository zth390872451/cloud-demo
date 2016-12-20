package com.company.web.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @EnableDiscoveryClient 注解:
 * 该注解能激活Eureka中的DiscoveryClient实现，才能实现Controller中对服务信息的输出。
 * Eureka提供了Application Service 客户端的自行注册功能。
 * Eureka作为客户端
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ComputeService01 {
    /**
     * 创建RestTemplate实例，并通过@LoadBalanced注解开启均衡负载能力。
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(ComputeService01.class).web(true).run(args);
    }
}
