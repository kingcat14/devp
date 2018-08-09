package com.yunkang.saas.platform.business.platform.tenant.domain;

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
 * 租户类型
 * @author icode
 */
@Entity
@Table(appliesTo = "tenant_type", comment = "[租户类型]")
//@DynamicUpdate
//@DynamicInsert
public class TenantType extends GenericBaseEntity<Long>{

	public static final String PROPERTY_TENANT_TYPE_CODE = "tenantTypeCode";
	public static final String PROPERTY_NAME = "name";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 租户类型编码
    * 
    */
    @Column(name = "tenant_type_code")
	@NotNull(message = "租户类型编码不能为空")
	@Size(max = 255, message = "租户类型编码超长，最多255个字符")
	private String tenantTypeCode;

    /**
    * 租户类型名称
    * 
    */
    @Column(name = "name")
	@NotNull(message = "租户类型名称不能为空")
	@Size(max = 255, message = "租户类型名称超长，最多255个字符")
	private String name;

	public String getTenantTypeCode(){
		return tenantTypeCode;
	}
	public void setTenantTypeCode(String tenantTypeCode) {
		this.tenantTypeCode = tenantTypeCode;
	}

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

