package net.aicoder.devp.security.business.security.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
 * 角色
 * @author icode
 */
@ApiModel(value = "新增角色使用的DTO")
public class RoleAddDto {

    /**
	 * 角色名称
	 * 
     */
	@NotNull(message = "角色名称不能为空")
	@ApiModelProperty(value = "角色名称", required = true)
	@Size(max = 255, message = "角色名称超长，最多255个字符")
	private String name;

    /**
	 * 角色描述
	 * 
     */
	@ApiModelProperty(value = "角色描述", required = false)
	@Size(max = 255, message = "角色描述超长，最多255个字符")
	private String description;


	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
