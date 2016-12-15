package com.company.web.controller.fileter;

import com.company.web.controller.util.StoreMemory;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 服务过滤:对外开放服务还需要一些安全措施来保护客户端只能访问它应该访问到的资源
 *
 * 定义了一个Zuul过滤器，实现了在请求被路由之前检查请求中是否有accessToken参数，
 * 若有就进行路由，若没有就拒绝访问，返回401 Unauthorized错误
 *
 */

public class AccessFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

/**
     * pre：可以在请求被路由之前调用
     * routing：在路由请求时候被调用
     * post：在routing和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     *
     * @return
     */

    @Override
    public String filterType() {
        return "pre";
    }

/**
     * 通过int值来定义过滤器的执行顺序
     *
     * @return
     */

    @Override
    public int filterOrder() {
        return 0;
    }

/**
     * 返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。在上例中，我们直接返回true，所以该过滤器总是生效。
     *
     * @return
     */

    @Override
    public boolean shouldFilter() {
        return true;
    }

/**
     * run():过滤器的具体逻辑
     * 业务逻辑：访问的url必须有 accessToken 参数
     * @return
     */

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        Object accessToken = request.getParameter("access_token");
        if (accessToken == null) {
            log.warn("access token is empty");
            ctx.setSendZuulResponse(false);//zuul过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(401);//无权访问
            return "forbidden request,missing the access_token parameter";
        }else {
//            String username
            Object o = StoreMemory.tokenStorage.get(accessToken);


        }
        log.info("access token ok");
        return null;
    }

}
