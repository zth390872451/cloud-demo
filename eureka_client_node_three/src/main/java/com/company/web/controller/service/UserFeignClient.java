package com.company.web.controller.service;
import com.company.web.controller.api.UserApi;
import org.springframework.cloud.netflix.feign.FeignClient;
/**
 * Feign 客户端
 */
@FeignClient("feign-server")
public interface UserFeignClient extends UserApi {

}
