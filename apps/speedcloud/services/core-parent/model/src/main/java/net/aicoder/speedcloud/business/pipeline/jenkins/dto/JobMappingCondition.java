package net.aicoder.speedcloud.business.pipeline.jenkins.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询任务映射使用的DTO")
@Getter @Setter
public class JobMappingCondition {

    @ApiModelProperty(value = "租户id")
    private Long tid;
	@ApiModelProperty(value = "类型", notes = "流水线、JOB")
	private String taskType;
	@ApiModelProperty(value = "任务或流水线")
	private String jobInPlatform;
	@ApiModelProperty(value = "任务或流水线名称")
	private String jobInPlatformName;
	@ApiModelProperty(value = "Jenkins中任务名称")
	private String jobInJenkinsName;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
