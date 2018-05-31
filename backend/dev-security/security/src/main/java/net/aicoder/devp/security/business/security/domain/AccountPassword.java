package net.aicoder.devp.security.business.security.domain;

import com.yunkang.saas.common.framework.eo.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 账号密码
 * @author icode
 */
@Entity
@Table
public class AccountPassword extends BaseEntity{

	public static final String PROPERTY_ACCOUNT_ID = "accountId";
	public static final String PROPERTY_ACCOUNT_NAME = "accountName";
	public static final String PROPERTY_PASSWORD = "password";
	public static final String PROPERTY_WRONG_COUNT = "wrongCount";


    @Id
    @Column(name = "rid")
    private Long id;


    /**
    * 账号Id
    * 
    */
    @Column(name = "account_id")
	@NotNull
	private Long accountId;

    /**
    * 账号账号
    * 
    */
    @Column(name = "account_name")
	@Size(max = 255, message = "账号账号超长，最多255个字符")
	private String accountName;

    /**
    * 密码
    * 
    */
    @Column(name = "password")
	@Size(max = 255, message = "密码超长，最多255个字符")
	private String password;

    /**
    * 错误次数
    * 
    */
    @Column(name = "wrong_count")
	private Integer wrongCount;

	public Long getAccountId(){
		return accountId;
	}
	public void setAccountId(Long accountId) {
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

