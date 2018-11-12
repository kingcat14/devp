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

    @ApiModelProperty(value = "应用")
    private String app;
	@ApiModelProperty(value = "主机")
	private String host;
	@ApiModelProperty(value = "端口")
	private Integer port;
	@ApiModelProperty(value = "端口最大值")
	private Integer portMax;
	@ApiModelProperty(value = "端口最小值")
	private Integer portMin;
	@ApiModelProperty(value = "运行中")
	private Boolean alive;
	@ApiModelProperty(value = "停运告警")
	private Boolean alarm;
	@ApiModelProperty(value = "最近活跃时间", notes = "最近活跃时间")
	private Date aliveTime;
	@ApiModelProperty(value = "起始最近活跃时间")
	private Date aliveTimeStart;
	@ApiModelProperty(value = "结束最近活跃时间")
	private Date aliveTimeEnd;
	@ApiModelProperty(value = "最近检测时间")
	private Date detectionTime;
	@ApiModelProperty(value = "起始最近检测时间")
	private Date detectionTimeStart;
	@ApiModelProperty(value = "结束最近检测时间")
	private Date detectionTimeEnd;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
