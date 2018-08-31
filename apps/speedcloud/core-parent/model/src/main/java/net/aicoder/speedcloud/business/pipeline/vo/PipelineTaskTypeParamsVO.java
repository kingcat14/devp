package net.aicoder.speedcloud.business.pipeline.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineTaskTypeVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 任务类型参数定义的值对象
*/
@ApiModel(value = "展现任务类型参数定义的值对象")
public class PipelineTaskTypeParamsVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**所属任务类型*/
    @ApiModelProperty(value = "所属任务类型")
    private Long taskType;
    private PipelineTaskTypeVO taskTypeVO;


    /**参数名称*/
    @ApiModelProperty(value = "参数名称")
    private String name;


    /**参数代码*/
    @ApiModelProperty(value = "参数代码")
    private String code;


    @ApiModelProperty(value = "展现顺序")
    private Integer viewOrder;


    public Long getTaskType(){
        return taskType;
    }
    public void setTaskType(Long taskType) {
        this.taskType = taskType;
    }
    public PipelineTaskTypeVO getTaskTypeVO(){
        return taskTypeVO;
    }
    public void setTaskTypeVO(PipelineTaskTypeVO taskTypeVO) {
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

    public Integer getViewOrder(){
        return viewOrder;
    }
    public void setViewOrder(Integer viewOrder) {
        this.viewOrder = viewOrder;
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