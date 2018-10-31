package com.yunkang.saas.bootstrap.platform.business.platform.tenant.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Table;


/**
 * 租户管理员账号
 * @author icode
 */
@Entity(name = "platform_tenant_admin_account")
@Table(appliesTo = "platform_tenant_admin_account", comment = "[租户管理员账号]")
//@DynamicUpdate
//@DynamicInsert
public class TenantAdminAccount extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ACCOUNT_NAME = "accountName";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 租户id
    * 
    */
    @Column(name = "tid", nullable = false, updatable = false, unique = true)
	private Long tid;

    /**
    * 管理员账号
    * 
    */
    @Column(name = "account_name", nullable = false, updatable = true)
	@Size(max = 255, message = "管理员账号超长，最多255个字符")
	private String accountName;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getAccountName(){
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
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

