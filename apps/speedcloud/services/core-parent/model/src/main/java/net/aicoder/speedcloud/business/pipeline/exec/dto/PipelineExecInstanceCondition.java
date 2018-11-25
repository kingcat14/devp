package net.aicoder.speedcloud.business.pipeline.exec.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


@ApiModel(value = "查询运行计划使用的DTO")
public class PipelineExecInstanceCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "编号")
	private String code;
	@ApiModelProperty(value = "运行主体", notes = "")
	private Long executeTargetId;
	@ApiModelProperty(value = "运行主体最大值")
	private Long executeTargetIdMax;
	@ApiModelProperty(value = "运行主体最小值")
	private Long executeTargetIdMin;
	@ApiModelProperty(value = "运行类型", notes = "流水线、任务")
	private String executeTargetType;
	@ApiModelProperty(value = "运行状态", notes = "未开始、运行中、已结束")
	private String status;
	@ApiModelProperty(value = "运行结果", notes = "成功、失败")
	private String result;
	@ApiModelProperty(value = "开始时间")
	private Date startTime;
	@ApiModelProperty(value = "起始开始时间")
	private Date startTimeStart;
	@ApiModelProperty(value = "结束开始时间")
	private Date startTimeEnd;


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


	public Long getExecuteTargetId(){
		return executeTargetId;
	}
	public void setExecuteTargetId(Long executeTargetId) {
		this.executeTargetId = executeTargetId;
	}

	public Long getExecuteTargetIdMin(){
		return executeTargetIdMin;
	}
	public void setExecuteTargetIdMin(Long executeTargetIdMin) {
		this.executeTargetIdMin = executeTargetIdMin;
	}

	public Long getExecuteTargetIdMax(){
		return executeTargetIdMax;
	}
	public void setExecuteTargetIdMax(Long executeTargetIdMax) {
		this.executeTargetIdMax = executeTargetIdMax;
	}


	public String getExecuteTargetType(){
		return executeTargetType;
	}
	public void setExecuteTargetType(String executeTargetType) {
		this.executeTargetType = executeTargetType;
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




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
