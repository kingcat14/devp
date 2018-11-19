package net.aicoder.speedcloud.business.config.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 开发语言版本
 * @author icode
 */
@ApiModel(value = "新增开发语言版本使用的DTO")
public class ConfigDevelopLanguageVersionAddDto {

    /**版本号*/
	@ApiModelProperty(value = "版本号", required = false)
	private String name;

    /**开发语言*/
	@ApiModelProperty(value = "开发语言", required = false)
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
