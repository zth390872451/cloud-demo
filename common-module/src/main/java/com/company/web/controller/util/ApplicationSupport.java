package com.company.web.controller.util;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ApplicationSupport implements DisposableBean, ApplicationContextAware {

    private static ApplicationContext appContext;

    // 获取bean对象
    public static Object getBean(String name) {
        Assert.hasText(name);

        return appContext.getBean(name);
    }

    public static Object getBeanByClass(Class classArgs){
        return appContext.getBean(classArgs);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    @Override
    public void destroy() throws Exception {
        appContext = null;
    }
}