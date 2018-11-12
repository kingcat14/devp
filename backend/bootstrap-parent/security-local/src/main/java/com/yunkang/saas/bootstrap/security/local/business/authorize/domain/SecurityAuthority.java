package com.yunkang.saas.bootstrap.security.local.business.authorize.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by gonghongrui on 2017/1/10.
 */
public class SecurityAuthority implements GrantedAuthority {

	public SecurityAuthority(String authority){
		this.authority = authority;
	}

	private  String authority;

	@Override
	public String getAuthority() {
		return authority;
	}
}
