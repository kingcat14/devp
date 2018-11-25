package net.aicoder.speedcloud.business.app.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询应用开发配置使用的DTO")
@Setter @Getter
public class AppDevelopConfigCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
    @ApiModelProperty(value = "应用")
    private Long app;
    @ApiModelProperty(value = "代码", notes = "")
    private Long code;
	@ApiModelProperty(value = "测试环境DB")
	private String testDatabase;
	@ApiModelProperty(value = "测试环境域名")
	private String testDomainName;
	@ApiModelProperty(value = "生产环境DB")
	private String productionDatabase;
	@ApiModelProperty(value = "生产环境域名")
	private String productionDomainName;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
