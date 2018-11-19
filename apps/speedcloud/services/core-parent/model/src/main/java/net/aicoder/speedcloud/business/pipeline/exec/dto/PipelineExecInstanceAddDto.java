package net.aicoder.speedcloud.business.pipeline.exec.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;


/**
 * 运行计划
 * @author icode
 */
@ApiModel(value = "新增运行计划使用的DTO")
public class PipelineExecInstanceAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**编号*/
	@ApiModelProperty(value = "编号", required = false)
	private String code;

    /**运行主体*/
	@ApiModelProperty(value = "运行主体", required = false, notes = "")
	private Long executeTargetId;

    /**运行类型*/
	@ApiModelProperty(value = "运行类型", required = false, notes = "流水线、任务")
	private String executeTargetType;

    /**运行状态*/
	@ApiModelProperty(value = "运行状态", required = false, notes = "未开始、运行中、已结束")
	private String status;

    /**运行结果*/
	@ApiModelProperty(value = "运行结果", required = false, notes = "成功、失败")
	private String result;

    /**开始时间*/
	@ApiModelProperty(value = "开始时间", required = false)
	private Date startTime;

	private List<PipelineExecNodeParamAddDto> paramList;


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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
