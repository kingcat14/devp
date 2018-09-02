package net.aicoder.speedcloud.business.pipeline.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageVO;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 阶段任务关联的值对象
*/
@ApiModel(value = "展现阶段任务关联的值对象")
public class PipelineStageTaskRelationVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**阶段*/
    @ApiModelProperty(value = "阶段")
    private Long stage;
    private PipelineStageVO stageVO;


    /**任务*/
    @ApiModelProperty(value = "任务")
    private Long task;
    private PipelineTaskVO taskVO;


    @ApiModelProperty(value = "执行排序")
    private Integer execOrder;


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

    public Long getTask(){
        return task;
    }
    public void setTask(Long task) {
        this.task = task;
    }
    public PipelineTaskVO getTaskVO(){
        return taskVO;
    }
    public void setTaskVO(PipelineTaskVO taskVO) {
        this.taskVO = taskVO;
    }

    public Integer getExecOrder(){
        return execOrder;
    }
    public void setExecOrder(Integer execOrder) {
        this.execOrder = execOrder;
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