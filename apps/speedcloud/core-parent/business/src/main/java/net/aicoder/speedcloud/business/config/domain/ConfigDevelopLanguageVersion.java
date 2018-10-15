package net.aicoder.speedcloud.business.config.domain;

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
 * 开发语言版本
 * @author icode
 */
@Entity
@Table(appliesTo = "config_develop_language_version", comment = "[开发语言版本]")
//@DynamicUpdate
//@DynamicInsert
public class ConfigDevelopLanguageVersion extends BaseEntity<Long>{

	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_LANGUAGE = "language";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 版本号
    * 
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "版本号超长，最多255个字符")
	private String name;

    /**
    * 开发语言
    * 
    */
    @Column(name = "language", nullable = true, updatable = true)
	private Long language;

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Long getLanguage(){
		return language;
	}
	public void setLanguage(Long language) {
		this.language = language;
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

