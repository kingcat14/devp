package net.aicoder.speedcloud.icode.business.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 属性类型
 * @author icode
 */
@ApiModel(value = "新增属性类型使用的DTO")
@Setter @Getter
public class PropertyTypeAddDto {

    /**代码*/
	@ApiModelProperty(value = "代码", required = false, notes = "")
	private String code;

    /**名称*/
	@ApiModelProperty(value = "名称", required = false, notes = "")
	private String name;

    /**排序*/
	@ApiModelProperty(value = "排序", required = false, notes = "")
	private Integer viewIndex;


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

	public Integer getViewIndex(){
		return viewIndex;
	}
	public void setViewIndex(Integer viewIndex) {
		this.viewIndex = viewIndex;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
