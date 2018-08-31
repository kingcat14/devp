package net.aicoder.speedcloud.business.pipeline.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 任务类型
 * @author icode
 */
@ApiModel(value = "修改任务类型使用的DTO")
public class PipelineTaskTypeEditDto {


	/**类型代码*/
	@ApiModelProperty(value = "类型代码", required = false)
	private String code;


	/**类型名称*/
	@ApiModelProperty(value = "类型名称", required = false)
	private String name;



	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}


	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
