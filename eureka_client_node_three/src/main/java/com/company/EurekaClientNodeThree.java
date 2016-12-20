package com.company;

import feign.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class EurekaClientNodeThree {

	/**
	 * 创建RestTemplate实例，并通过@LoadBalanced注解开启均衡负载能力。
	 */
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) {
		new SpringApplicationBuilder(EurekaClientNodeThree.class).web(true).run(args);
	}

	@Bean
	Logger.Level feignLoggerLevel(){
		return Logger.Level.FULL;
	}
	/**
	 * 抽样(Samling)
	 * @return
     */
	@Bean
	public AlwaysSampler defaultSampler(){
		return new AlwaysSampler();
	}

}
