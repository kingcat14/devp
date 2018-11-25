package net.aicoder.speedcloud.business.pipeline.task.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询操作使用的DTO")
public class PipelineTaskActionCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
    @ApiModelProperty(value = "所属任务")
    private Long task;
	@ApiModelProperty(value = "操作名称")
	private String name;
	@ApiModelProperty(value = "操作说明")
	private String memo;
	@ApiModelProperty(value = "执行顺序")
	private Integer execIndex;
	@ApiModelProperty(value = "执行顺序最大值")
	private Integer execIndexMax;
	@ApiModelProperty(value = "执行顺序最小值")
	private Integer execIndexMin;
    @ApiModelProperty(value = "操作类型")
    private Long type;
	@ApiModelProperty(value = "脚本内容", notes = "脚本内容")
	private String content;


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
