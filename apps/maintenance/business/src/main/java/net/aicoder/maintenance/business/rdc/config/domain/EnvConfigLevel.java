package net.aicoder.maintenance.business.rdc.config.domain;

import com.yunkang.saas.common.framework.eo.GenericBaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;


/**
 * 环境级别
 * @author icode
 */
@Entity
@Table(appliesTo = "env_config_level", comment = "[环境级别]")
//@DynamicUpdate
//@DynamicInsert
public class EnvConfigLevel extends GenericBaseEntity<String>{

	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_TYPE = "type";


    @Id
    @Column(name = "id")
    private String id;


    /**
    * 名称
    * 
    */
    @Column(name = "name")
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;

    /**
    * 代码
    * 
    */
    @Column(name = "code")
	@Size(max = 255, message = "代码超长，最多255个字符")
	private String code;

    /**
    * 类型
    * 环境类型：生成环境、测试环境（开发也是测试环境）
    */
    @Column(name = "type")
	@Size(max = 255, message = "类型超长，最多255个字符")
	private String type;

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

