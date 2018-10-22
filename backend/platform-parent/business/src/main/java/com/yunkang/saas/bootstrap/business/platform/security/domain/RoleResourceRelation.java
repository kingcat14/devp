package com.yunkang.saas.bootstrap.business.platform.security.domain;

import com.yunkang.saas.common.jpa.SaaSEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


/**
 * 角色资源关系
 * @author icode
 */
@Entity(name = "platform_role_resource_relation")
@Table(appliesTo = "platform_role_resource_relation", comment = "[账号密码]")
public class RoleResourceRelation extends SaaSEntity<Long> {

	public static final String PROPERTY_ROLE_ID = "roleId";
	public static final String PROPERTY_RESOURCE_ID = "resourceId";


    @Id
    @Column(name = "rid")
    private Long id;


    /**
    * 角色Id
    * 
    */
    @Column(name = "role_id")
	@NotNull(message = "角色Id不能为空")
	private Long roleId;

    /**
    * 资源Id
    * 
    */
    @Column(name = "resource_id")
	@NotNull(message = "资源Id不能为空")
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

