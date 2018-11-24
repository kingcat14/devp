package net.aicoder.speedcloud.icode.business.tplfile.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter @Setter
@ApiModel(value = "查询公共代码模板集合使用的DTO")
public class TplSetCondition {

	@ApiModelProperty(value = "集合代码", notes = "")
	private String code;
	@ApiModelProperty(value = "集合名称", notes = "")
	private String name;
	@ApiModelProperty(value = "parent_id", notes = "")
	private String parentId;
	@ApiModelProperty(value = "集合类型", notes = "")
	private String type;
	@ApiModelProperty(value = "集合描述", notes = "")
	private String description;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
