package net.aicoder.devp.security.business.security.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 角色资源关系的值对象
*/
@ApiModel(value = "展现角色资源关系的值对象")
public class RoleResourceRelationVO {

    @ApiModelProperty(value = "记录id")
    private Long id;

    @ApiModelProperty(value = "角色Id")
    /**
    * 角色Id
    * 
    */
    private Long roleId;

    @ApiModelProperty(value = "资源Id")
    /**
    * 资源Id
    * 
    */
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