package net.aicoder.speedcloud.business.env.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 应用环境的值对象
*/
@ApiModel(value = "展现应用环境的值对象")
public class AppEnvConfigVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**环境名称*/
    @ApiModelProperty(value = "环境名称")
    private String name;


    /**环境级别*/
    @ApiModelProperty(value = "环境级别")
    private String level;


    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLevel(){
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
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