package com.yunkang.saas.bootstrap.business.platform.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 应用密码的值对象
*/
@ApiModel(value = "展现应用密码的值对象")
public class ApplicationPasswordVO {

    @ApiModelProperty(value = "记录id")
    private Long id;

    /**应用Id*/
    @ApiModelProperty(value = "应用Id")
    private Long appId;
    private AppVO appIdVO;

    /**访问ID*/
    @ApiModelProperty(value = "访问ID")
    private String accessId;

    /**访问密码*/
    @ApiModelProperty(value = "访问密码")
    private String accessKey;


    public Long getAppId(){
        return appId;
    }
    public void setAppId(Long appId) {
        this.appId = appId;
    }
    public AppVO getAppIdVO(){
        return appIdVO;
    }
    public void setAppIdVO(AppVO appIdVO) {
        this.appIdVO = appIdVO;
    }

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