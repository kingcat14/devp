package net.aicoder.speedcloud.icode.business.tplfile.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter @Setter
@ApiModel(value = "查询组件代码模板使用的DTO")
public class ProjectTplCodeCondition {

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "租户id最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户id最小值")
	private Long tidMin;
	@ApiModelProperty(value = "模板代码", notes = "")
	private String code;
	@ApiModelProperty(value = "模板名称", notes = "")
	private String name;
	@ApiModelProperty(value = "生成文件名", notes = "")
	private String fileName;
	@ApiModelProperty(value = "生成文件路径", notes = "相对路径")
	private String filePath;
	@ApiModelProperty(value = "模板内容", notes = "")
	private String content;
	@ApiModelProperty(value = "模板类型", notes = "")
	private String type;
	@ApiModelProperty(value = "接受的模型类型", notes = "")
	private String acceptModelType;
	@ApiModelProperty(value = "是否可覆盖", notes = "")
	private Integer overridable;
	@ApiModelProperty(value = "是否可覆盖最大值")
	private Integer overridableMax;
	@ApiModelProperty(value = "是否可覆盖最小值")
	private Integer overridableMin;
    @ApiModelProperty(value = "所属系统组件")
    private String component;
    @ApiModelProperty(value = "关联的代码模板")
    private String tplCode;
	@ApiModelProperty(value = "自动刷新", notes = "自动同步公共代码模板的更新")
	private Boolean autoUpdate;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
