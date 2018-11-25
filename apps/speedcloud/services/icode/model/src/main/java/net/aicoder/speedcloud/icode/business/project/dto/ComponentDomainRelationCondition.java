package net.aicoder.speedcloud.icode.business.project.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询组件-领域-关系使用的DTO")
@Getter @Setter
public class ComponentDomainRelationCondition {

	@ApiModelProperty(value = "租户id")
	private Long tid;
    @ApiModelProperty(value = "系统组件")
    private String component;
    @ApiModelProperty(value = "领域")
    private String domain;
	@ApiModelProperty(value = "关系类型", notes = "引用领域业务、实现领域业务")
	private String relationType;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
