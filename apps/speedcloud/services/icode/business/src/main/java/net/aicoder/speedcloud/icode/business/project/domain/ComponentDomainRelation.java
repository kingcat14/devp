package net.aicoder.speedcloud.icode.business.project.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 组件-领域-关系
 * @author icode
 */
@Entity()
@Table(name = "project_component_domain_relation")
//@DynamicUpdate
//@DynamicInsert
public class ComponentDomainRelation extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_COMPONENT = "component";
	public static final String PROPERTY_DOMAIN = "domain";
	public static final String PROPERTY_RELATION_TYPE = "relationType";


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
    * 系统组件
    * 
    */
    @Column(name = "component", nullable = false, updatable = true)
	@Size(max = 255, message = "系统组件超长，最多255个字符")
	private String component;

    /**
    * 领域
    * 
    */
    @Column(name = "domain", nullable = false, updatable = true)
	@Size(max = 255, message = "领域超长，最多255个字符")
	private String domain;

    /**
    * 关系类型
    * 引用领域业务、实现领域业务
    */
    @Column(name = "relation_type", nullable = true, updatable = true)
	@Size(max = 255, message = "关系类型超长，最多255个字符")
	private String relationType;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getComponent(){
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}

	public String getDomain(){
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getRelationType(){
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
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

