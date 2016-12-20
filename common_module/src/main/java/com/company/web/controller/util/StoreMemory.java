package com.company.web.controller.util;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟缓存
 */
public class StoreMemory {
    //模拟redis存储
    // 以 token 为键，失效时间为值。
    public static ConcurrentHashMap<String,Object> tokenStorage = new ConcurrentHashMap<String, Object>();

}
