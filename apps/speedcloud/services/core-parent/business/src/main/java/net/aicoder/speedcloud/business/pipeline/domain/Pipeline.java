package net.aicoder.speedcloud.business.pipeline.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;



/**
 * 流水线
 * @author icode
 */
@Entity
@Table(appliesTo = "pipeline", comment = "[流水线]")
//@DynamicUpdate
//@DynamicInsert
public class Pipeline extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_PROJECT = "project";


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
    * 流水线名称
    * 流水线名称
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "流水线名称超长，最多255个字符")
	private String name;

    /**
    * 类型
    * 
    */
    @Column(name = "type", nullable = true, updatable = true)
	private String type;

    /**
    * 所属产品
    * 
    */
    @Column(name = "project", nullable = false, updatable = false)
	private Long project;

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

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Long getProject(){
		return project;
	}
	public void setProject(Long project) {
		this.project = project;
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

