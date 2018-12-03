package com.yunkang.saas.platform.authentication.business.platform.application.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 应用类别
 * @author icode
 */
@Entity()
@Table(name = "platform_config_app_category")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class ConfigAppCategory extends BaseEntity<Long>{

	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 类别名称
    * 
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "类别名称超长，最多255个字符")
	private String name;

    /**
    * 类别代码
    * 
    */
    @Column(name = "code", nullable = true, updatable = true)
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

