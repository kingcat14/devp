package net.aicoder.speedcloud.business.pipeline.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 任务参数值
 * @author icode
 */
@ApiModel(value = "修改任务参数值使用的DTO")
public class PipelineTaskParamValueEditDto {


	/**所属任务类型*/
	@ApiModelProperty(value = "所属任务类型", required = false)
	private Long taskType;


	/**参数名称*/
	@ApiModelProperty(value = "参数名称", required = false)
	private String name;


	/**参数代码*/
	@ApiModelProperty(value = "参数代码", required = false)
	private String code;


	/**展现顺序*/
	@ApiModelProperty(value = "展现顺序", required = false)
	private Integer viewOrder;



	public Long getTaskType(){
        return taskType;
    }
    public void setTaskType(Long taskType) {
        this.taskType = taskType;
    }


	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}


	public Integer getViewOrder(){
		return viewOrder;
	}
	public void setViewOrder(Integer viewOrder) {
		this.viewOrder = viewOrder;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
