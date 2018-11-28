package net.aicoder.speedcloud.icode.business.project.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询组件使用的DTO")
@Getter @Setter
public class ComponentCondition {

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "租户id最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户id最小值")
	private Long tidMin;
    @ApiModelProperty(value = "所属产品")
    private String product;
	@ApiModelProperty(value = "组件编号", notes = "")
	private Integer number;
	@ApiModelProperty(value = "组件编号最大值")
	private Integer numberMax;
	@ApiModelProperty(value = "组件编号最小值")
	private Integer numberMin;
	@ApiModelProperty(value = "组件名称", notes = "")
	private String name;
	@ApiModelProperty(value = "组件代码", notes = "")
	private String code;
	@ApiModelProperty(value = "基础包", notes = "")
	private String basePackage;
    @ApiModelProperty(value = "代码模板", notes = "")
    private String tplSet;
	@ApiModelProperty(value = "描述", notes = "")
	private String description;
    @ApiModelProperty(value = "类型", notes = "IOS、ANDROID、WEB、应用、服务、公共组件")
    private String type;
	@ApiModelProperty(value = "可运行组件")
	private Boolean runnable;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
