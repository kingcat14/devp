package net.aicoder.speedcloud.business.pipeline.task.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;
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


    /**任务名称*/
    @ApiModelProperty(value = "任务名称")
    private String name;


    /**任务类型*/
    @ApiModelProperty(value = "任务类型")
    private String taskType;
    private SimpleConfigVO taskTypeVO;


    /**执行计划*/
    @ApiModelProperty(value = "执行计划", notes = "手工，每日，每周")
    private String execType;
    private SimpleConfigVO execTypeVO;


    /**执行开始时间*/
    @ApiModelProperty(value = "执行开始时间", notes = "数据格式 HH:ss")
    private String taskStartTime;


    /**执行日*/
    @ApiModelProperty(value = "执行日", notes = "1,2,3,4,5,6,7")
    private String taskDayOfWeeks;


    /**任务描述*/
    @ApiModelProperty(value = "任务描述")
    private String description;


    /**所属产品*/
    @ApiModelProperty(value = "所属产品")
    private Long project;
    private ProjectVO projectVO;


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
    public SimpleConfigVO getTaskTypeVO(){
        return taskTypeVO;
    }
    public void setTaskTypeVO(SimpleConfigVO taskTypeVO) {
        this.taskTypeVO = taskTypeVO;
    }

    public String getExecType(){
        return execType;
    }
    public void setExecType(String execType) {
        this.execType = execType;
    }
    public SimpleConfigVO getExecTypeVO(){
        return execTypeVO;
    }
    public void setExecTypeVO(SimpleConfigVO execTypeVO) {
        this.execTypeVO = execTypeVO;
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
    public ProjectVO getProjectVO(){
        return projectVO;
    }
    public void setProjectVO(ProjectVO projectVO) {
        this.projectVO = projectVO;
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