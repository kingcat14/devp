package net.aicoder.speedcloud.business.config.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询环境级别使用的DTO")
@Getter @Setter
public class EnvLevelCondition {

	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "代码")
	private String code;
	@ApiModelProperty(value = "类型", notes = "环境类型：生产环境、测试环境（开发也是测试环境）")
	private String type;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
