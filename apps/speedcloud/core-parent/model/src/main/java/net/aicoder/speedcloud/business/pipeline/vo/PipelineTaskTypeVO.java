package net.aicoder.speedcloud.business.pipeline.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 任务类型的值对象
*/
@ApiModel(value = "展现任务类型的值对象")
public class PipelineTaskTypeVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**类型代码*/
    @ApiModelProperty(value = "类型代码")
    private String code;


    /**类型名称*/
    @ApiModelProperty(value = "类型名称")
    private String name;


    public String getCode(){
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
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