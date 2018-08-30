package com.kingzoo.kingcat.project.icode4.business.system.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 对象类型
 * @author icode
 */
@Entity
@Table
public class ModelType {

	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";


    @Id
    private String id;


    /**
    * 类型代码
    * 
    */
    @Column(name = "code")
	@NotNull(message = "类型代码不能为空")
	@Size(max = 255, message = "类型代码超长，最多255个字符")
	private String code;

    /**
    * 类型名称
    * 
    */
    @Column(name = "name")
	@NotNull(message = "类型名称不能为空")
	@Size(max = 255, message = "类型名称超长，最多255个字符")
	private String name;

	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
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