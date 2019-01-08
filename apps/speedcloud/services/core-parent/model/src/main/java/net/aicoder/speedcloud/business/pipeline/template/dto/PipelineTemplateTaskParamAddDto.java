package net.aicoder.speedcloud.business.pipeline.template.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 任务模板参数
 * @author icode
 */
@ApiModel(value = "新增任务模板参数使用的DTO")
@Setter @Getter
public class PipelineTemplateTaskParamAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**所属任务*/
	@ApiModelProperty(value = "所属任务", required = false, notes = "")
	private String task;

    /**参数名称*/
	@ApiModelProperty(value = "参数名称", required = false)
	private String name;

    /**参数类型*/
	@ApiModelProperty(value = "参数类型", required = false, notes = "字符或者枚举")
	private String type;

    /**默认值*/
	@ApiModelProperty(value = "默认值", required = false)
	private String defaultValue;

    /**展现顺序*/
	@ApiModelProperty(value = "展现顺序", required = false)
	private Integer viewOrder;

    /**参数描述*/
	@ApiModelProperty(value = "参数描述", required = false)
	private String description;

    /**可删除*/
	@ApiModelProperty(value = "可删除", required = false)
	private Boolean deletable;

    /**可选值*/
	@ApiModelProperty(value = "可选值", required = false, notes = "类型为枚举时可选的值，分号分隔")
	private String enumValue;

    /**参数值*/
	@ApiModelProperty(value = "参数值", required = false, notes = "执行时最终使用的值")
	private String value;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getTask(){
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getDefaultValue(){
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Integer getViewOrder(){
		return viewOrder;
	}
	public void setViewOrder(Integer viewOrder) {
		this.viewOrder = viewOrder;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDeletable(){
		return deletable;
	}
	public void setDeletable(Boolean deletable) {
		this.deletable = deletable;
	}

	public String getEnumValue(){
		return enumValue;
	}
	public void setEnumValue(String enumValue) {
		this.enumValue = enumValue;
	}

	public String getValue(){
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
