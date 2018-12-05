package net.aicoder.speedcloud.business.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询应用类型使用的DTO")
@Getter @Setter
public class ApplicationTypeCondition {

    @ApiModelProperty(value = "租户id")
    private Long tid;
	@ApiModelProperty(value = "排序")
	private Integer idx;
	@ApiModelProperty(value = "排序最大值")
	private Integer idxMax;
	@ApiModelProperty(value = "排序最小值")
	private Integer idxMin;
	@ApiModelProperty(value = "代码")
	private String code;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "种类")
	private String category;
	@ApiModelProperty(value = "默认图标")
	private String icon;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
