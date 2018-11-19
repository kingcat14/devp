package net.aicoder.speedcloud.business.pipeline.command.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;




/**
* 创建Job指令的值对象
*/
@ApiModel(value = "展现创建Job指令的值对象")
public class PipelineJobCommandVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**关联任务*/
    @ApiModelProperty(value = "关联任务")
    private Long task;
    private PipelineTaskVO taskVO;


    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    /**状态*/
    @ApiModelProperty(value = "状态", notes = "未执行")
    private String status;


    /**之类类型*/
    @ApiModelProperty(value = "之类类型", notes = "新增修改删除")
    private String type;


    /**执行结果*/
    @ApiModelProperty(value = "执行结果")
    private String result;


    public Long getTask(){
        return task;
    }
    public void setTask(Long task) {
        this.task = task;
    }
    public PipelineTaskVO getTaskVO(){
        return taskVO;
    }
    public void setTaskVO(PipelineTaskVO taskVO) {
        this.taskVO = taskVO;
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