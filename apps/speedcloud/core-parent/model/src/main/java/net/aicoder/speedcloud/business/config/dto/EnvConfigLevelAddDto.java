package net.aicoder.speedcloud.business.config.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 环境级别
 * @author icode
 */
@ApiModel(value = "新增环境级别使用的DTO")
public class EnvConfigLevelAddDto {

    /**名称*/
	@ApiModelProperty(value = "名称", required = false)
	private String name;

    /**代码*/
	@ApiModelProperty(value = "代码", required = false)
	private String code;

    /**类型*/
	@ApiModelProperty(value = "类型", required = false, notes = "环境类型：生成环境、测试环境（开发也是测试环境）")
	private String type;


	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
