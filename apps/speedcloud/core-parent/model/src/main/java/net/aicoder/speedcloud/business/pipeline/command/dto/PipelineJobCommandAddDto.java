package net.aicoder.speedcloud.business.pipeline.command.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 创建Job指令
 * @author icode
 */
@ApiModel(value = "新增创建Job指令使用的DTO")
public class PipelineJobCommandAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**关联任务*/
	@ApiModelProperty(value = "关联任务", required = false)
	private Long task;

    /**创建时间*/
	@ApiModelProperty(value = "创建时间", required = false)
	private Date createTime;

    /**状态*/
	@ApiModelProperty(value = "状态", required = false, notes = "未执行")
	private String status;

    /**之类类型*/
	@ApiModelProperty(value = "之类类型", required = false, notes = "新增修改删除")
	private String type;

    /**执行结果*/
	@ApiModelProperty(value = "执行结果", required = false)
	private String result;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getTask(){
        return task;
    }
    public void setTask(Long task) {
        this.task = task;
    }

	public Date getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getResult(){
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
