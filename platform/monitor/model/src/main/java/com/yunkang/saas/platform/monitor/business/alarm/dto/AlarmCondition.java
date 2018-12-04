package com.yunkang.saas.platform.monitor.business.alarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

@ApiModel(value = "查询告警使用的DTO")
@Getter @Setter
public class AlarmCondition {

	@ApiModelProperty(value = "代码")
	private String code;
	@ApiModelProperty(value = "名称")
	private String name;
    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "程序")
    private String app;
    @ApiModelProperty(value = "指标")
    private String counter;
	@ApiModelProperty(value = "指标值")
	private String value;
	@ApiModelProperty(value = "状态", notes = "已确认、已取消、已失效")
	private String status;
	@ApiModelProperty(value = "发生时间")
	private Date occurTime;
	@ApiModelProperty(value = "起始发生时间")
	private Date occurTimeStart;
	@ApiModelProperty(value = "结束发生时间")
	private Date occurTimeEnd;
	@ApiModelProperty(value = "最后发生时间")
	private Date lastOccurTime;
	@ApiModelProperty(value = "起始最后发生时间")
	private Date lastOccurTimeStart;
	@ApiModelProperty(value = "结束最后发生时间")
	private Date lastOccurTimeEnd;
	@ApiModelProperty(value = "消失时间")
	private Date disappearTime;
	@ApiModelProperty(value = "起始消失时间")
	private Date disappearTimeStart;
	@ApiModelProperty(value = "结束消失时间")
	private Date disappearTimeEnd;
	@ApiModelProperty(value = "发生次数")
	private Integer occurCount;
	@ApiModelProperty(value = "发生次数最大值")
	private Integer occurCountMax;
	@ApiModelProperty(value = "发生次数最小值")
	private Integer occurCountMin;
	@ApiModelProperty(value = "内容", notes = "告警内容")
	private String content;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
