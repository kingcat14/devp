package com.yunkang.saas.platform.services.core.business.platform.account.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 账号
 * @author icode
 */
@Entity()
@Table(name = "platform_account")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class Account extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_NICK_NAME = "nickName";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_ACCOUNT_NAME = "accountName";
	public static final String PROPERTY_ACCOUNT_PASSWORD = "accountPassword";
	public static final String PROPERTY_MOBILE = "mobile";
	public static final String PROPERTY_EMAIL = "email";
	public static final String PROPERTY_ENABLE = "enable";
	public static final String PROPERTY_EXPIRED = "expired";
	public static final String PROPERTY_LOCKED = "locked";
	public static final String PROPERTY_DELETED = "deleted";


    @Id
    @Column(name = "id", length = 32)
    private Long id;


    /**
    * 租户id
    * 
    */
    @Column(name = "tid", nullable = false, updatable = false)
	private Long tid;

    /**
    * 昵称
    * 
    */
    @Column(name = "nick_name", nullable = false, updatable = true)
	@Size(max = 255, message = "昵称超长，最多255个字符")
	private String nickName;

    /**
    * 姓名
    * 
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "姓名超长，最多255个字符")
	private String name;

    /**
    * 账号
    * 
    */
    @Column(name = "account_name", nullable = false, updatable = false)
	@Size(max = 255, message = "账号超长，最多255个字符")
	private String accountName;

    /**
    * 密码
    * 
    */
    @Column(name = "account_password", nullable = false, updatable = true)
	@Size(max = 255, message = "密码超长，最多255个字符")
	private String accountPassword;

    /**
    * 手机号
    * 
    */
    @Column(name = "mobile", nullable = true, updatable = true)
	@Size(max = 255, message = "手机号超长，最多255个字符")
	private String mobile;

    /**
    * 邮箱
    * 
    */
    @Column(name = "email", nullable = true, updatable = true)
	@Size(max = 255, message = "邮箱超长，最多255个字符")
	private String email;

    /**
    * 已启用
    * 
    */
    @Column(name = "enable", nullable = false, updatable = true)
	private Boolean enable;

    /**
    * 已过期
    * 
    */
    @Column(name = "expired", nullable = false, updatable = true)
	private Boolean expired;

    /**
    * 已锁定
    * 
    */
    @Column(name = "locked", nullable = false, updatable = true)
	private Boolean locked;

    /**
    * 已删除
    * 
    */
    @Column(name = "deleted", nullable = false, updatable = true)
	private Boolean deleted;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getNickName(){
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getAccountName(){
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountPassword(){
		return accountPassword;
	}
	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public String getMobile(){
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail(){
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEnable(){
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Boolean getExpired(){
		return expired;
	}
	public void setExpired(Boolean expired) {
		this.expired = expired;
	}

	public Boolean getLocked(){
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean getDeleted(){
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

