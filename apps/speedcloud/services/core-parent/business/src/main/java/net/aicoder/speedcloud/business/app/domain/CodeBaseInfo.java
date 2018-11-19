package net.aicoder.speedcloud.business.app.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;



/**
 * 代码库详细信息
 * @author icode
 */
@Entity
@Table(appliesTo = "code_base_info", comment = "[代码库详细信息]")
//@DynamicUpdate
//@DynamicInsert
public class CodeBaseInfo extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_CODE_REPERTORY = "codeRepertory";
	public static final String PROPERTY_LANGUAGE = "language";
	public static final String PROPERTY_LANGUAGE_LEVEL = "languageLevel";


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
    * 代码库
    *  
    */
    @Column(name = "code_repertory", nullable = true, updatable = true)
	private Long codeRepertory;

    /**
    * 开发语言
    * 
    */
    @Column(name = "language", nullable = true, updatable = true)
	@Size(max = 255, message = "开发语言超长，最多255个字符")
	private String language;

    /**
    * 语言级别
    * 
    */
    @Column(name = "language_level", nullable = true, updatable = true)
	@Size(max = 255, message = "语言级别超长，最多255个字符")
	private String languageLevel;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getCodeRepository(){
		return codeRepertory;
	}
	public void setCodeRepository(Long codeRepertory) {
		this.codeRepertory = codeRepertory;
	}

	public String getLanguage(){
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguageLevel(){
		return languageLevel;
	}
	public void setLanguageLevel(String languageLevel) {
		this.languageLevel = languageLevel;
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

