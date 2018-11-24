package net.aicoder.speedcloud.icode.business.domain.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 领域
 * @author icode
 */
@Entity()
@Table(name = "domain_domain")
//@DynamicUpdate
//@DynamicInsert
public class Domain extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_PARENT = "parent";
	public static final String PROPERTY_PREFIX = "prefix";


    @Id
    @Column(name = "id")
    private String id;


    /**
    * 租户id
    * 
    */
    @Column(name = "tid", nullable = false, updatable = false)
	@Size(max = 255, message = "租户id超长，最多255个字符")
	private String tid;

    /**
    * 领域名称
    * 
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "领域名称超长，最多255个字符")
	private String name;

    /**
    * 领域代码
    * 
    */
    @Column(name = "code", nullable = false, updatable = false)
	@Size(max = 255, message = "领域代码超长，最多255个字符")
	private String code;

    /**
    * 父领域
    * 
    */
    @Column(name = "parent", nullable = true, updatable = true)
	@Size(max = 255, message = "父领域超长，最多255个字符")
	private String parent;

    /**
    * 领域代码前缀
    * 附领域为空时，必须填该字段
    */
    @Column(name = "prefix", nullable = true, updatable = true)
	@Size(max = 255, message = "领域代码前缀超长，最多255个字符")
	private String prefix;

	public String getTid(){
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
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

	public String getParent(){
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getPrefix(){
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
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

