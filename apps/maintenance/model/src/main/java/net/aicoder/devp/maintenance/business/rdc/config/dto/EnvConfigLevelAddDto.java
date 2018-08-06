package net.aicoder.devp.maintenance.business.rdc.config.dto;

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

    /**
	 * 名称
	 * 
     */
	@ApiModelProperty(value = "名称", required = false)
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;

    /**
	 * 代码
	 * 
     */
	@ApiModelProperty(value = "代码", required = false)
	@Size(max = 255, message = "代码超长，最多255个字符")
	private String code;

    /**
	 * 类型
	 * 环境类型：生成环境、测试环境（开发也是测试环境）
     */
	@ApiModelProperty(value = "类型", required = false)
	@Size(max = 255, message = "类型超长，最多255个字符")
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
