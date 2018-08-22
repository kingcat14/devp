package net.aicoder.maintenance.business.rdc.config.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
 * 开发模式
 * @author icode
 */
@ApiModel(value = "新增开发模式使用的DTO")
public class DevelopTypeAddDto {

    /**
	 * 名称
	 * 
     */
	@ApiModelProperty(value = "名称", required = false)
	@Size(max = 255, message = "名称超长，最多255个字符")
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
