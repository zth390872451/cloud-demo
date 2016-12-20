package com.company.service;

import com.company.domain.Role;
import com.company.web.controller.domain.AccountUser;
import com.company.web.controller.repository.AccountUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
	@Autowired
	private AccountUserRepository accountUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountUser accountUser = accountUserRepository.findOneByMobile(username);
		if (accountUser == null) {
			log.error("用户不存在");
			throw new UsernameNotFoundException(String.format("AccountUser %s does not exist!", username));
		}
		return new UserRepositoryUserDetails(accountUser);
	}

	/**
	 * bug：在重命名该类为B(原有名字为A)之后，若仍旧以原来的账号密码进行获取AccessToken信息(旧AccessToken失效，刷新AccessToken方式)，将会提示
	 *  A类找不到(ClassNotFound)的异常。或者是在 自定义的 loadUserByUsername(String username)方法执行时，username=null，导致 获取AccessToken失败，提示用户不存在
	 *  解决方式：删除数据库的两张表对应的两条记录： oauth_access_token 和oauth_refresh_token
	 */
	private final static class UserRepositoryUserDetails extends AccountUser implements UserDetails {
		private static final long serialVersionUID = 1L;
		private UserRepositoryUserDetails(AccountUser accountUser) {
			super(accountUser);
		}
//		BasicAuthenticationFilter
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			Role role = new Role();
			return role.getRoles();
		}

		@Override
		public String getUsername() {
			return getMobile();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

	}
	
	/*public static void main(String[] args) {
		String password = "4bd1kufizod95rlm157rxqfueb4ernj5";
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
		System.out.println(System.currentTimeMillis());
	}*/
	
	/*
	  INSERT INTO oauth_client_details (
			client_id,
			client_secret,
			scope,
			authorized_grant_types,
			authorities,
			access_token_validity
		)
		VALUES
			(
				'wxb_doki_api_ios',
				'$2a$10$/AgahDVVEckNZt18ZIrQVONkXVx/NG.srm9tX6JgkZ8r8ULeLr3o.',
				'read,write',
				'client_credentials,refresh_token',
				'USER',
				'2592000'
			)
			*/
}
