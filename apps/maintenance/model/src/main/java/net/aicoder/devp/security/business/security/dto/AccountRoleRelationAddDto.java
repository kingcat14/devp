package net.aicoder.devp.security.business.security.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
 * 账号角色关联
 * @author icode
 */
@ApiModel(value = "新增账号角色关联使用的DTO")
public class AccountRoleRelationAddDto {

    /**
	 * 账号Id
	 * 
     */
	@NotNull(message = "账号Id不能为空")
	@ApiModelProperty(value = "账号Id", required = true)
	private Long accountId;

    /**
	 * 角色Id
	 * 
     */
	@NotNull(message = "角色Id不能为空")
	@ApiModelProperty(value = "角色Id", required = true)
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
