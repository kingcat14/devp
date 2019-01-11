package com.yunkang.saas.platform.monitor.business.notification.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 通知方式
 * @author icode
 */
@ApiModel(value = "修改通知方式使用的DTO")
@Setter @Getter
public class NotificationTypeEditDto {


	/**通知方式*/
	@ApiModelProperty(value = "通知方式", required = false, notes = "SMS、EMAIL")
	private String code;



	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
