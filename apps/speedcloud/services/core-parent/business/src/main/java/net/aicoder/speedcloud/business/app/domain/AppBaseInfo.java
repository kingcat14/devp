package net.aicoder.speedcloud.business.app.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 应用（系统）
 * @author icode
 */
@Entity()
@Table(name = "app_app_base_info")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class AppBaseInfo extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_PROJECT = "project";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_REGIST_TIME = "registTime";


    @Id
    @Column(name = "id", length = 32)
    private String id;


    /**
    * 租户id
    * 
    */
    @Column(name = "tid", nullable = false, updatable = false)
	private Long tid;

    /**
    * 所属项目
    * 
    */
    @Column(name = "project", nullable = false, updatable = true)
	@Size(max = 255, message = "所属项目超长，最多255个字符")
	private String project;

    /**
    * 应用类型
    * 
    */
    @Column(name = "type", nullable = false, updatable = true)
	@Size(max = 255, message = "应用类型超长，最多255个字符")
	private String type;

    /**
    * 名称
    * 
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;

    /**
    * 代码
    * 
    */
    @Column(name = "code", nullable = false, updatable = false)
	@Size(max = 255, message = "代码超长，最多255个字符")
	private String code;

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

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getProject(){
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
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

