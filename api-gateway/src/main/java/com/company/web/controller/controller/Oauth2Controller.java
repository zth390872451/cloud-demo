package com.company.web.controller.controller;


import com.company.web.controller.domain.AccountUser;
import com.company.web.controller.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * @Desc 授权
 *
 同一个会员登录（可能存在2个版本的APP）
	member.setClientId(client_id);后一个
 * @author umeox
 * @version II
 */
@RestController
@RequestMapping("/api/oauth2")
public class Oauth2Controller {
	
	private static final Logger log = LoggerFactory.getLogger(Oauth2Controller.class);

	@Autowired
	private ResourceOwnerPasswordResourceDetails resource;//账号密码验证(密Oauth2的码模式)
	@Autowired
	private ClientCredentialsResourceDetails clientCredentials;//客户端验证(Oauth2的客户端模式)
	@Autowired
	private UserRepository userRepository;
	/**
	 * 获取token
	 */
	@RequestMapping(value = "/accessTokenByPwd",method = RequestMethod.POST)
	public Object accessToken(@RequestParam(value = "client_id") String client_id,
							  @RequestParam(value = "client_secret") String client_secret,
							  @RequestParam(value = "grant_type") String grant_type,
							  @RequestParam(value = "username") String username,
							  @RequestParam(value = "password") String password
									 ){
		//App端dm5加密后不足32位加零补齐的情况
		String pre = "";
		if (password.length() < 32) {
			int len = 32 - password.length();
			pre = String.format("%0" + len + "d", 0);
		}
//		resource.setAccessTokenUri(tokenUrl);
		resource.setClientId(client_id);
		resource.setClientSecret(client_secret);
		resource.setGrantType(grant_type);
		//resource.setScope(Arrays.asList("read", "write"));
		resource.setUsername(username);
		resource.setPassword(pre + password);

		ResourceOwnerPasswordAccessTokenProvider provider = new ResourceOwnerPasswordAccessTokenProvider();
		OAuth2AccessToken accessToken = null;
		try {
			accessToken = provider.obtainAccessToken(resource, new DefaultAccessTokenRequest());
		} catch (NullPointerException e) {
			log.error("授权失败原因：{}", e.getMessage());
			return "用户不存在";
		}catch (Exception e){
			log.error("授权失败原因：{}", e.getMessage());
			return "创建token失败";
		}
		AccountUser accountUser = userRepository.findOneByLoginName(username);//因为会在 OAuth2 服务器进行用户名密码验证，所以不必再判断空异常
		//目前没有找到每次认证获取新的token方式，故用刷新token方式来处理
		if (accessToken != null && !StringUtils.isEmpty(accessToken.getRefreshToken().getValue())) {
//			redisService.del(RedisKeyPre.OAUTH2_TOKEN+accessToken.getValue());
			OAuth2RefreshToken refreshToken = new DefaultOAuth2RefreshToken(accessToken.getRefreshToken().getValue());
			accessToken = provider.refreshAccessToken(resource, refreshToken, new DefaultAccessTokenRequest());
		}
		String token = accessToken.getValue();
		int expiresIn = accessToken.getExpiresIn();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("access_token", token);
		map.put("token_type", accessToken.getTokenType());
		map.put("expires_in", expiresIn);

		return accessToken;
	}


}
