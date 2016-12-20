package com.company.web.controller.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon配置
 */
@Configuration
public class RibbonConfiguration {

    /**
     * 创建RestTemplate实例，并通过@LoadBalanced注解开启均衡负载能力。
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    /*
        更改默认的均衡负载的算法: 设置为 轮询选择server
        AvailabilityFilteringRule方式(默认算法)：过滤掉那些因为一直连接失败的被标记为circuit tripped的后端server，
        并过滤掉那些高并发的的后端server（active connections 超过配置的阈值）
        roundRobin方式：轮询选择server
     */
    @Bean
    public IRule RibbonRule() {
        return new RoundRobinRule();
    }
}
