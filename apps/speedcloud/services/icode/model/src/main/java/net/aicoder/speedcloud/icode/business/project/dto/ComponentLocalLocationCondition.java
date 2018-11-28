package net.aicoder.speedcloud.icode.business.project.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询组件本地路径使用的DTO")
@Getter @Setter
public class ComponentLocalLocationCondition {

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "用户id")
	private Long accountId;
    @ApiModelProperty(value = "组件")
    private String component;
	@ApiModelProperty(value = "本地路径")
	private String location;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
