package net.aicoder.speedcloud.business.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询应用私密配置使用的DTO")
@Getter @Setter
public class SecurityConfigCondition {

    @ApiModelProperty(value = "租户id")
    private Long tid;
    @ApiModelProperty(value = "应用")
    private String app;
	@ApiModelProperty(value = "配置名")
	private String itemName;
	@ApiModelProperty(value = "配置值")
	private String itemValue;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
