package com.yunkang.saas.platform.monitor.business.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter @Setter
@ApiModel(value = "查询未知程序使用的DTO")
public class UnknownAppCondition {

	@ApiModelProperty(value = "代码")
	private String code;
	@ApiModelProperty(value = "发现时间")
	private Date registerTime;
	@ApiModelProperty(value = "起始发现时间")
	private Date registerTimeStart;
	@ApiModelProperty(value = "结束发现时间")
	private Date registerTimeEnd;
	@ApiModelProperty(value = "当前活跃")
	private Boolean alive;
	@ApiModelProperty(value = "当前数量")
	private Integer aliveCount;
	@ApiModelProperty(value = "当前数量最大值")
	private Integer aliveCountMax;
	@ApiModelProperty(value = "当前数量最小值")
	private Integer aliveCountMin;
	@ApiModelProperty(value = "最大数量")
	private Integer maxCount;
	@ApiModelProperty(value = "最大数量最大值")
	private Integer maxCountMax;
	@ApiModelProperty(value = "最大数量最小值")
	private Integer maxCountMin;
	@ApiModelProperty(value = "状态", notes = "有效，已移除")
	private String status;
	@ApiModelProperty(value = "忽略")
	private Boolean ignored;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
