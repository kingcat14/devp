package net.aicoder.speedcloud.business.deployscheme.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;



/**
 * 方案资源
 * @author icode
 */
@Entity(name = "deployscheme_resource")
@Table(appliesTo = "deployscheme_resource", comment = "[方案资源]")
//@DynamicUpdate
//@DynamicInsert
public class Resource extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_CATEGORY = "category";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_NOTES = "notes";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_VERSION = "version";
	public static final String PROPERTY_SEQ = "seq";
	public static final String PROPERTY_ENV = "env";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_PROJECT = "project";
	public static final String PROPERTY_OUTER_RESOURCE = "outerResource";
	public static final String PROPERTY_SCHEME = "scheme";


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
    * 资源名称
    * [资源名称]
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "资源名称超长，最多255个字符")
	private String name;

    /**
    * 资源代码
    * [资源代码]
    */
    @Column(name = "code", nullable = false, updatable = true)
	@Size(max = 255, message = "资源代码超长，最多255个字符")
	private String code;

    /**
    * 资源别名
    * [资源别名]
    */
    @Column(name = "alias", nullable = true, updatable = true)
	@Size(max = 255, message = "资源别名超长，最多255个字符")
	private String alias;

    /**
    * 资源类别
    * 
    */
    @Column(name = "category", nullable = true, updatable = true)
	private String category;

    /**
    * 资源类型
    * [类型]-运行环境/数据库/消息队列/缓存/外部接口
    */
    @Column(name = "type", nullable = true, updatable = true)
	private String type;

    /**
    * 备注
    * [备注]
    */
    @Column(name = "notes", nullable = true, updatable = true)
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String notes;

    /**
    * 资源描述
    * [资源描述]
    */
    @Column(name = "description", nullable = true, updatable = true, length=1999, columnDefinition = "TEXT")
	private String description;

    /**
    * 版本
    * [版本]
    */
    @Column(name = "version", nullable = true, updatable = true)
	@Size(max = 255, message = "版本超长，最多255个字符")
	private String version;

    /**
    * 顺序号
    * 
    */
    @Column(name = "seq", nullable = true, updatable = true)
	private Integer seq;

    /**
    * 所属环境
    * 
    */
    @Column(name = "env", nullable = true, updatable = true)
	private String env;

    /**
    * 状态
    * [状态]
    */
    @Column(name = "status", nullable = true, updatable = true)
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;

    /**
    * 产品编号
    * [产品编号]
    */
    @Column(name = "project", nullable = false, updatable = true)
	private String project;

    /**
    * 外部资源
    * 
    */
    @Column(name = "outer_resource", nullable = false, updatable = true)
	private Boolean outerResource;

    /**
    * 所属方案
    * 
    */
    @Column(name = "scheme", nullable = false, updatable = false)
	private Long scheme;

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

	public String getCategory(){
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion(){
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getProject(){
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}

	public Boolean getOuterResource(){
		return outerResource;
	}
	public void setOuterResource(Boolean outerResource) {
		this.outerResource = outerResource;
	}

	public Long getScheme(){
		return scheme;
	}
	public void setScheme(Long scheme) {
		this.scheme = scheme;
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

