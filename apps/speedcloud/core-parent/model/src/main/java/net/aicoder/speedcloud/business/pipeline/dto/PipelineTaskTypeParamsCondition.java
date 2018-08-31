package net.aicoder.speedcloud.business.pipeline.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询任务类型参数定义使用的DTO")
public class PipelineTaskTypeParamsCondition extends SaaSCondition{

    @ApiModelProperty(value = "所属任务类型")
    private Long taskType;
	@ApiModelProperty(value = "参数名称")
	private String name;
	@ApiModelProperty(value = "参数代码")
	private String code;
	@ApiModelProperty(value = "展现顺序")
	private Integer viewOrder;
	@ApiModelProperty(value = "展现顺序最大值")
	private Integer viewOrderMax;
	@ApiModelProperty(value = "展现顺序最小值")
	private Integer viewOrderMin;


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

	public Integer getViewOrderMin(){
		return viewOrderMin;
	}
	public void setViewOrderMin(Integer viewOrderMin) {
		this.viewOrderMin = viewOrderMin;
	}

	public Integer getViewOrderMax(){
		return viewOrderMax;
	}
	public void setViewOrderMax(Integer viewOrderMax) {
		this.viewOrderMax = viewOrderMax;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
