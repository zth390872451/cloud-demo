package com.company.web.controller.domain;

import javax.persistence.*;


/**
 * Entity - 会员
 * @author umeox
 */
@Entity
@Table(name = "ux_member")
public class Member extends BaseEntity {

	private static final long serialVersionUID = 100007152668889L;

	/**
	 * 手机（账号：mobile/email）
	 */
	private String mobile;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * oauth2认证的clientId
	 */
	private String clientId;

	public Member(Member member){
		super();
		this.mobile = member.getMobile();
		this.password = member.getPassword();
	}
	
	public Member() {
	}
	

	public Member(Long id) {
		super.setId(id);
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
