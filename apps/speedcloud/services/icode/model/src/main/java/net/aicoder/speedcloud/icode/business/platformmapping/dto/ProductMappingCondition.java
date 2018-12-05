package net.aicoder.speedcloud.icode.business.platformmapping.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询产品映射使用的DTO")
@Getter @Setter
public class ProductMappingCondition {

    @ApiModelProperty(value = "产品")
    private String product;
	@ApiModelProperty(value = "平台产品名称")
	private String platformProductName;
	@ApiModelProperty(value = "平台产品ID")
	private String platformProductId;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
