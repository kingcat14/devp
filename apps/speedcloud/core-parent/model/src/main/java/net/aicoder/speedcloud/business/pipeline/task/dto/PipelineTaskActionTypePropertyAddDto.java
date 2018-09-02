package net.aicoder.speedcloud.business.pipeline.task.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 操作类型属性定义
 * @author icode
 */
@ApiModel(value = "新增操作类型属性定义使用的DTO")
public class PipelineTaskActionTypePropertyAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**所属任务类型*/
	@ApiModelProperty(value = "所属任务类型", required = false)
	private Long taskType;

    /**属性名称*/
	@ApiModelProperty(value = "属性名称", required = false)
	private String name;

    /**属性代码*/
	@ApiModelProperty(value = "属性代码", required = false)
	private String code;

    /**展现顺序*/
	@ApiModelProperty(value = "展现顺序", required = true)
	private Integer viewOrder;

    /**属性类型*/
	@ApiModelProperty(value = "属性类型", required = false)
    private String type;

    /**可选值*/
	@ApiModelProperty(value = "可选值", required = false, notes = "类型为枚举时使用的值")
	private String optionValue;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

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

    public String getType(){
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

	public String getOptionValue(){
		return optionValue;
	}
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
