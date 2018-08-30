package net.aicoder.speedcloud.business.config.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询开发语言使用的DTO")
public class ConfigDevelopLanguageCondition extends SaaSCondition{

	@ApiModelProperty(value = "名称")
	private String name;


	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
