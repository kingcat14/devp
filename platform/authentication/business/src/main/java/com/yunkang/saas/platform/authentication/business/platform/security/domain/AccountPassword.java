package com.yunkang.saas.platform.authentication.business.platform.security.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 账号密码
 * @author icode
 */
@Entity()
@Table(name = "platform_account_password")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class AccountPassword extends BaseEntity<String>{

	public static final String PROPERTY_ACCOUNT = "account";
	public static final String PROPERTY_ACCOUNT_NAME = "accountName";
	public static final String PROPERTY_PASSWORD = "password";
	public static final String PROPERTY_WRONG_COUNT = "wrongCount";


    @Id
    @Column(name = "id")
    private String id;


    /**
    * 账号
    * 
    */
    @Column(name = "account", nullable = false, updatable = true)
	private Long account;

    /**
    * 姓名
    * 
    */
    @Column(name = "account_name", nullable = true, updatable = true)
	@Size(max = 255, message = "姓名超长，最多255个字符")
	private String accountName;

    /**
    * 密码
    * 
    */
    @Column(name = "password", nullable = false, updatable = true)
	@Size(max = 255, message = "密码超长，最多255个字符")
	private String password;

    /**
    * 错误次数
    * 
    */
    @Column(name = "wrong_count", nullable = true, updatable = true)
	private Integer wrongCount;

	public Long getAccount(){
		return account;
	}
	public void setAccount(Long account) {
		this.account = account;
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

