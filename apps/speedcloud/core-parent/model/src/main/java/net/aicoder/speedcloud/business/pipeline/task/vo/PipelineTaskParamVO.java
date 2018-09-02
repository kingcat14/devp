package net.aicoder.speedcloud.business.pipeline.task.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 任务参数的值对象
*/
@ApiModel(value = "展现任务参数的值对象")
public class PipelineTaskParamVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**所属任务*/
    @ApiModelProperty(value = "所属任务", notes = "")
    private Long taskType;
    private PipelineTaskVO taskTypeVO;


    /**参数名称*/
    @ApiModelProperty(value = "参数名称")
    private String name;


    /**参数类型*/
    @ApiModelProperty(value = "参数类型", notes = "字符或者枚举")
    private String type;


    /**默认值*/
    @ApiModelProperty(value = "默认值")
    private String defaultValue;


    @ApiModelProperty(value = "展现顺序")
    private Integer viewOrder;


    /**参数描述*/
    @ApiModelProperty(value = "参数描述")
    private String description;


    @ApiModelProperty(value = "可删除")
    private Boolean deletable;


    /**可选值*/
    @ApiModelProperty(value = "可选值", notes = "类型为枚举时可选的值，分号分隔")
    private String enumValue;


    /**参数值*/
    @ApiModelProperty(value = "参数值", notes = "执行时最终使用的值")
    private String value;


    public Long getTaskType(){
        return taskType;
    }
    public void setTaskType(Long taskType) {
        this.taskType = taskType;
    }
    public PipelineTaskVO getTaskTypeVO(){
        return taskTypeVO;
    }
    public void setTaskTypeVO(PipelineTaskVO taskTypeVO) {
        this.taskTypeVO = taskTypeVO;
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType(){
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultValue(){
        return defaultValue;
    }
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Integer getViewOrder(){
        return viewOrder;
    }
    public void setViewOrder(Integer viewOrder) {
        this.viewOrder = viewOrder;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeletable(){
        return deletable;
    }
    public void setDeletable(Boolean deletable) {
        this.deletable = deletable;
    }

    public String getEnumValue(){
        return enumValue;
    }
    public void setEnumValue(String enumValue) {
        this.enumValue = enumValue;
    }

    public String getValue(){
        return value;
    }
    public void setValue(String value) {
        this.value = value;
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