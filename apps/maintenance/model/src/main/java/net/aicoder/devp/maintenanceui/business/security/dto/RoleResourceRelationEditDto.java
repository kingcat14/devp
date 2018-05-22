package net.aicoder.devp.maintenanceui.business.security.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
 * 角色资源关系
 * @author icode
 */
@ApiModel(value = "修改角色资源关系使用的DTO")
public class RoleResourceRelationEditDto {

    /**
	 * 角色Id
	 * 
     */
	@NotNull(message = "角色Id不能为空")
	@ApiModelProperty(value = "角色Id", required = true)
	private Long roleId;

    /**
	 * 资源Id
	 * 
     */
	@NotNull(message = "资源Id不能为空")
	@ApiModelProperty(value = "资源Id", required = true)
	private Long resourceId;


	public Long getRoleId(){
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getResourceId(){
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
