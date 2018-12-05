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
 * 代码基本信息
 * @author icode
 */
@Entity()
@Table(name = "app_code_base_info")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class CodeBaseInfo extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_CODE_REPOSITORY = "codeRepository";
	public static final String PROPERTY_LANGUAGE = "language";
	public static final String PROPERTY_LANGUAGE_LEVEL = "languageLevel";


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
    * 代码库
    *  
    */
    @Column(name = "code_repository", nullable = true, updatable = true)
	@Size(max = 255, message = "代码库超长，最多255个字符")
	private String codeRepository;

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

	public String getCodeRepository(){
		return codeRepository;
	}
	public void setCodeRepository(String codeRepository) {
		this.codeRepository = codeRepository;
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

