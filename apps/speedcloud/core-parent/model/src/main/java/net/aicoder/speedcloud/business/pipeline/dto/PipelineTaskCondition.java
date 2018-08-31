package net.aicoder.speedcloud.business.pipeline.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询任务使用的DTO")
public class PipelineTaskCondition extends SaaSCondition{

    @ApiModelProperty(value = "所属阶段")
    private Long stage;
	@ApiModelProperty(value = "执行排序")
	private Integer execOrder;
	@ApiModelProperty(value = "执行排序最大值")
	private Integer execOrderMax;
	@ApiModelProperty(value = "执行排序最小值")
	private Integer execOrderMin;
    @ApiModelProperty(value = "任务类型")
    private Long taskType;


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

	public Integer getExecOrderMin(){
		return execOrderMin;
	}
	public void setExecOrderMin(Integer execOrderMin) {
		this.execOrderMin = execOrderMin;
	}

	public Integer getExecOrderMax(){
		return execOrderMax;
	}
	public void setExecOrderMax(Integer execOrderMax) {
		this.execOrderMax = execOrderMax;
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
