package net.aicoder.speedcloud.business.pipeline.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageVO;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineTaskTypeVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 任务的值对象
*/
@ApiModel(value = "展现任务的值对象")
public class PipelineTaskVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**所属阶段*/
    @ApiModelProperty(value = "所属阶段")
    private Long stage;
    private PipelineStageVO stageVO;


    @ApiModelProperty(value = "执行排序")
    private Integer execOrder;


    /**任务类型*/
    @ApiModelProperty(value = "任务类型")
    private Long taskType;
    private PipelineTaskTypeVO taskTypeVO;


    public Long getStage(){
        return stage;
    }
    public void setStage(Long stage) {
        this.stage = stage;
    }
    public PipelineStageVO getStageVO(){
        return stageVO;
    }
    public void setStageVO(PipelineStageVO stageVO) {
        this.stageVO = stageVO;
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
    public PipelineTaskTypeVO getTaskTypeVO(){
        return taskTypeVO;
    }
    public void setTaskTypeVO(PipelineTaskTypeVO taskTypeVO) {
        this.taskTypeVO = taskTypeVO;
    }


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}