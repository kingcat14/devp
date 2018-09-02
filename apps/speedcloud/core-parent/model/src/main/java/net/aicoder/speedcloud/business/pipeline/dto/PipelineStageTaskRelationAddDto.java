package net.aicoder.speedcloud.business.pipeline.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 阶段任务关联
 * @author icode
 */
@ApiModel(value = "新增阶段任务关联使用的DTO")
public class PipelineStageTaskRelationAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**阶段*/
	@ApiModelProperty(value = "阶段", required = false)
	private Long stage;

    /**任务*/
	@ApiModelProperty(value = "任务", required = false)
	private Long task;

    /**执行排序*/
	@ApiModelProperty(value = "执行排序", required = false)
	private Integer execOrder;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getStage(){
        return stage;
    }
    public void setStage(Long stage) {
        this.stage = stage;
    }

	public Long getTask(){
        return task;
    }
    public void setTask(Long task) {
        this.task = task;
    }

	public Integer getExecOrder(){
		return execOrder;
	}
	public void setExecOrder(Integer execOrder) {
		this.execOrder = execOrder;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
