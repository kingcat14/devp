package com.yunkang.saas.platform.monitor.business.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 程序类型的值对象
*/
@ApiModel(value = "展现程序类型的值对象")
public class ApplicationTypeVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**代码*/
    @ApiModelProperty(value = "代码")
    private String code;


    /**名称*/
    @ApiModelProperty(value = "名称")
    private String name;


    /**图标*/
    @ApiModelProperty(value = "图标")
    private String icon;


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

    public String getIcon(){
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
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