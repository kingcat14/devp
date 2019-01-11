package net.aicoder.speedcloud.business.pipeline.jenkins.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询JenkinsAdapter使用的DTO")
@Getter @Setter
public class JenkinsAdapterCondition {

    @ApiModelProperty(value = "租户id")
    private Long tid;
    @ApiModelProperty(value = "所属产品")
    private String project;
    @ApiModelProperty(value = "所属环境")
    private String env;
	@ApiModelProperty(value = "端口")
	private Integer port;
	@ApiModelProperty(value = "端口最大值")
	private Integer portMax;
	@ApiModelProperty(value = "端口最小值")
	private Integer portMin;
	@ApiModelProperty(value = "IP")
	private String host;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
