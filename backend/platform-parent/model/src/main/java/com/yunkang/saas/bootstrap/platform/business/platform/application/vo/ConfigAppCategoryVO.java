package com.yunkang.saas.bootstrap.platform.business.platform.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 应用类别的值对象
*/
@ApiModel(value = "展现应用类别的值对象")
public class ConfigAppCategoryVO {

    @ApiModelProperty(value = "记录id")
    private Long id;

    /**类别名称*/
    @ApiModelProperty(value = "类别名称")
    private String name;

    /**类别代码*/
    @ApiModelProperty(value = "类别代码")
    private String code;


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