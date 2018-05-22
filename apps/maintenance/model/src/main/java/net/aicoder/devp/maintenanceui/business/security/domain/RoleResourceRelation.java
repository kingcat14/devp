package net.aicoder.devp.maintenanceui.business.security.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;
import com.yunkang.saas.common.framework.eo.BaseEntity;



/**
 * 角色资源关系
 * @author icode
 */
@Entity
@Table
public class RoleResourceRelation extends BaseEntity{

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

