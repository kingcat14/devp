package net.aicoder.speedcloud.icode.business.domain.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 领域对象
 * @author icode
 */
@javax.persistence.Entity()
@Table(name = "domain_entity")
//@DynamicUpdate
//@DynamicInsert
public class Entity extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_VIEW_INDEX = "viewIndex";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_PARENT_ENTITY = "parentEntity";
	public static final String PROPERTY_AGGREGATE = "aggregate";
	public static final String PROPERTY_DOMAIN = "domain";


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
    * 实体代码
    * 
    */
    @Column(name = "code", nullable = false, updatable = true)
	@Size(max = 255, message = "实体代码超长，最多255个字符")
	private String code;

    /**
    * 实体名称
    * 
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "实体名称超长，最多255个字符")
	private String name;

    /**
    * 排序
    * 
    */
    @Column(name = "view_index", nullable = true, updatable = true)
	private Integer viewIndex;

    /**
    * 描述
    * 
    */
    @Column(name = "description", nullable = true, updatable = true)
	@Size(max = 255, message = "描述超长，最多255个字符")
	private String description;

    /**
    * 父对象
    * 
    */
    @Column(name = "parent_entity", nullable = true, updatable = true)
	@Size(max = 255, message = "父对象超长，最多255个字符")
	private String parentEntity;

    /**
    * 所属聚合
    * 
    */
    @Column(name = "aggregate", nullable = true, updatable = true)
	@Size(max = 255, message = "所属聚合超长，最多255个字符")
	private String aggregate;

    /**
    * 所属领域
    * 
    */
    @Column(name = "domain", nullable = true, updatable = true)
	@Size(max = 255, message = "所属领域超长，最多255个字符")
	private String domain;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

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

	public Integer getViewIndex(){
		return viewIndex;
	}
	public void setViewIndex(Integer viewIndex) {
		this.viewIndex = viewIndex;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentEntity(){
		return parentEntity;
	}
	public void setParentEntity(String parentEntity) {
		this.parentEntity = parentEntity;
	}

	public String getAggregate(){
		return aggregate;
	}
	public void setAggregate(String aggregate) {
		this.aggregate = aggregate;
	}

	public String getDomain(){
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
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

