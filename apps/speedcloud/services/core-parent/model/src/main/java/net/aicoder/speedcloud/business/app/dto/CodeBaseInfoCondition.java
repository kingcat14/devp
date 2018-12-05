package net.aicoder.speedcloud.business.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询代码基本信息使用的DTO")
@Getter @Setter
public class CodeBaseInfoCondition {

    @ApiModelProperty(value = "租户id")
    private Long tid;
    @ApiModelProperty(value = "代码库", notes = " ")
    private String codeRepository;
	@ApiModelProperty(value = "开发语言")
	private String language;
	@ApiModelProperty(value = "语言级别")
	private String languageLevel;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
