package net.aicoder.speedcloud.business.pipeline.exec.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 运行节点日志
 * @author icode
 */
@ApiModel(value = "新增运行节点日志使用的DTO")
public class PipelineExecNodeLogAddDto {

    /**log*/
	@ApiModelProperty(value = "log", required = false)
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
