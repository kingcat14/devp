package com.yunkang.saas.platform.monitor.business.indicator.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;



/**
 * 指标
 * @author icode
 */
@Entity()
@Table(name = "indicator_indicator")
//@DynamicUpdate
//@DynamicInsert
public class Indicator extends BaseEntity<String>{

	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_TYPE = "type";


    @Id
    @Column(name = "id")
    private String id;


    /**
    * 代码
    * 
    */
    @Column(name = "code", nullable = false, updatable = true)
	@Size(max = 255, message = "代码超长，最多255个字符")
	private String code;

    /**
    * 名称
    * 
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;

    /**
    * 类型
    * 持续型,数值型
    */
    @Column(name = "type", nullable = false, updatable = true)
	@Size(max = 255, message = "类型超长，最多255个字符")
	private String type;

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

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
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

