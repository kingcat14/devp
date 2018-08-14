package com.yunkang.saas.platform.business.common.domain;

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
 * 通用配置类型
 * @author icode
 */
@Entity
@Table(appliesTo = "simple_config_type", comment = "[通用配置类型]")
//@DynamicUpdate
//@DynamicInsert
public class SimpleConfigType extends SaaSEntity{

	public static final String PROPERTY_TYPE_NAME = "typeName";
	public static final String PROPERTY_TYPE_CODE = "typeCode";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 类型名称
    * 
    */
    @Column(name = "type_name")
	@Size(max = 255, message = "类型名称超长，最多255个字符")
	private String typeName;

    /**
    * 类型代码
    * 
    */
    @Column(name = "type_code")
	@Size(max = 255, message = "类型代码超长，最多255个字符")
	private String typeCode;

	public String getTypeName(){
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeCode(){
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
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

