package net.aicoder.speedcloud.business.pipeline.exec.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import net.aicoder.speedcloud.business.pipeline.exec.vo.ExecVO;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 运行实例节点的值对象
*/
@ApiModel(value = "展现运行实例节点的值对象")
public class ExecNodeVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**编号*/
    @ApiModelProperty(value = "编号")
    private String code;


    /**节点名称*/
    @ApiModelProperty(value = "节点名称", notes = "节点或任务的名称")
    private String name;


    /**节点类型*/
    @ApiModelProperty(value = "节点类型", notes = "阶段、任务")
    private String nodeType;


    /**执行方式*/
    @ApiModelProperty(value = "执行方式", notes = "并行、串行")
    private String execMode;


    /**运行状态*/
    @ApiModelProperty(value = "运行状态", notes = "未开始、等待中、运行中、已结束")
    private String status;


    /**运行结果*/
    @ApiModelProperty(value = "运行结果", notes = "成功、失败")
    private String result;


    /**所属实例*/
    @ApiModelProperty(value = "所属实例")
    private Long exec;
    private ExecVO execVO;


    /**结果消息*/
    @ApiModelProperty(value = "结果消息")
    private String resultMessage;


    /**开始时间*/
    @ApiModelProperty(value = "开始时间")
    private Date startTime;


    /**上级节点*/
    @ApiModelProperty(value = "上级节点")
    private String parentId;


    /**关联任务*/
    @ApiModelProperty(value = "关联任务")
    private Long task;
    private PipelineTaskVO taskVO;


    @ApiModelProperty(value = "自动运行", notes = "手动、自动")
    private Boolean autoStart;


    @ApiModelProperty(value = "节点排序")
    private Integer execIndex;


    public String getCode(){
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getNodeType(){
        return nodeType;
    }
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getExecMode(){
        return execMode;
    }
    public void setExecMode(String execMode) {
        this.execMode = execMode;
    }

    public String getStatus(){
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult(){
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }

    public Long getExec(){
        return exec;
    }
    public void setExec(Long exec) {
        this.exec = exec;
    }
    public ExecVO getExecVO(){
        return execVO;
    }
    public void setExecVO(ExecVO execVO) {
        this.execVO = execVO;
    }

    public String getResultMessage(){
        return resultMessage;
    }
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Date getStartTime(){
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getParentId(){
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public Boolean getAutoStart(){
        return autoStart;
    }
    public void setAutoStart(Boolean autoStart) {
        this.autoStart = autoStart;
    }

    public Integer getExecIndex(){
        return execIndex;
    }
    public void setExecIndex(Integer execIndex) {
        this.execIndex = execIndex;
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