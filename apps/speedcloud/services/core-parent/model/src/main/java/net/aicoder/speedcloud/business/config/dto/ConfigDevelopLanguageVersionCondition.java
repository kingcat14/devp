package net.aicoder.speedcloud.business.config.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询开发语言版本使用的DTO")
public class ConfigDevelopLanguageVersionCondition extends SaaSCondition{

	@ApiModelProperty(value = "版本号")
	private String name;
    @ApiModelProperty(value = "开发语言")
    private Long language;


	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


    public Long getLanguage(){
        return language;
    }
    public void setLanguage(Long language) {
        this.language = language;
    }




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
