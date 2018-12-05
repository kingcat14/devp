package net.aicoder.speedcloud.business.deployscheme.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询部署资源类别使用的DTO")
@Getter @Setter
public class ResourceCategoryCondition {

    @ApiModelProperty(value = "租户id")
    private Long tid;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "代码")
	private String code;
	@ApiModelProperty(value = "图标")
	private String icon;
	@ApiModelProperty(value = "排序")
	private Integer idx;
	@ApiModelProperty(value = "排序最大值")
	private Integer idxMax;
	@ApiModelProperty(value = "排序最小值")
	private Integer idxMin;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
