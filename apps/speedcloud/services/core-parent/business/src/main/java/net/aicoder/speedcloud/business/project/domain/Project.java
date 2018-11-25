package net.aicoder.speedcloud.business.project.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;



/**
 * 项目
 * @author icode
 */
@Entity
@Table(appliesTo = "project", comment = "[项目]")
//@DynamicUpdate
//@DynamicInsert
public class Project extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_SCOPE = "scope";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_PARENT = "parent";
	public static final String PROPERTY_PROJECT_SET = "projectSet";


    @Id
    @Column(name = "id")
    private String id;


    /**
    * 租户id
    * 
    */
    @Column(name = "tid", nullable = false, updatable = false)
	private Long tid;

    /**
    * 名称
    * 
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;

	/**代码*/
	@Column(name = "code", nullable = true, updatable = true)
	@Size(max = 255, message = "代码超长，最多255个字符")
	private String code;

    /**
    * 类型
    * 
    */
    @Column(name = "type", nullable = true, updatable = true)
	@Size(max = 255, message = "类型超长，最多255个字符")
	private String type;

    /**
    * 公开性
    * 
    */
    @Column(name = "scope", nullable = true, updatable = true)
	@Size(max = 255, message = "公开性超长，最多255个字符")
	private String scope;

    /**
    * 描述
    * 
    */
    @Column(name = "description", nullable = true, updatable = true, length=1999, columnDefinition = "TEXT")
	private String description;

    /**
    * 上级项目
    * 
    */
    @Column(name = "parent", nullable = true, updatable = true)
	@Size(max = 255, message = "上级项目超长，最多255个字符")
	private String parent;

    /**
    * 所属项目集
    * 
    */
    @Column(name = "project_set", nullable = true, updatable = true)
	private String projectSet;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
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

	public String getScope(){
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getParent(){
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getProjectSet(){
		return projectSet;
	}
	public void setProjectSet(String projectSet) {
		this.projectSet = projectSet;
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

