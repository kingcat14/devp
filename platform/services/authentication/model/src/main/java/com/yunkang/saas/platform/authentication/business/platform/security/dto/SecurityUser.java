package com.yunkang.saas.platform.authentication.business.platform.security.dto;


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

	private AccountDTO account;

	private Set<SecurityAuthority> authorities = new HashSet<>();

	public SecurityUser(AccountDTO account, List<String> resourceList){
		this.account = account;

		if(CollectionUtils.isNotEmpty(resourceList)) {
			for (String resource : resourceList) {
				authorities.add(new SecurityAuthority(resource));
			}
		}
	}

	public SecurityUser(AccountDTO account){

		this.account = account;

	}


	public AccountDTO getAccount() {
		return account;
	}
	public void setAccount(AccountDTO account) {
		this.account = account;
	}

	@Override
	public Collection<SecurityAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return account.getAccountPassword();
	}
	@Override
	public String getUsername() {
		return account.getAccountName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return !account.getExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return !account.getLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return account.getEnable() && !account.getDeleted() ;
	}

	public void setAuthorities(Set<SecurityAuthority> authorities) {
		this.authorities = authorities;
	}
	public void addAuthority(SecurityAuthority securityAuthority){
		this.authorities.add(securityAuthority);
	}
}
