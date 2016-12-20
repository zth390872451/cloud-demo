package com.company;

//import com.company.AccessFilter;
import com.company.web.controller.fileter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用@EnableZuulProxy注解激活zuul。
 * 跟进该注解可以看到该注解整合了@EnableCircuitBreaker、@EnableDiscoveryClient，是个组合注解，目的是简化配置。
 */
@SpringBootApplication
@EnableZuulProxy
@RestController
public class ZuulApiGatewayApplication {

    @RequestMapping("home")
    public String home(){
        return "home";
    }
    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(ZuulApiGatewayApplication.class, args);
    }
}
