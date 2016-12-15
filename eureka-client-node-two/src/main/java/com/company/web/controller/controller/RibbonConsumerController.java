package com.company.web.controller.controller;

import com.company.web.controller.service.ComputeService;
import com.company.web.controller.util.ApplicationSupport;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Ribbon 客户端 消费add 服务（使用2种方式）
 *
 */
@RestController
public class RibbonConsumerController {

    private static final Logger logger = LoggerFactory.getLogger(RibbonConsumerController.class);

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private ComputeService computeService;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * Ribbon 客户端但是不加 断路器
     *
     * @return
     */

    @RequestMapping(value = "/consumer", method = RequestMethod.GET)
    public String compute(@RequestParam Integer a, @RequestParam Integer b) {
        //查看当前使用的 负载均衡算法
        try {
            RoundRobinRule roundRobinRule = (RoundRobinRule) ApplicationSupport.getBean("RoundRobinRule");
            logger.info("loadBalancer：{}", roundRobinRule.toString());
        }catch (Exception e){
            logger.info("Exception e：{}", e.toString());
        }
        IRule beanByClass = (IRule) ApplicationSupport.getBeanByClass(IRule.class);
        ILoadBalancer loadBalancer = beanByClass.getLoadBalancer();
        if (loadBalancer!=null){
            logger.info("负载均衡信息 loadBalancer：{}", loadBalancer.toString());
            List<Server> allServers = loadBalancer.getAllServers();
            if (allServers.size()>0){
                logger.info(" 服务器列表信息 ");

                for (Server server:allServers) {
                    Server.MetaInfo metaInfo = server.getMetaInfo();
                    logger.info(" 服务节点：appName={},instanceId={},serviceIdForDiscovery={},serverGroup={}",
                            metaInfo.getAppName(),metaInfo.getInstanceId(),metaInfo.getServiceIdForDiscovery(),metaInfo.getServerGroup());
                }
            }
        }
//        RoundRobinRule ribbonRule = (RoundRobinRule) ApplicationSupport.getBeanByClass(RoundRobinRule.class);
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://eureka-client-node/add?a="+a+"&b="+b, String.class);
        return forEntity.getBody();
    }

    /**
     *  Ribbon 客户端+断路器
     * @return
     */
    @RequestMapping(value = "/consumer2", method = RequestMethod.GET)
    public String add2() {
        logger.info("断路器方式");
        return computeService.computeService();
    }

}