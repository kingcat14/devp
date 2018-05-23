package net.aicoder.devp.security.business.security.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;



public class AccountPasswordCondition implements Serializable{


	private String id;

	private String accountId;
	private String accountName;
	private String password;
	private Integer wrongCount;
	private Integer wrongCountMax;
	private Integer wrongCountMin;


	public String getAccountId(){
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}


	public String getAccountName(){
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public String getPassword(){
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public Integer getWrongCount(){
		return wrongCount;
	}
	public void setWrongCount(Integer wrongCount) {
		this.wrongCount = wrongCount;
	}

	public Integer getWrongCountMin(){
		return wrongCountMin;
	}
	public void setWrongCountMin(Integer wrongCountMin) {
		this.wrongCountMin = wrongCountMin;
	}

	public Integer getWrongCountMax(){
		return wrongCountMax;
	}
	public void setWrongCountMax(Integer wrongCountMax) {
		this.wrongCountMax = wrongCountMax;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
