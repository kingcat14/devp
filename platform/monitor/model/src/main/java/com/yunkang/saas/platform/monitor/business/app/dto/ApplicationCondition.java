package com.yunkang.saas.platform.monitor.business.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter @Setter
@ApiModel(value = "查询程序使用的DTO")
public class ApplicationCondition {

	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "代码")
	private String code;
	@ApiModelProperty(value = "配置实例数量")
	private Integer totalCount;
	@ApiModelProperty(value = "配置实例数量最大值")
	private Integer totalCountMax;
	@ApiModelProperty(value = "配置实例数量最小值")
	private Integer totalCountMin;
	@ApiModelProperty(value = "当前实例数量")
	private Integer aliveCount;
	@ApiModelProperty(value = "当前实例数量最大值")
	private Integer aliveCountMax;
	@ApiModelProperty(value = "当前实例数量最小值")
	private Integer aliveCountMin;
	@ApiModelProperty(value = "低实例告警", notes = "")
	private Boolean alarm;
	@ApiModelProperty(value = "启动监控")
	private Boolean enable;
	@ApiModelProperty(value = "告警数量")
	private Integer thresholdValue;
	@ApiModelProperty(value = "告警数量最大值")
	private Integer thresholdValueMax;
	@ApiModelProperty(value = "告警数量最小值")
	private Integer thresholdValueMin;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
