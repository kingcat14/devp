package com.yunkang.saas.security.service.business.platform.domain;


import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by gonghongrui on 2017/1/10.
 */
public class SecurityUser implements UserDetails {

	private Account account;

	public SecurityUser(Account account, List<Resource> resourceList){
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public Collection<SecurityAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return account.getAccountName();
	}

	@Override
	public String getUsername() {
		return account.getName();
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
