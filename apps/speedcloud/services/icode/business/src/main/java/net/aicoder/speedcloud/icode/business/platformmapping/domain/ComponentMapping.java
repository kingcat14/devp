package net.aicoder.speedcloud.icode.business.platformmapping.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 组件映射
 * @author icode
 */
@Entity()
@Table(name = "platformmapping_component_mapping")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class ComponentMapping extends BaseEntity<String>{

	public static final String PROPERTY_COMPONENT = "component";
	public static final String PROPERTY_PLATFORM_COMPONENT_NAME = "platformComponentName";
	public static final String PROPERTY_PLATFORM_COMPONENT_ID = "platformComponentId";


    @Id
    @Column(name = "id", length = 32)
    private String id;


    /**
    * 组件
    * 
    */
    @Column(name = "component", nullable = false, updatable = true)
	@Size(max = 255, message = "组件超长，最多255个字符")
	private String component;

    /**
    * 平台组件
    * 
    */
    @Column(name = "platform_component_name", nullable = false, updatable = true)
	@Size(max = 255, message = "平台组件超长，最多255个字符")
	private String platformComponentName;

    /**
    * 平台组件ID
    * 
    */
    @Column(name = "platform_component_id", nullable = false, updatable = true)
	@Size(max = 255, message = "平台组件ID超长，最多255个字符")
	private String platformComponentId;

	public String getComponent(){
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}

	public String getPlatformComponentName(){
		return platformComponentName;
	}
	public void setPlatformComponentName(String platformComponentName) {
		this.platformComponentName = platformComponentName;
	}

	public String getPlatformComponentId(){
		return platformComponentId;
	}
	public void setPlatformComponentId(String platformComponentId) {
		this.platformComponentId = platformComponentId;
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

