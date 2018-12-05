package net.aicoder.speedcloud.business.config.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 代码库类型
 * @author icode
 */
@Entity()
@Table(name = "config_code_repository_type")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class CodeRepositoryType extends BaseEntity<String>{

	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";


    @Id
    @Column(name = "id", length = 32)
    private String id;


    /**
    * 代码
    * 
    */
    @Column(name = "code", nullable = false, updatable = false)
	@Size(max = 255, message = "代码超长，最多255个字符")
	private String code;

    /**
    * 名称
    * 
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "名称超长，最多255个字符")
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

