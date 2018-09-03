package net.aicoder.speedcloud.business.pipeline.task.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 操作
 * @author icode
 */
@ApiModel(value = "修改操作使用的DTO")
public class PipelineTaskActionEditDto {


	/**所属任务*/
	@ApiModelProperty(value = "所属任务", required = true)
	private Long task;


	/**操作名称*/
	@ApiModelProperty(value = "操作名称", required = false)
	private String name;


	/**操作说明*/
	@ApiModelProperty(value = "操作说明", required = false)
	private String memo;


	/**执行顺序*/
	@ApiModelProperty(value = "执行顺序", required = false)
	private Integer execIndex;


	/**操作类型*/
	@ApiModelProperty(value = "操作类型", required = false)
	private Long type;


	/**脚本内容*/
	@ApiModelProperty(value = "脚本内容", required = false, notes = "脚本内容")
	private String content;



	public Long getTask(){
        return task;
    }
    public void setTask(Long task) {
        this.task = task;
    }


	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public String getMemo(){
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}


	public Integer getExecIndex(){
		return execIndex;
	}
	public void setExecIndex(Integer execIndex) {
		this.execIndex = execIndex;
	}


	public Long getType(){
        return type;
    }
    public void setType(Long type) {
        this.type = type;
    }


	public String getContent(){
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
