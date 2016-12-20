package com.company;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient//开启
@SpringBootApplication
@EnableCircuitBreaker //  注解开启断路器功能
public class EurekaClientNodeTwo {
	/**
	 * 创建RestTemplate实例，并通过@LoadBalanced注解开启均衡负载能力。
	 */
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) {
		new SpringApplicationBuilder(EurekaClientNodeTwo.class).web(true).run(args);
	}

}
