package net.aicoder.speedcloud.business.config.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 代码库类型
 * @author icode
 */
@ApiModel(value = "修改代码库类型使用的DTO")
@Setter @Getter
public class CodeRepositoryTypeEditDto {


	/**代码*/
	@ApiModelProperty(value = "代码", required = false)
	private String code;


	/**名称*/
	@ApiModelProperty(value = "名称", required = false)
	private String name;



	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}


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
