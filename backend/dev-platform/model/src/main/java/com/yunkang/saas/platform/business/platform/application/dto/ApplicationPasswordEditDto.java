package com.yunkang.saas.platform.business.platform.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 应用密码
 * @author icode
 */
@ApiModel(value = "修改应用密码使用的DTO")
public class ApplicationPasswordEditDto {



    /**访问ID*/
	@NotNull(message = "访问ID不能为空")
	@ApiModelProperty(value = "访问ID", required = true)
	@Size(max = 255, message = "访问ID超长，最多255个字符")
	private String accessId;



    /**访问密码*/
	@NotNull(message = "访问密码不能为空")
	@ApiModelProperty(value = "访问密码", required = true)
	@Size(max = 255, message = "访问密码超长，最多255个字符")
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
