package net.aicoder.speedcloud.business.app.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询代码库详细信息使用的DTO")
@Setter @Getter
public class CodeBaseInfoCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
    @ApiModelProperty(value = "代码库", notes = " ")
    private Long codeRepository;
	@ApiModelProperty(value = "开发语言")
	private String language;
	@ApiModelProperty(value = "语言级别")
	private String languageLevel;






	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
