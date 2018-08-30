package net.aicoder.speedcloud.business.config.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 开发语言
 * @author icode
 */
@ApiModel(value = "修改开发语言使用的DTO")
public class ConfigDevelopLanguageEditDto {


	/**名称*/
	@ApiModelProperty(value = "名称", required = false)
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
