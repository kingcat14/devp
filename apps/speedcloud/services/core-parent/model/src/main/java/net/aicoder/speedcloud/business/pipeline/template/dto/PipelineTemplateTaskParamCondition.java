package net.aicoder.speedcloud.business.pipeline.template.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询任务模板参数使用的DTO")
@Getter @Setter
public class PipelineTemplateTaskParamCondition {

    @ApiModelProperty(value = "租户id")
    private Long tid;
    @ApiModelProperty(value = "所属任务", notes = "")
    private String task;
	@ApiModelProperty(value = "参数名称")
	private String name;
	@ApiModelProperty(value = "参数类型", notes = "字符或者枚举")
	private String type;
	@ApiModelProperty(value = "默认值")
	private String defaultValue;
	@ApiModelProperty(value = "展现顺序")
	private Integer viewOrder;
	@ApiModelProperty(value = "展现顺序最大值")
	private Integer viewOrderMax;
	@ApiModelProperty(value = "展现顺序最小值")
	private Integer viewOrderMin;
	@ApiModelProperty(value = "参数描述")
	private String description;
	@ApiModelProperty(value = "可删除")
	private Boolean deletable;
	@ApiModelProperty(value = "可选值", notes = "类型为枚举时可选的值，分号分隔")
	private String enumValue;
	@ApiModelProperty(value = "参数值", notes = "执行时最终使用的值")
	private String value;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
