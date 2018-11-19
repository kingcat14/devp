package com.yunkang.saas.platform.monitor.business.indicator.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

@Getter @Setter
@ApiModel(value = "查询指标值使用的DTO")
public class IndicatorValueCondition {

    @ApiModelProperty(value = "指标")
    private String indicator;
	@ApiModelProperty(value = "对象代码")
	private String target;
	@ApiModelProperty(value = "对象类型", notes = "应用、实例")
	private String targetType;
	@ApiModelProperty(value = "取值")
	private String value;
	@ApiModelProperty(value = "采集时间")
	private Date collectTime;
	@ApiModelProperty(value = "起始采集时间")
	private Date collectTimeStart;
	@ApiModelProperty(value = "结束采集时间")
	private Date collectTimeEnd;
	@ApiModelProperty(value = "入库时间")
	private Date saveTime;
	@ApiModelProperty(value = "起始入库时间")
	private Date saveTimeStart;
	@ApiModelProperty(value = "结束入库时间")
	private Date saveTimeEnd;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
