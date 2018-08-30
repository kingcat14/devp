package com.kingzoo.kingcat.project.icode4.business.microservice.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 微服务
 * @author icode
 */
@Entity
@Table
public class MicroService {

	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_MODULE_ID = "moduleId";
	public static final String PROPERTY_DESCRIPTION = "description";


    @Id
    private String id;


    /**
    * 微服务名称
    * 
    */
    @Column(name = "name")
	@NotNull(message = "微服务名称不能为空")
	@Size(max = 255, message = "微服务名称超长，最多255个字符")
	private String name;

    /**
    * 微服务代码
    * 
    */
    @Column(name = "code")
	@NotNull(message = "微服务代码不能为空")
	@Size(max = 255, message = "微服务代码超长，最多255个字符")
	private String code;

    /**
    * 所属模块
    * 
    */
    @Column(name = "moduleId")
	@NotNull(message = "所属模块不能为空")
	private String moduleId;
	@Transient
	private String moduleName;

    /**
    * 描述
    * 
    */
    @Column(name = "description")
	private String description;

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getModuleId(){
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName(){
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}