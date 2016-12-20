package com.company.web.controller.fileter;

import com.alibaba.druid.pool.DruidDataSource;
import com.company.web.controller.domain.AccountUser;
import com.company.web.controller.repository.UserRepository;
import com.netflix.zuul.http.ZuulServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.web.ZuulController;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Oauth2Interceptor implements HandlerInterceptor {

	/*private static RedisService redisService = (RedisService) ApplicationSupport.getBean("redisServiceImpl");
	private static DruidDataSource dataSource = (DruidDataSource) ApplicationSupport.getBean("druidDataSource");
	private static MonitorRepository monitorRepository = (MonitorRepository) ApplicationSupport.getBean("monitorRepository");
	private static BarrierRepository barrierRepository = (BarrierRepository) ApplicationSupport.getBean("barrierRepository");
	private static FamilyNumberRepository familyNumberRepository = (FamilyNumberRepository) ApplicationSupport.getBean("familyNumberRepository");
	private static LogMessageRepository logMessageRepository = (LogMessageRepository)ApplicationSupport.getBean("logMessageRepository");
	private static HolderRepository holderRepository = (HolderRepository) ApplicationSupport.getBean("holderRepository");
	private static MemberRepository memberRepository = (MemberRepository) ApplicationSupport.getBean("memberRepository");*/
	@Autowired
	private DruidDataSource dataSource;

	public static ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

	@Autowired
	private UserRepository userRepository;
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response, Object handler) throws Exception {
		/*if (request.getParameter("access_token")==null)
			return false;*/
		System.out.println("preHandle 被 invoked! " + request);
//		ZuulController
//		DispatcherServlet
//		HandlerMapping
//		ZuulServlet
//		ZuulHandlerMapping
		String authorization = request.getHeader("Authorization");
		String client_id = request.getHeader("client_id");
		String token = request.getParameter("token");
		if (StringUtils.isEmpty(authorization)||StringUtils.isEmpty(client_id)||StringUtils.isEmpty(token)){
			return false;
		}
//		oauth2 = (Oauth2) redisService.get(RedisKeyPre.OAUTH2_TOKEN+token);//先从Redis中查询Oauth
		Map<String,Object> map = (Map) concurrentHashMap.get(token);
		boolean resultStatus = true;
		if (map == null) {//Redis中不存在Oauth认证信息，从数据库中查询
			JdbcTokenStore store = new JdbcTokenStore(dataSource);
			OAuth2AccessToken oAuth2AccessToken = store.readAccessToken(token);
			if (oAuth2AccessToken == null) {//token 不正确
				return false;
			}else {
				if (oAuth2AccessToken.isExpired()) {//Token过期
					return false;
				}else {//从数据库中查询账户信息，验证信息是否正确
					JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
					String sql = "select user_name from oauth_access_token where token_id = ?";
					String tokenId = extractTokenKey(oAuth2AccessToken.getValue());
					String userName = jdbcTemplate.queryForObject(sql, new Object[]{tokenId},String.class);
					AccountUser accountUser = userRepository.findOneByLoginName(userName);
					if (accountUser==null)
						return false;
					if (!accountUser.getClientId().equals(client_id)){//参数有误
						return false;
					}

				}
			}
		}else {
			return true;
		}
		 return true;
	}

	private boolean HandlerMethod(Object handler, Long memberId, Long holderId, Long currentMemberId, HttpServletRequest request){
		System.out.println("HandlerMethod 被 invoked! " + handler);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
						   HttpServletResponse response, Object handler,
						   ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}


	protected static String extractTokenKey(String value) {
		if (value == null) {
			return null;
		}
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
		}

		try {
			byte[] bytes = digest.digest(value.getBytes("UTF-8"));
			return String.format("%032x", new BigInteger(1, bytes));
		}
		catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
		}
	}
}
