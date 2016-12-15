package com.company.web.controller.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 该属性值。是否从远程仓库指定的文件加载。
 * Spring Cloud Config应用
 * @RefreshScope ： 当监听到refresh的 post 请求时，所有使用该注解的Bean对象(均为单例)，将会被重新创建(类似于热部署、及时编译)
 *
 */
@RefreshScope
@RestController
public class ConfigController {
    /**
     * configName应用启动时，会从配置的仓库按照指定的规则获取配置信息：configName=xxx (key=value的键=值形式)
     * 1、若是应用无法获取(如连接超时)将使用默认值。
     * 2、若是应用无法获取，可以采用重试机制:依赖(pom.xml) —— spring-retry(默认6次，间隔1s)
     */
    @Value("${configName:defaultLocal}")
    private String configName;

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }


    @RequestMapping("/configName")
    public String getConfig(){
        return this.configName;
    }

}