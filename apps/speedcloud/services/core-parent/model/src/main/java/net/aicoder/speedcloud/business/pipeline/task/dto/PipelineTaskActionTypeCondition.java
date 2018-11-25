package net.aicoder.speedcloud.business.pipeline.task.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询操作类型使用的DTO")
public class PipelineTaskActionTypeCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "类型代码")
	private String code;
	@ApiModelProperty(value = "类型名称")
	private String name;
	@ApiModelProperty(value = "展现顺序")
	private String viewOrder;
	@ApiModelProperty(value = "说明")
	private String memo;
	@ApiModelProperty(value = "描述")
	private String description;
	@ApiModelProperty(value = "脚本内容", notes = "")
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
