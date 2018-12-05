package net.aicoder.speedcloud.business.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询应用（系统）使用的DTO")
@Getter @Setter
public class AppBaseInfoCondition {

    @ApiModelProperty(value = "租户id")
    private Long tid;
    @ApiModelProperty(value = "所属项目")
    private String project;
    @ApiModelProperty(value = "应用类型")
    private String type;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "代码")
	private String code;
	@ApiModelProperty(value = "状态")
	private String status;
	@ApiModelProperty(value = "描述")
	private String description;
	@ApiModelProperty(value = "注册时间")
	private String registTime;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
