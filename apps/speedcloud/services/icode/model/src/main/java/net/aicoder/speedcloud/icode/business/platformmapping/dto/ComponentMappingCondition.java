package net.aicoder.speedcloud.icode.business.platformmapping.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询组件映射使用的DTO")
@Getter @Setter
public class ComponentMappingCondition {

    @ApiModelProperty(value = "组件")
    private String component;
	@ApiModelProperty(value = "平台组件")
	private String platformComponentName;
	@ApiModelProperty(value = "平台组件ID")
	private String platformComponentId;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
