package com.yunkang.saas.security.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 角色的值对象
*/
@ApiModel(value = "展现角色的值对象")
public class RoleVO {

    @ApiModelProperty(value = "记录id")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    /**
    * 角色名称
    * 
    */
    private String name;

    @ApiModelProperty(value = "角色描述")
    /**
    * 角色描述
    * 
    */
    private String description;


    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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