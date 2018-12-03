package com.demo.client.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 领域对象行为参数
 * @author icode
 */
@ApiModel(value = "新增领域对象行为参数使用的DTO")
@Setter @Getter
public class EntityActionParameterAddDto {

    /**对象代码*/
	@ApiModelProperty(value = "对象代码", required = false, notes = "")
	private String code;

    /**对象名称*/
	@ApiModelProperty(value = "对象名称", required = false, notes = "")
	private String name;

    /**备注*/
	@ApiModelProperty(value = "备注", required = false, notes = "")
	private String memo;

    /**描述*/
	@ApiModelProperty(value = "描述", required = false, notes = "")
	private String description;

    /**领域对象行为*/
	@ApiModelProperty(value = "领域对象行为", required = false)
	private String entityAction;


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

	public String getMemo(){
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getEntityAction(){
        return entityAction;
    }
    public void setEntityAction(String entityAction) {
        this.entityAction = entityAction;
    }


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
