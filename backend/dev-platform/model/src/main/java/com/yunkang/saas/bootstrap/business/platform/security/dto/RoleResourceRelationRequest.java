package com.yunkang.saas.bootstrap.business.platform.security.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* RoleResourceRelation请求
*/
public class RoleResourceRelationRequest {





    /**
    * 资源ID
    */
	
	private String resourceId;


    /**
    * 角色ID
    */
	
	private String roleId;


	public String getResourceId(){
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getRoleId(){
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}



	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}