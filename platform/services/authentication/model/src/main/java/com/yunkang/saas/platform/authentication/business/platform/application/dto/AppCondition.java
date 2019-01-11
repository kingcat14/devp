package com.yunkang.saas.platform.authentication.business.platform.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

@ApiModel(value = "查询应用使用的DTO")
@Getter @Setter
public class AppCondition {

	@ApiModelProperty(value = "应用名称")
	private String name;
	@ApiModelProperty(value = "应用代码")
	private String code;
    @ApiModelProperty(value = "应用类别")
    private Long appCategoryId;
	@ApiModelProperty(value = "标签", notes = "应用的标签，逗号分隔，可填写多个")
	private String label;
	@ApiModelProperty(value = "已启用")
	private Boolean enable;
	@ApiModelProperty(value = "上架时间")
	private Date onBoardTime;
	@ApiModelProperty(value = "起始上架时间")
	private Date onBoardTimeStart;
	@ApiModelProperty(value = "结束上架时间")
	private Date onBoardTimeEnd;
	@ApiModelProperty(value = "应用链接", notes = "跳转至对应的应用")
	private String url;
	@ApiModelProperty(value = "描述")
	private String description;
	@ApiModelProperty(value = "可见")
	private Boolean visible;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
