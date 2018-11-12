package com.yunkang.saas.platform.monitor.business.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter @Setter
@ApiModel(value = "查询程序实例使用的DTO")
public class ApplicationInstanceCondition {

    @ApiModelProperty(value = "app")
    private String app;
	@ApiModelProperty(value = "ip")
	private String ip;
	@ApiModelProperty(value = "port")
	private Integer port;
	@ApiModelProperty(value = "port最大值")
	private Integer portMax;
	@ApiModelProperty(value = "port最小值")
	private Integer portMin;
	@ApiModelProperty(value = "运行中")
	private Boolean alive;
	@ApiModelProperty(value = "停运告警")
	private Boolean alarm;
	@ApiModelProperty(value = "最近停运时间", notes = "最近停运时间")
	private Date stopTime;
	@ApiModelProperty(value = "起始最近停运时间")
	private Date stopTimeStart;
	@ApiModelProperty(value = "结束最近停运时间")
	private Date stopTimeEnd;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
