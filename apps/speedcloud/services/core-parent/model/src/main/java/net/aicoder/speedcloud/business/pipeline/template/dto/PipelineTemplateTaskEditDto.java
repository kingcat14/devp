package net.aicoder.speedcloud.business.pipeline.template.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;


/**
 * 任务模板
 * @author icode
 */
@ApiModel(value = "修改任务模板使用的DTO")
@Setter @Getter
public class PipelineTemplateTaskEditDto {


	/**任务名称*/
	@ApiModelProperty(value = "任务名称", required = false)
	private String name;


	/**任务类型*/
	@ApiModelProperty(value = "任务类型", required = false)
	private String taskType;


	/**执行计划*/
	@ApiModelProperty(value = "执行计划", required = false, notes = "手工，每日，每周")
    private String execType;


	/**执行开始时间*/
	@ApiModelProperty(value = "执行开始时间", required = false, notes = "数据格式 HH:ss")
	private String taskStartTime;


	/**执行日*/
	@ApiModelProperty(value = "执行日", required = false, notes = "1,2,3,4,5,6,7")
	private String taskDayOfWeeks;


	/**任务描述*/
	@ApiModelProperty(value = "任务描述", required = false)
	private String description;

	/**步骤的操作列表*/
	@ApiModelProperty(value = "步骤的操作列表", required = true)
	List<PipelineTemplateTaskActionAddDto> actions;

	/**步骤的参数列表*/
	@ApiModelProperty(value = "步骤的参数列表", required = true)
	List<PipelineTemplateTaskParamAddDto> params;

	

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
