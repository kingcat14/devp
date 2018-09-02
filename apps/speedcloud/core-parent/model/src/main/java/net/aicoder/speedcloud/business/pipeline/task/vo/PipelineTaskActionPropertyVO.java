package net.aicoder.speedcloud.business.pipeline.task.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskActionVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 操作属性的值对象
*/
@ApiModel(value = "展现操作属性的值对象")
public class PipelineTaskActionPropertyVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**所属操作*/
    @ApiModelProperty(value = "所属操作", notes = "")
    private Long taskType;
    private PipelineTaskActionVO taskTypeVO;


    /**属性名称*/
    @ApiModelProperty(value = "属性名称")
    private String name;


    /**属性代码*/
    @ApiModelProperty(value = "属性代码")
    private String code;


    /**属性值*/
    @ApiModelProperty(value = "属性值", notes = "执行时最终使用的值")
    private String value;


    @ApiModelProperty(value = "排序")
    private Integer viewOrder;


    /**类型*/
    @ApiModelProperty(value = "类型")
    private String type;


    public Long getTaskType(){
        return taskType;
    }
    public void setTaskType(Long taskType) {
        this.taskType = taskType;
    }
    public PipelineTaskActionVO getTaskTypeVO(){
        return taskTypeVO;
    }
    public void setTaskTypeVO(PipelineTaskActionVO taskTypeVO) {
        this.taskTypeVO = taskTypeVO;
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCode(){
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getValue(){
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public Integer getViewOrder(){
        return viewOrder;
    }
    public void setViewOrder(Integer viewOrder) {
        this.viewOrder = viewOrder;
    }

    public String getType(){
        return type;
    }
    public void setType(String type) {
        this.type = type;
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