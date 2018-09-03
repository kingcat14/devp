package net.aicoder.speedcloud.business.pipeline.exec.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 运行计划的值对象
*/
@ApiModel(value = "展现运行计划的值对象")
public class PipelineExecInstanceVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**编号*/
    @ApiModelProperty(value = "编号")
    private String code;


    @ApiModelProperty(value = "运行主体", notes = "")
    private Long executeTargetId;


    /**运行类型*/
    @ApiModelProperty(value = "运行类型", notes = "流水线、任务")
    private String executeTargetType;


    /**运行状态*/
    @ApiModelProperty(value = "运行状态", notes = "未开始、运行中、已结束")
    private String status;


    /**运行结果*/
    @ApiModelProperty(value = "运行结果", notes = "成功、失败")
    private String result;


    /**开始时间*/
    @ApiModelProperty(value = "开始时间")
    private Date startTime;


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