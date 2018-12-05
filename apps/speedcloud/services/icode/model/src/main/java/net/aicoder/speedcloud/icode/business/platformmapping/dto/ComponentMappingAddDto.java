package net.aicoder.speedcloud.icode.business.platformmapping.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 组件映射
 * @author icode
 */
@ApiModel(value = "新增组件映射使用的DTO")
@Setter @Getter
public class ComponentMappingAddDto {

    /**组件*/
	@ApiModelProperty(value = "组件", required = false)
	private String component;

    /**平台组件*/
	@ApiModelProperty(value = "平台组件", required = false)
	private String platformComponentName;

    /**平台组件ID*/
	@ApiModelProperty(value = "平台组件ID", required = false)
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
