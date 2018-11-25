package net.aicoder.speedcloud.icode.business.tplfile.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询公共代码模板使用的DTO")
@Getter @Setter
public class TplCodeCondition {

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "模板代码", notes = "")
	private String code;
	@ApiModelProperty(value = "模板名称", notes = "")
	private String name;
	@ApiModelProperty(value = "模板类型", notes = "")
	private String type;
	@ApiModelProperty(value = "模板内容", notes = "")
	private String content;
	@ApiModelProperty(value = "文件名称", notes = "")
	private String fileName;
	@ApiModelProperty(value = "文件路径", notes = "")
	private String filePath;
	@ApiModelProperty(value = "模板集合", notes = "")
	private String tplSet;
	@ApiModelProperty(value = "接受的模型类型", notes = "")
	private String acceptModelType;
	@ApiModelProperty(value = "是否可覆盖", notes = "")
	private Boolean overridable;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
