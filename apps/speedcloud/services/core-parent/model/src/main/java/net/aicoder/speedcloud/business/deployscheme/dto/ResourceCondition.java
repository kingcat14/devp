package net.aicoder.speedcloud.business.deployscheme.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询方案资源使用的DTO")
@Setter @Getter
public class ResourceCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "资源名称", notes = "[资源名称]")
	private String name;
	@ApiModelProperty(value = "资源代码", notes = "[资源代码]")
	private String code;
	@ApiModelProperty(value = "资源别名", notes = "[资源别名]")
	private String alias;
    @ApiModelProperty(value = "资源类别")
    private String category;
    @ApiModelProperty(value = "资源类型", notes = "[类型]-运行环境/数据库/消息队列/缓存/外部接口")
    private String type;
	@ApiModelProperty(value = "备注", notes = "[备注]")
	private String notes;
	@ApiModelProperty(value = "资源描述", notes = "[资源描述]")
	private String description;
	@ApiModelProperty(value = "版本", notes = "[版本]")
	private String version;
	@ApiModelProperty(value = "顺序号")
	private Integer seq;
	@ApiModelProperty(value = "顺序号最大值")
	private Integer seqMax;
	@ApiModelProperty(value = "顺序号最小值")
	private Integer seqMin;
    @ApiModelProperty(value = "所属环境")
    private String env;
	@ApiModelProperty(value = "状态", notes = "[状态]")
	private String status;
    @ApiModelProperty(value = "产品编号", notes = "[产品编号]")
    private String project;
	@ApiModelProperty(value = "外部资源")
	private Boolean outerResource;
    @ApiModelProperty(value = "所属方案")
    private Long scheme;


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
