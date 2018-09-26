package net.aicoder.speedcloud.business.pipeline.exec.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.*;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询运行实例节点使用的DTO")
public class PipelineExecNodeCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "租户id最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户id最小值")
	private Long tidMin;
	@ApiModelProperty(value = "编号")
	private String code;
	@ApiModelProperty(value = "节点名称", notes = "节点或任务的名称")
	private String name;
	@ApiModelProperty(value = "节点类型", notes = "流水线、阶段、任务")
	private String nodeType;
	@ApiModelProperty(value = "执行方式", notes = "并行、串行")
	private String execMode;
	@ApiModelProperty(value = "运行状态", notes = "未开始、等待中、运行中、已结束")
	private String status;
	@ApiModelProperty(value = "运行结果", notes = "成功、失败")
	private String result;
    @ApiModelProperty(value = "所属实例")
    private Long exec;
	@ApiModelProperty(value = "结果消息")
	private String resultMessage;
	@ApiModelProperty(value = "开始时间")
	private Date startTime;
	@ApiModelProperty(value = "起始开始时间")
	private Date startTimeStart;
	@ApiModelProperty(value = "结束开始时间")
	private Date startTimeEnd;
	@ApiModelProperty(value = "上级节点")
	private String parentId;
	@ApiModelProperty(value = "关联阶段节点", notes = "")
	private Long stageNode;
	@ApiModelProperty(value = "关联阶段节点最大值")
	private Long stageNodeMax;
	@ApiModelProperty(value = "关联阶段节点最小值")
	private Long stageNodeMin;
	@ApiModelProperty(value = "关联对象ID", notes = "具体流水线、阶段、任务的ID")
	private Long relationObjId;
	@ApiModelProperty(value = "关联对象ID最大值")
	private Long relationObjIdMax;
	@ApiModelProperty(value = "关联对象ID最小值")
	private Long relationObjIdMin;
	@ApiModelProperty(value = "自动运行", notes = "手动、自动")
	private Boolean autoStart;
	@ApiModelProperty(value = "节点排序")
	private Integer execIndex;
	@ApiModelProperty(value = "节点排序最大值")
	private Integer execIndexMax;
	@ApiModelProperty(value = "节点排序最小值")
	private Integer execIndexMin;


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
	public Date getStartTimeStart(){
		return startTimeStart;
	}
	public void setStartTimeStart(Date startTimeStart) {
		this.startTimeStart = startTimeStart;
	}
	public Date getStartTimeEnd(){
		return startTimeEnd;
	}
	public void setStartTimeEnd(Date startTimeEnd) {
		this.startTimeEnd = startTimeEnd;
	}


	public String getParentId(){
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


	public Long getStageNode(){
		return stageNode;
	}
	public void setStageNode(Long stageNode) {
		this.stageNode = stageNode;
	}

	public Long getStageNodeMin(){
		return stageNodeMin;
	}
	public void setStageNodeMin(Long stageNodeMin) {
		this.stageNodeMin = stageNodeMin;
	}

	public Long getStageNodeMax(){
		return stageNodeMax;
	}
	public void setStageNodeMax(Long stageNodeMax) {
		this.stageNodeMax = stageNodeMax;
	}


	public Long getRelationObjId(){
		return relationObjId;
	}
	public void setRelationObjId(Long relationObjId) {
		this.relationObjId = relationObjId;
	}

	public Long getRelationObjIdMin(){
		return relationObjIdMin;
	}
	public void setRelationObjIdMin(Long relationObjIdMin) {
		this.relationObjIdMin = relationObjIdMin;
	}

	public Long getRelationObjIdMax(){
		return relationObjIdMax;
	}
	public void setRelationObjIdMax(Long relationObjIdMax) {
		this.relationObjIdMax = relationObjIdMax;
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

	public Integer getExecIndexMin(){
		return execIndexMin;
	}
	public void setExecIndexMin(Integer execIndexMin) {
		this.execIndexMin = execIndexMin;
	}

	public Integer getExecIndexMax(){
		return execIndexMax;
	}
	public void setExecIndexMax(Integer execIndexMax) {
		this.execIndexMax = execIndexMax;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
