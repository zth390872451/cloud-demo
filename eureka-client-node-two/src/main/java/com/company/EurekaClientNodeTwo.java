package com.company;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient//开启
@SpringBootApplication
@EnableCircuitBreaker //  注解开启断路器功能
public class EurekaClientNodeTwo {

	public static void main(String[] args) {
		new SpringApplicationBuilder(EurekaClientNodeTwo.class).web(true).run(args);
	}

}
