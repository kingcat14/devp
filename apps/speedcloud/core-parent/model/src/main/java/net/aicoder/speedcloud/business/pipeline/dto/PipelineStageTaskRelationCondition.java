package net.aicoder.speedcloud.business.pipeline.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询阶段任务关联使用的DTO")
public class PipelineStageTaskRelationCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "租户id最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户id最小值")
	private Long tidMin;
    @ApiModelProperty(value = "阶段")
    private Long stage;
    @ApiModelProperty(value = "任务")
    private Long task;
	@ApiModelProperty(value = "执行排序")
	private Integer execOrder;
	@ApiModelProperty(value = "执行排序最大值")
	private Integer execOrderMax;
	@ApiModelProperty(value = "执行排序最小值")
	private Integer execOrderMin;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getTidMin(){
		return tidMin;
	}
	public void setTidMin(Long tidMin) {
		this.tidMin = tidMin;
	}

	public Long getTidMax(){
		return tidMax;
	}
	public void setTidMax(Long tidMax) {
		this.tidMax = tidMax;
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




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
