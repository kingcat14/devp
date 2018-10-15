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
 * 开发语言
 * @author icode
 */
@Entity
@Table(appliesTo = "config_develop_language", comment = "[开发语言]")
//@DynamicUpdate
//@DynamicInsert
public class ConfigDevelopLanguage extends BaseEntity<Long>{

	public static final String PROPERTY_NAME = "name";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 名称
    * 
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

