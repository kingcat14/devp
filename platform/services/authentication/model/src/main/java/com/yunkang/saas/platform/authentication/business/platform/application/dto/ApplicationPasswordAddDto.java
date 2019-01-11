package com.yunkang.saas.platform.authentication.business.platform.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 应用密码
 * @author icode
 */
@ApiModel(value = "新增应用密码使用的DTO")
@Setter @Getter
public class ApplicationPasswordAddDto {

    /**应用*/
	@ApiModelProperty(value = "应用", required = false)
	private Long appId;

    /**访问ID*/
	@ApiModelProperty(value = "访问ID", required = false)
	private String accessId;

    /**访问密码*/
	@ApiModelProperty(value = "访问密码", required = false)
	private String accessKey;


	public Long getAppId(){
        return appId;
    }
    public void setAppId(Long appId) {
        this.appId = appId;
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
