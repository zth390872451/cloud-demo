package com.company;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by Administrator on 2016/11/30.
 */
@SpringBootApplication
@EnableAdminServer//作为健康监测服务器
@EnableDiscoveryClient//Eureka客户端节点
public class SpringBootAdminServer {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdminServer.class, args);
    }

}
