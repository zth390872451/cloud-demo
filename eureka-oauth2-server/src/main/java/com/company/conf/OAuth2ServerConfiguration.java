package com.company.conf;

import com.company.service.CustomUserDetailsServiceSelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer//于配置 OAuth 2.0 授权服务器机制
public class OAuth2ServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	DataSource dataSource;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private CustomUserDetailsServiceSelf userDetailsService;

	@Bean
	public JdbcTokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	/**
	 * AuthorizationCodeServices:为了身份验证代码授予定义了授权代码服务
	 设置授权码 储存位置：1、内存（默认)~InMemory 2、数据库 ~Jdbc
	 * @return
	 */
	@Bean
	protected AuthorizationCodeServices authorizationCodeServices() {
		return new JdbcAuthorizationCodeServices(dataSource);
	}

	/**
	 * 在令牌端点上定义了安全约束
	 * 1.clientSecret字段加密
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security)
			throws Exception {
		security.passwordEncoder(passwordEncoder);
	}

	/**
	 * 定义授权和令牌端点和令牌服务
	 * 1.The AuthenticationManager for the password grant
	 * 2.数据库存储token
	 * 3.用户详情使用自定义
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints)throws Exception {
		endpoints.authorizationCodeServices(authorizationCodeServices())
				.authenticationManager(authenticationManager).tokenStore(tokenStore()).userDetailsService(userDetailsService);
	}

	/**
	 * 客户端细节服务
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}

	/*@Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore());
        return tokenServices;
    }*/
}
