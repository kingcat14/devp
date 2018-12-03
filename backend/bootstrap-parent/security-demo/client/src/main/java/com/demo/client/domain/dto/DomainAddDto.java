package com.demo.client.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 领域
 * @author icode
 */
@ApiModel(value = "新增领域使用的DTO")
@Setter @Getter
public class DomainAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**领域名称*/
	@ApiModelProperty(value = "领域名称", required = false)
	private String name;

    /**领域代码*/
	@ApiModelProperty(value = "领域代码", required = false)
	private String code;

    /**父领域*/
	@ApiModelProperty(value = "父领域", required = false)
	private String parent;

    /**领域代码前缀*/
	@ApiModelProperty(value = "领域代码前缀", required = false, notes = "附领域为空时，必须填该字段")
	private String prefix;

    /**描述*/
	@ApiModelProperty(value = "描述", required = false)
	private String description;


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

	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getParent(){
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getPrefix(){
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
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
