package net.aicoder.speedcloud.business.env.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 应用环境
 * @author icode
 */
@ApiModel(value = "新增应用环境使用的DTO")
public class AppEnvConfigAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**环境名称*/
	@ApiModelProperty(value = "环境名称", required = false)
	private String name;

    /**环境级别*/
	@ApiModelProperty(value = "环境级别", required = false)
	private String level;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getLevel(){
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}