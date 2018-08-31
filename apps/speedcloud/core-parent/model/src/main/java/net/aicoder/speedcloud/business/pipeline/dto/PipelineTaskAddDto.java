package net.aicoder.speedcloud.business.pipeline.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 任务
 * @author icode
 */
@ApiModel(value = "新增任务使用的DTO")
public class PipelineTaskAddDto {

    /**任务名称*/
	@ApiModelProperty(value = "任务名称", required = true)
	private String name;

    /**所属阶段*/
	@ApiModelProperty(value = "所属阶段", required = true)
	private Long stage;

    /**执行排序*/
	@ApiModelProperty(value = "执行排序", required = false)
	private Integer execOrder;

    /**任务类型*/
	@ApiModelProperty(value = "任务类型", required = true)
	private Long taskType;


	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Long getStage(){
        return stage;
    }
    public void setStage(Long stage) {
        this.stage = stage;
    }

	public Integer getExecOrder(){
		return execOrder;
	}
	public void setExecOrder(Integer execOrder) {
		this.execOrder = execOrder;
	}

	public Long getTaskType(){
        return taskType;
    }
    public void setTaskType(Long taskType) {
        this.taskType = taskType;
    }


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
