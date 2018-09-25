package net.aicoder.speedcloud.business.pipeline.exec.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 运行实例节点
 * @author icode
 */
@ApiModel(value = "新增运行实例节点使用的DTO")
public class PipelineExecNodeAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**编号*/
	@ApiModelProperty(value = "编号", required = false)
	private String code;

    /**节点名称*/
	@ApiModelProperty(value = "节点名称", required = false, notes = "节点或任务的名称")
	private String name;

    /**节点类型*/
	@ApiModelProperty(value = "节点类型", required = false, notes = "阶段、任务")
	private String nodeType;

    /**执行方式*/
	@ApiModelProperty(value = "执行方式", required = false, notes = "并行、串行")
	private String execMode;

    /**运行状态*/
	@ApiModelProperty(value = "运行状态", required = false, notes = "未开始、等待中、运行中、已结束")
	private String status;

    /**运行结果*/
	@ApiModelProperty(value = "运行结果", required = false, notes = "成功、失败")
	private String result;

    /**所属实例*/
	@ApiModelProperty(value = "所属实例", required = false)
	private Long exec;

    /**结果消息*/
	@ApiModelProperty(value = "结果消息", required = false)
	private String resultMessage;

    /**开始时间*/
	@ApiModelProperty(value = "开始时间", required = false)
	private Date startTime;

    /**上级节点*/
	@ApiModelProperty(value = "上级节点", required = false)
	private String parentId;

    /**关联任务*/
	@ApiModelProperty(value = "关联任务", required = false)
	private Long task;

    /**自动运行*/
	@ApiModelProperty(value = "自动运行", required = false, notes = "手动、自动")
	private Boolean autoStart;

    /**节点排序*/
	@ApiModelProperty(value = "节点排序", required = false)
	private Integer execIndex;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
