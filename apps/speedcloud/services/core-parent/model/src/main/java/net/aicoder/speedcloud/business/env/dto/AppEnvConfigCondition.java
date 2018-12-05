package net.aicoder.speedcloud.business.env.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询产品环境使用的DTO")
@Getter @Setter
public class AppEnvConfigCondition {

    @ApiModelProperty(value = "租户id")
    private Long tid;
    @ApiModelProperty(value = "所属产品（项目）")
    private String project;
	@ApiModelProperty(value = "环境名称")
	private String name;
    @ApiModelProperty(value = "环境级别")
    private String level;
	@ApiModelProperty(value = "顺序号")
	private Integer seq;
	@ApiModelProperty(value = "顺序号最大值")
	private Integer seqMax;
	@ApiModelProperty(value = "顺序号最小值")
	private Integer seqMin;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
