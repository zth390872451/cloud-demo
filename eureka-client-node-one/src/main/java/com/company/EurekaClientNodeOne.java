package com.company;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/*
	EnableDiscoveryClient:
		功能：开启发现服务功能，应用启动，将试图向注册中心(application.properties指定的defaultZone)注册服务
		实现依赖(pom)：spring-cloud-starter-eureka(eureka-client)
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker //  注解开启断路器功能
public class EurekaClientNodeOne {

	public static void main(String[] args) {
		new SpringApplicationBuilder(EurekaClientNodeOne.class).web(true).run(args);
	}

}
