package com.kingzoo.kingcat.project.icode4.business.tplfile.controller.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 模板集合
 * @author icode
 */
public class TplSetAddRequest {

	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_PARENT_ID = "parentId";
	public static final String PROPERTY_DESCRIPTION = "description";

    /**
	 * 集合名称
	 * 
     */
	@NotNull(message = "集合名称不能为空")
	@Size(max = 255, message = "集合名称超长，最多255个字符")
	private String name;

    /**
	 * 集合代码
	 * 
     */
	@NotNull(message = "集合代码不能为空")
	@Size(max = 255, message = "集合代码超长，最多255个字符")
	private String code;

    /**
	 * 集合类型
	 * 
     */
	@Size(max = 255, message = "集合类型超长，最多255个字符")
	private String type;

    /**
	 * 上级集合
	 * 
     */
	private String parentId;
	private String parentName;

    /**
	 * 描述
	 * 
     */
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

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getParentId(){
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentName(){
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
