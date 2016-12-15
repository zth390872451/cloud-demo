package com.company;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class EurekaClientNodeThree {

	public static void main(String[] args) {
		new SpringApplicationBuilder(EurekaClientNodeThree.class).web(true).run(args);
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
