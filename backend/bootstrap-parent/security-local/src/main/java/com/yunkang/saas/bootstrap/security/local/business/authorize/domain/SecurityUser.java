package com.yunkang.saas.bootstrap.security.local.business.authorize.domain;


import com.yunkang.saas.bootstrap.application.business.resource.domain.Resource;
import com.yunkang.saas.bootstrap.platform.business.account.domain.Account;
import com.yunkang.saas.bootstrap.platform.business.account.domain.AccountPassword;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gonghongrui on 2017/1/10.
 */
public class SecurityUser implements UserDetails {

	private Account account;
	private String password;
	private Set<SecurityAuthority> authorities = new HashSet<>();

	public SecurityUser(Account account, List<Resource> resourceList){
		this.account = account;

		if(CollectionUtils.isNotEmpty(resourceList)) {
			for (Resource resource : resourceList) {
				authorities.add(new SecurityAuthority(resource.getUrl()));
			}

		}
	}

	public SecurityUser(Account account){

		this.account = account;

	}

	public SecurityUser(Account account, AccountPassword accountPassword, List<Resource> resourceList){

		this(account, resourceList);
		this.password = accountPassword.getPassword();

	}

	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public Collection<SecurityAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return account.getAccountName();
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
		return account.getEnable();
	}

	public void setAuthorities(Set<SecurityAuthority> authorities) {
		this.authorities = authorities;
	}
	public void addAuthority(SecurityAuthority securityAuthority){
		this.authorities.add(securityAuthority);
	}
}
