package net.aicoder.speedcloud.business.pipeline.template.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询操作模板使用的DTO")
@Getter @Setter
public class PipelineTemplateTaskActionCondition {

    @ApiModelProperty(value = "租户id")
    private Long tid;
    @ApiModelProperty(value = "所属任务")
    private String task;
	@ApiModelProperty(value = "操作说明")
	private String memo;
	@ApiModelProperty(value = "操作名称")
	private String name;
	@ApiModelProperty(value = "执行顺序")
	private Integer execIndex;
	@ApiModelProperty(value = "执行顺序最大值")
	private Integer execIndexMax;
	@ApiModelProperty(value = "执行顺序最小值")
	private Integer execIndexMin;
    @ApiModelProperty(value = "操作类型")
    private Long type;
	@ApiModelProperty(value = "脚本内容", notes = "脚本内容")
	private String content;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
