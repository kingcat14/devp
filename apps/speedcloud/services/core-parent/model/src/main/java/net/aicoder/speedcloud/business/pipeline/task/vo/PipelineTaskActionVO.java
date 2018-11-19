package net.aicoder.speedcloud.business.pipeline.task.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 操作的值对象
*/
@ApiModel(value = "展现操作的值对象")
public class PipelineTaskActionVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**所属任务*/
    @ApiModelProperty(value = "所属任务")
    private Long task;
    private PipelineTaskVO taskVO;


    /**操作名称*/
    @ApiModelProperty(value = "操作名称")
    private String name;


    /**操作说明*/
    @ApiModelProperty(value = "操作说明")
    private String memo;


    @ApiModelProperty(value = "执行顺序")
    private Integer execIndex;


    /**操作类型*/
    @ApiModelProperty(value = "操作类型")
    private Long type;
    private PipelineTaskActionTypeVO typeVO;


    /**脚本内容*/
    @ApiModelProperty(value = "脚本内容", notes = "脚本内容")
    private String content;


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
    public PipelineTaskActionTypeVO getTypeVO(){
        return typeVO;
    }
    public void setTypeVO(PipelineTaskActionTypeVO typeVO) {
        this.typeVO = typeVO;
    }

    public String getContent(){
        return content;
    }
    public void setContent(String content) {
        this.content = content;
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