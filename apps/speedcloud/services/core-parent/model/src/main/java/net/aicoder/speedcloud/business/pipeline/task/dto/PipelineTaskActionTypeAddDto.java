package net.aicoder.speedcloud.business.pipeline.task.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 操作类型
 * @author icode
 */
@ApiModel(value = "新增操作类型使用的DTO")
public class PipelineTaskActionTypeAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**类型代码*/
	@ApiModelProperty(value = "类型代码", required = false)
	private String code;

    /**类型名称*/
	@ApiModelProperty(value = "类型名称", required = false)
	private String name;

    /**展现顺序*/
	@ApiModelProperty(value = "展现顺序", required = false)
	private String viewOrder;

    /**说明*/
	@ApiModelProperty(value = "说明", required = false)
	private String memo;

    /**描述*/
	@ApiModelProperty(value = "描述", required = false)
	private String description;

    /**脚本内容*/
	@ApiModelProperty(value = "脚本内容", required = false, notes = "")
	private String content;


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

	public String getViewOrder(){
		return viewOrder;
	}
	public void setViewOrder(String viewOrder) {
		this.viewOrder = viewOrder;
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

	public String getContent(){
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
