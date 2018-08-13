package com.yunkang.saas.platform.business.platform.application.domain;

import com.yunkang.saas.common.jpa.SaaSEntity;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotEmpty;
import com.yunkang.saas.common.framework.eo.GenericBaseEntity;



/**
 * 应用密码
 * @author icode
 */
@Entity
@Table(appliesTo = "application_password", comment = "[应用密码]")
//@DynamicUpdate
//@DynamicInsert
public class ApplicationPassword extends SaaSEntity{

	public static final String PROPERTY_ACCESS_ID = "accessId";
	public static final String PROPERTY_ACCESS_KEY = "accessKey";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 访问ID
    * 
    */
    @Column(name = "access_id")
	@NotNull(message = "访问ID不能为空")
	@Size(max = 255, message = "访问ID超长，最多255个字符")
	private String accessId;

    /**
    * 访问密码
    * 
    */
    @Column(name = "access_key")
	@NotNull(message = "访问密码不能为空")
	@Size(max = 255, message = "访问密码超长，最多255个字符")
	private String accessKey;

	public String getAccessId(){
		return accessId;
	}
	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}

	public String getAccessKey(){
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
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

