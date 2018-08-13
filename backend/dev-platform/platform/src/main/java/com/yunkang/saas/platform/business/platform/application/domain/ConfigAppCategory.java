package com.yunkang.saas.platform.business.platform.application.domain;

import com.yunkang.saas.common.jpa.SaaSEntity;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotEmpty;
import com.yunkang.saas.common.framework.eo.GenericBaseEntity;



/**
 * 应用类别
 * @author icode
 */
@Entity
@Table(appliesTo = "config_app_category", comment = "[应用类别]")
//@DynamicUpdate
//@DynamicInsert
public class ConfigAppCategory extends SaaSEntity{

	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 类别名称
    * 
    */
    @Column(name = "name")
	@Size(max = 255, message = "类别名称超长，最多255个字符")
	private String name;

    /**
    * 类别代码
    * 
    */
    @Column(name = "code")
	@Size(max = 255, message = "类别代码超长，最多255个字符")
	private String code;

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

