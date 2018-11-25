package net.aicoder.speedcloud.icode.business.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 模型类型
 * @author icode
 */
@ApiModel(value = "新增模型类型使用的DTO")
@Setter @Getter
public class ModelTypeAddDto {

    /**code*/
	@ApiModelProperty(value = "code", required = false, notes = "")
	private String code;

    /**name*/
	@ApiModelProperty(value = "name", required = false, notes = "")
	private String name;

    /**idx*/
	@ApiModelProperty(value = "idx", required = false, notes = "")
	private Integer idx;


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

	public Integer getIdx(){
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
