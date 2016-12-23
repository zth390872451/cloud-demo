
package com.company.web.controller.conf;

import com.company.web.controller.fileter.Oauth2Interceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ZuulHandlerBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

//    private Oauth2Interceptor oauth2Interceptor = new Oauth2Interceptor() ;
//
    @Autowired
    private Oauth2Interceptor oauth2Interceptor;

    @Override
    public boolean postProcessAfterInstantiation(final Object bean, final String beanName) throws BeansException {
        if (bean instanceof ZuulHandlerMapping) {
            ZuulHandlerMapping zuulHandlerMapping = (ZuulHandlerMapping) bean;
            Object[] objects = {oauth2Interceptor};
            zuulHandlerMapping.setInterceptors(objects);
        }

        return super.postProcessAfterInstantiation(bean, beanName);
    }

}
