package com.company.web.controller.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 该属性值。是否从远程仓库指定的文件加载。
 * Spring Cloud Config应用
 */
@RefreshScope
@RestController
public class ConfigController {

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