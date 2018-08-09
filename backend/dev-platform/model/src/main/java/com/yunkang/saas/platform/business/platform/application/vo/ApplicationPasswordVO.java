package com.yunkang.saas.platform.business.platform.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 应用密码的值对象
*/
@ApiModel(value = "展现应用密码的值对象")
public class ApplicationPasswordVO {

    @ApiModelProperty(value = "记录id")
    private Long id;

    /**
    * 访问ID
    * 
    */
    @ApiModelProperty(value = "访问ID")
    private String accessId;

    /**
    * 访问密码
    * 
    */
    @ApiModelProperty(value = "访问密码")
    private String accessKey;


    public String getAccessId(){
        return accessId;
    }
    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }
    public String getAccessKey(){
        return accessKey;
    }
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
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