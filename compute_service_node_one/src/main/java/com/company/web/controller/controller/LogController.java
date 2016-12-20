package com.company.web.controller.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/12/16.
 */
@RestController
@RequestMapping("log_test")
public class LogController {

    @RequestMapping("test01")
    public String test01(){

        return "test01";
    }

}
