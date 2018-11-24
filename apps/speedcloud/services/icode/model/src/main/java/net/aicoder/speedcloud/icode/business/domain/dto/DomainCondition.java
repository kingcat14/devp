package net.aicoder.speedcloud.icode.business.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter @Setter
@ApiModel(value = "查询领域使用的DTO")
public class DomainCondition {

	@ApiModelProperty(value = "租户id")
	private String tid;
	@ApiModelProperty(value = "领域名称")
	private String name;
	@ApiModelProperty(value = "领域代码")
	private String code;
	@ApiModelProperty(value = "父领域")
	private String parent;
	@ApiModelProperty(value = "领域代码前缀", notes = "附领域为空时，必须填该字段")
	private String prefix;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
