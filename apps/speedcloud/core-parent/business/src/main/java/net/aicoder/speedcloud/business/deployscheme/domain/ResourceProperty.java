package net.aicoder.speedcloud.business.deployscheme.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotEmpty;



/**
 * 资源属性
 * @author icode
 */
@Entity(name = "deployscheme_resource_property")
@Table(appliesTo = "deployscheme_resource_property", comment = "[资源属性]")
//@DynamicUpdate
//@DynamicInsert
public class ResourceProperty extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_RESOURCE = "resource";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_VALUE = "value";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 租户id
    * 
    */
    @Column(name = "tid", nullable = false, updatable = false)
	private Long tid;

    /**
    * 所属资源
    * 
    */
    @Column(name = "resource", nullable = true, updatable = true)
	private Long resource;

    /**
    * 属性名称
    * 
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "属性名称超长，最多255个字符")
	private String name;

    /**
    * 属性代码
    * 
    */
    @Column(name = "code", nullable = false, updatable = true)
	@Size(max = 255, message = "属性代码超长，最多255个字符")
	private String code;

    /**
    * 属性值
    * 
    */
    @Column(name = "value", nullable = true, updatable = true)
	@Size(max = 255, message = "属性值超长，最多255个字符")
	private String value;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getResource(){
		return resource;
	}
	public void setResource(Long resource) {
		this.resource = resource;
	}

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

	public String getValue(){
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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

