package net.aicoder.speedcloud.business.deployscheme.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 方案资源
 * @author icode
 */
@ApiModel(value = "修改方案资源使用的DTO")
@Getter
@Setter
public class ResourceEditDto {


	/**资源名称*/
	@ApiModelProperty(value = "资源名称", required = false, notes = "[资源名称]")
	private String name;


	/**资源代码*/
	@ApiModelProperty(value = "资源代码", required = false, notes = "[资源代码]")
	private String code;


	/**资源别名*/
	@ApiModelProperty(value = "资源别名", required = false, notes = "[资源别名]")
	private String alias;


	/**资源类别*/
	@ApiModelProperty(value = "资源类别", required = false)
	private String category;


	/**资源类型*/
	@ApiModelProperty(value = "资源类型", required = false, notes = "[类型]-运行环境/数据库/消息队列/缓存/外部接口")
	private String type;


	/**备注*/
	@ApiModelProperty(value = "备注", required = false, notes = "[备注]")
	private String notes;


	/**资源描述*/
	@ApiModelProperty(value = "资源描述", required = false, notes = "[资源描述]")
	private String description;


	/**版本*/
	@ApiModelProperty(value = "版本", required = false, notes = "[版本]")
	private String version;


	/**顺序号*/
	@ApiModelProperty(value = "顺序号", required = false)
	private Integer seq;


	/**所属环境*/
	@ApiModelProperty(value = "所属环境", required = false)
	private String env;


	/**状态*/
	@ApiModelProperty(value = "状态", required = false, notes = "[状态]")
	private String status;


	/**产品编号*/
	@ApiModelProperty(value = "产品编号", required = false, notes = "[产品编号]")
	private String project;


	/**外部资源*/
	@ApiModelProperty(value = "外部资源", required = false)
	private Boolean outerResource;


	/**所属方案*/
	@ApiModelProperty(value = "所属方案", required = false)
	private Long scheme;




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
