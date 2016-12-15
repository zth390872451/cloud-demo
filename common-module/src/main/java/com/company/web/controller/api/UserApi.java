package com.company.web.controller.api;

import com.company.web.controller.domain.AccountUser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Feign 客户端继承：UserClient extends UserApi
 * 普通服务端实现：UserFeignServer implements UserApi
 */
public interface UserApi {

    @RequestMapping(method = RequestMethod.GET, value ="/users/{id}")
    public AccountUser getUser(@PathVariable("id") long id);
}
