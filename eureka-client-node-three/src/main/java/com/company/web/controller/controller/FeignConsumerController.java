package com.company.web.controller.controller;

import com.company.web.controller.domain.AccountUser;
import com.company.web.controller.service.ComputeClient;
import com.company.web.controller.service.UserFeignClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Ribbon 客户端 消费add 服务（使用2种方式）
 *
 */
@RestController
public class FeignConsumerController {

//    private static final Logger logger = LoggerFactory.getLogger(FeignConsumerController.class);
        private static final Logger log = Logger.getLogger(FeignConsumerController.class.getName());
    @Autowired
    UserFeignClient userFeignClient;
    @Autowired
    ComputeClient computeClient;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/feign_consumer", method = RequestMethod.GET)
    public String add() {
//        logger.info("{}", "Spring-feign-service-add2 method");
        String result = computeClient.add(10, 30);
        return result;
    }


    /**
     *
     * @return
     */
    @RequestMapping(value = "/feign_user", method = RequestMethod.GET)
    public AccountUser feignUser() {
//        logger.info("{}", "Feign 客户端调用服务端服务");
//        logger.info("calling trace demo backend");
        log.log(Level.INFO, "calling trace demo backend");
        AccountUser accountUser = userFeignClient.getUser(1);
        return accountUser;
    }


}