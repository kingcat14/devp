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
 * 运行实例
 * @author icode
 */
@ApiModel(value = "修改运行实例使用的DTO")
public class ExecEditDto {


	/**编号*/
	@ApiModelProperty(value = "编号", required = false)
	private String code;


	/**运行主体*/
	@ApiModelProperty(value = "运行主体", required = false, notes = "")
	private Long runnerId;


	/**运行类型*/
	@ApiModelProperty(value = "运行类型", required = false, notes = "流水线、任务")
	private String runnerType;


	/**运行状态*/
	@ApiModelProperty(value = "运行状态", required = false, notes = "未开始、运行中、已结束")
	private String status;


	/**运行结果*/
	@ApiModelProperty(value = "运行结果", required = false, notes = "成功、失败")
	private String result;


	/**开始时间*/
	@ApiModelProperty(value = "开始时间", required = false)
	private Date startTime;



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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
