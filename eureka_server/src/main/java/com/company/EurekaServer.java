package com.company;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer//开启Eureka，作为注册中心
@SpringBootApplication
public class EurekaServer {

	public static void main(String[] args) {
		new SpringApplicationBuilder(EurekaServer.class).web(true).run(args);
	}

}
