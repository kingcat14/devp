package com.yunkang.saas.platform.monitor.business.indicator.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 指标的值对象
*/
@ApiModel(value = "展现指标的值对象")
public class IndicatorVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**代码*/
    @ApiModelProperty(value = "代码")
    private String code;


    /**名称*/
    @ApiModelProperty(value = "名称")
    private String name;


    /**类型*/
    @ApiModelProperty(value = "类型", notes = "持续型,数值型")
    private String type;


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

    public String getType(){
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}