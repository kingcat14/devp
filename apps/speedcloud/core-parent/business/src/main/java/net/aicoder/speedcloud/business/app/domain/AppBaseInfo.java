package net.aicoder.speedcloud.business.app.domain;

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
 * 应用
 * @author icode
 */
@Entity
@Table(appliesTo = "app_base_info", comment = "[应用]")
//@DynamicUpdate
//@DynamicInsert
public class AppBaseInfo extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_REGIST_TIME = "registTime";
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
    * 名称
    * 
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;

    /**
    * 应用类型
    * 
    */
    @Column(name = "type", nullable = true, updatable = true)
	@Size(max = 255, message = "应用类型超长，最多255个字符")
	private String type;

    /**
    * 状态
    * 
    */
    @Column(name = "status", nullable = true, updatable = true)
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;

    /**
    * 描述
    * 
    */
    @Column(name = "description", nullable = true, updatable = true, length=1999, columnDefinition = "TEXT")
	private String description;

    /**
    * 注册时间
    * 
    */
    @Column(name = "regist_time", nullable = true, updatable = true)
	@Size(max = 255, message = "注册时间超长，最多255个字符")
	private String registTime;

    /**
    * 所属项目
    * 
    */
    @Column(name = "project", nullable = false, updatable = true)
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

	public String getStatus(){
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegistTime(){
		return registTime;
	}
	public void setRegistTime(String registTime) {
		this.registTime = registTime;
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

