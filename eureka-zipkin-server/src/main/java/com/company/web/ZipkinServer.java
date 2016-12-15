package com.company.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableDiscoveryClient//开启
@EnableZipkinServer//作为ZipkinServer启动
public class ZipkinServer {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ZipkinServer.class).web(true).run(args);
	}

}
