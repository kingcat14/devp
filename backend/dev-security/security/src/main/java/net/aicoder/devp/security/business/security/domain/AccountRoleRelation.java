package net.aicoder.devp.security.business.security.domain;

import com.yunkang.saas.common.framework.eo.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * 账号角色关联
 * @author icode
 */
@Entity
@Table
public class AccountRoleRelation extends BaseEntity{

	public static final String PROPERTY_ACCOUNT_ID = "accountId";
	public static final String PROPERTY_ROLE_ID = "roleId";


    @Id
    @Column(name = "rid")
    private Long id;


    /**
    * 账号Id
    * 
    */
    @Column(name = "account_id")
	@NotNull(message = "账号Id不能为空")
	private Long accountId;

    /**
    * 角色Id
    * 
    */
    @Column(name = "role_id")
	@NotNull(message = "角色Id不能为空")
	private Long roleId;

	public Long getAccountId(){
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getRoleId(){
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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

