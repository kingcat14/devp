package net.aicoder.speedcloud.business.deploy.domain;

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
 * 部署方案
 * @author icode
 */
@Entity
@Table(appliesTo = "devp_sys_dpy_scheme", comment = "[部署方案]")
//@DynamicUpdate
//@DynamicInsert
public class DevpSysDpyScheme extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_VERSION = "version";
	public static final String PROPERTY_VER_POSTFIX = "verPostfix";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_NOTES = "notes";
	public static final String PROPERTY_PROJECT = "project";
	public static final String PROPERTY_ENV = "env";


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
    * 方案名称
    * [方案名称]
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "方案名称超长，最多255个字符")
	private String name;

    /**
    * 方案代码
    * [方案代码]
    */
    @Column(name = "code", nullable = false, updatable = true)
	@Size(max = 255, message = "方案代码超长，最多255个字符")
	private String code;

    /**
    * 方案别名
    * [方案别名]
    */
    @Column(name = "alias", nullable = true, updatable = true)
	@Size(max = 255, message = "方案别名超长，最多255个字符")
	private String alias;

    /**
    * 方案描述
    * [方案描述]
    */
    @Column(name = "description", nullable = true, updatable = true, length=1999, columnDefinition = "TEXT")
	private String description;

    /**
    * 方案类型
    * [类型]-开发,测试,验证,生产
    */
    @Column(name = "type", nullable = true, updatable = true)
	@Size(max = 255, message = "方案类型超长，最多255个字符")
	private String type;

    /**
    * 版本
    * [版本]
    */
    @Column(name = "version", nullable = true, updatable = true)
	@Size(max = 255, message = "版本超长，最多255个字符")
	private String version;

    /**
    * 版本标识后缀
    * [版本标识后缀]
    */
    @Column(name = "ver_postfix", nullable = true, updatable = true)
	@Size(max = 255, message = "版本标识后缀超长，最多255个字符")
	private String verPostfix;

    /**
    * 已生效
    * [状态]
    */
    @Column(name = "status", nullable = true, updatable = true)
	private Boolean status;

    /**
    * 备注
    * [备注]
    */
    @Column(name = "notes", nullable = true, updatable = true)
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String notes;

    /**
    * 所属项目（产品）
    * [所属项目]
    */
    @Column(name = "project", nullable = false, updatable = true)
	private Long project;

    /**
    * 所属环境
    * [所属环境]
    */
    @Column(name = "env", nullable = false, updatable = true)
	private Long env;

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

	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getAlias(){
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getVersion(){
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	public String getVerPostfix(){
		return verPostfix;
	}
	public void setVerPostfix(String verPostfix) {
		this.verPostfix = verPostfix;
	}

	public Boolean getStatus(){
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Long getProject(){
		return project;
	}
	public void setProject(Long project) {
		this.project = project;
	}

	public Long getEnv(){
		return env;
	}
	public void setEnv(Long env) {
		this.env = env;
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

