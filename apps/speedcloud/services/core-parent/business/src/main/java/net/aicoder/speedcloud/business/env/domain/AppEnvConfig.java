package net.aicoder.speedcloud.business.env.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;



/**
 * 应用环境
 * @author icode
 */
@Entity
@Table(appliesTo = "app_env_config", comment = "[应用环境]")
//@DynamicUpdate
//@DynamicInsert
public class AppEnvConfig extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_LEVEL = "level";
	public static final String PROPERTY_PROJECT = "project";
	public static final String PROPERTY_SEQ = "seq";


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
    * 环境名称
    * 
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "环境名称超长，最多255个字符")
	private String name;

    /**
    * 环境级别
    * 
    */
    @Column(name = "level", nullable = false, updatable = true)
	@Size(max = 255, message = "环境级别超长，最多255个字符")
	private String level;

    /**
    * 所属项目（产品）
    * 
    */
    @Column(name = "project", nullable = true, updatable = true)
	private Long project;

    /**
    * 顺序号
    * 
    */
    @Column(name = "seq", nullable = true, updatable = true)
	private Integer seq;

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

	public String getLevel(){
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}

	public Long getProject(){
		return project;
	}
	public void setProject(Long project) {
		this.project = project;
	}

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
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

