package com.demo.client.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 领域对象行为
 * @author icode
 */
@ApiModel(value = "新增领域对象行为使用的DTO")
@Setter @Getter
public class EntityActionAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**代码*/
	@ApiModelProperty(value = "代码", required = false)
	private String code;

    /**行为名称*/
	@ApiModelProperty(value = "行为名称", required = false)
	private String name;

    /**备注*/
	@ApiModelProperty(value = "备注", required = false, notes = "行为的概括性描述")
	private String memo;

    /**行为描述*/
	@ApiModelProperty(value = "行为描述", required = false, notes = "行为的操作步骤")
	private String description;

    /**行为输入对象*/
	@ApiModelProperty(value = "行为输入对象", required = false)
	private String request;

    /**行为响应对象*/
	@ApiModelProperty(value = "行为响应对象", required = false)
	private String response;

    /**所属领域对象*/
	@ApiModelProperty(value = "所属领域对象", required = false)
	private String entity;

    /**行为类型*/
	@ApiModelProperty(value = "行为类型", required = false, notes = "备用字段,将来用于标识 增、删、改、查、业务 等行为")
	private String type;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

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

	public String getRequest(){
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse(){
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}

	public String getEntity(){
        return entity;
    }
    public void setEntity(String entity) {
        this.entity = entity;
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
