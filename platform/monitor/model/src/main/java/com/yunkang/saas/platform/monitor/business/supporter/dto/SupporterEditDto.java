package com.yunkang.saas.platform.monitor.business.supporter.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 运维人员
 * @author icode
 */
@ApiModel(value = "修改运维人员使用的DTO")
@Setter @Getter
public class SupporterEditDto {


	/**姓名*/
	@ApiModelProperty(value = "姓名", required = false)
	private String name;


	/**手机号码*/
	@ApiModelProperty(value = "手机号码", required = false)
	private String mobile;


	/**邮箱*/
	@ApiModelProperty(value = "邮箱", required = false)
	private String email;



	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public String getMobile(){
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getEmail(){
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
