package com.company.web.controller;

import com.company.web.controller.annotation.ApiLimit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/12/27.
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @ApiLimit(times_max = 5,timeToLive = 5000,limitLevel= ApiLimit.LimitLevel.USER_LEVEL)
    @RequestMapping("/method01")
    public String method01(String mobile){
        return "success:"+mobile;
    }

}
