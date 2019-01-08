package net.aicoder.speedcloud.business.pipeline.template.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询任务模板使用的DTO")
@Getter @Setter
public class PipelineTemplateTaskCondition {

    @ApiModelProperty(value = "租户id")
    private Long tid;
	@ApiModelProperty(value = "任务名称")
	private String name;
    @ApiModelProperty(value = "任务类型")
    private String taskType;
	@ApiModelProperty(value = "执行计划", notes = "手工，每日，每周")
	private String execType;
	@ApiModelProperty(value = "执行开始时间", notes = "数据格式 HH:ss")
	private String taskStartTime;
	@ApiModelProperty(value = "执行日", notes = "1,2,3,4,5,6,7")
	private String taskDayOfWeeks;
	@ApiModelProperty(value = "任务描述")
	private String description;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
