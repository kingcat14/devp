package net.aicoder.speedcloud.business.pipeline.task.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询任务使用的DTO")
public class PipelineTaskCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "租户id最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户id最小值")
	private Long tidMin;
	@ApiModelProperty(value = "任务名称")
	private String name;
	@ApiModelProperty(value = "任务类型")
	private String taskType;
	@ApiModelProperty(value = "执行计划", notes = "手工，每日，每周")
	private String execType;
	@ApiModelProperty(value = "执行开始时间", notes = "数据格式 HH:ss")
	private String taskStartTime;
	@ApiModelProperty(value = "执行日", notes = "1,2,3,4,5,6,7")
	private String taskDayOfWeeks;
	@ApiModelProperty(value = "任务描述")
	private String description;
    @ApiModelProperty(value = "所属产品")
    private Long project;


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


	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public String getTaskType(){
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}


	public String getExecType(){
		return execType;
	}
	public void setExecType(String execType) {
		this.execType = execType;
	}


	public String getTaskStartTime(){
		return taskStartTime;
	}
	public void setTaskStartTime(String taskStartTime) {
		this.taskStartTime = taskStartTime;
	}


	public String getTaskDayOfWeeks(){
		return taskDayOfWeeks;
	}
	public void setTaskDayOfWeeks(String taskDayOfWeeks) {
		this.taskDayOfWeeks = taskDayOfWeeks;
	}


	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


    public Long getProject(){
        return project;
    }
    public void setProject(Long project) {
        this.project = project;
    }




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
