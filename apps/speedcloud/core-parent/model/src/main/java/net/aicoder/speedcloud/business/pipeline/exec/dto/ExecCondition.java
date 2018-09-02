package net.aicoder.speedcloud.business.pipeline.exec.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.*;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询运行实例使用的DTO")
public class ExecCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "租户id最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户id最小值")
	private Long tidMin;
	@ApiModelProperty(value = "编号")
	private String code;
	@ApiModelProperty(value = "运行主体", notes = "")
	private Long runnerId;
	@ApiModelProperty(value = "运行主体最大值")
	private Long runnerIdMax;
	@ApiModelProperty(value = "运行主体最小值")
	private Long runnerIdMin;
	@ApiModelProperty(value = "运行类型", notes = "流水线、任务")
	private String runnerType;
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


	public Long getRunnerId(){
		return runnerId;
	}
	public void setRunnerId(Long runnerId) {
		this.runnerId = runnerId;
	}

	public Long getRunnerIdMin(){
		return runnerIdMin;
	}
	public void setRunnerIdMin(Long runnerIdMin) {
		this.runnerIdMin = runnerIdMin;
	}

	public Long getRunnerIdMax(){
		return runnerIdMax;
	}
	public void setRunnerIdMax(Long runnerIdMax) {
		this.runnerIdMax = runnerIdMax;
	}


	public String getRunnerType(){
		return runnerType;
	}
	public void setRunnerType(String runnerType) {
		this.runnerType = runnerType;
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
