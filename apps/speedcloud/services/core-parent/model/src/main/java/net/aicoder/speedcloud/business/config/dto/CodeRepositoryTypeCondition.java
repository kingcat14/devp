package net.aicoder.speedcloud.business.config.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询代码库类型使用的DTO")
@Getter @Setter
public class CodeRepositoryTypeCondition {

	@ApiModelProperty(value = "代码")
	private String code;
	@ApiModelProperty(value = "名称")
	private String name;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
