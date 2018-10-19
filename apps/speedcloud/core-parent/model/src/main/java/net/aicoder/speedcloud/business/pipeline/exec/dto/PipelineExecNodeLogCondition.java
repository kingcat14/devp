package net.aicoder.speedcloud.business.pipeline.exec.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询运行节点日志使用的DTO")
public class PipelineExecNodeLogCondition extends SaaSCondition{

	@ApiModelProperty(value = "log")
	private String log;


	public String getLog(){
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
