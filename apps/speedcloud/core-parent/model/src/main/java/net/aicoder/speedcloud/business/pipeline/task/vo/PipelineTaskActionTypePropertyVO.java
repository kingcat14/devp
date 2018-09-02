package net.aicoder.speedcloud.business.pipeline.task.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskActionTypeVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 操作类型属性定义的值对象
*/
@ApiModel(value = "展现操作类型属性定义的值对象")
public class PipelineTaskActionTypePropertyVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**所属任务类型*/
    @ApiModelProperty(value = "所属任务类型")
    private Long taskType;
    private PipelineTaskActionTypeVO taskTypeVO;


    /**属性名称*/
    @ApiModelProperty(value = "属性名称")
    private String name;


    /**属性代码*/
    @ApiModelProperty(value = "属性代码")
    private String code;


    @ApiModelProperty(value = "展现顺序")
    private Integer viewOrder;


    /**属性类型*/
    @ApiModelProperty(value = "属性类型")
    private String type;
    private SimpleConfigVO typeVO;


    /**可选值*/
    @ApiModelProperty(value = "可选值", notes = "类型为枚举时使用的值")
    private String optionValue;


    public Long getTaskType(){
        return taskType;
    }
    public void setTaskType(Long taskType) {
        this.taskType = taskType;
    }
    public PipelineTaskActionTypeVO getTaskTypeVO(){
        return taskTypeVO;
    }
    public void setTaskTypeVO(PipelineTaskActionTypeVO taskTypeVO) {
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

    public String getType(){
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public SimpleConfigVO getTypeVO(){
        return typeVO;
    }
    public void setTypeVO(SimpleConfigVO typeVO) {
        this.typeVO = typeVO;
    }

    public String getOptionValue(){
        return optionValue;
    }
    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
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