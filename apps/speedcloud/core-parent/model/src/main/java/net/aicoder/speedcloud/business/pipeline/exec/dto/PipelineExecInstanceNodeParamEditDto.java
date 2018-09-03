package net.aicoder.speedcloud.business.pipeline.exec.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 运行实例节点参数
 * @author icode
 */
@ApiModel(value = "修改运行实例节点参数使用的DTO")
public class PipelineExecInstanceNodeParamEditDto {


	/**所属节点*/
	@ApiModelProperty(value = "所属节点", required = false, notes = "")
	private Long node;


	/**属性名称*/
	@ApiModelProperty(value = "属性名称", required = false)
	private String name;


	/**属性代码*/
	@ApiModelProperty(value = "属性代码", required = false)
	private String code;


	/**属性值*/
	@ApiModelProperty(value = "属性值", required = false, notes = "执行时最终使用的值")
	private String value;


	/**排序*/
	@ApiModelProperty(value = "排序", required = false)
	private Integer viewOrder;


	/**类型*/
	@ApiModelProperty(value = "类型", required = false)
	private String type;



	public Long getNode(){
        return node;
    }
    public void setNode(Long node) {
        this.node = node;
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


	public String getValue(){
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}


	public Integer getViewOrder(){
		return viewOrder;
	}
	public void setViewOrder(Integer viewOrder) {
		this.viewOrder = viewOrder;
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
