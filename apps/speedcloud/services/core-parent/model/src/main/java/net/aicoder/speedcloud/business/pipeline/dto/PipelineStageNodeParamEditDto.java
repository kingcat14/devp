package net.aicoder.speedcloud.business.pipeline.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 阶段执行节点参数
 * @author icode
 */
@ApiModel(value = "修改阶段执行节点参数使用的DTO")
public class PipelineStageNodeParamEditDto {


	/**参数名称*/
	@ApiModelProperty(value = "参数名称", required = false)
	private String name;


	/**参数值*/
	@ApiModelProperty(value = "参数值", required = false)
	private String value;


	/**展现顺序*/
	@ApiModelProperty(value = "展现顺序", required = false)
	private Integer viewOrder;


	/**阶段执行节点*/
	@ApiModelProperty(value = "阶段执行节点", required = false, notes = "")
	private Long pipelineStageNode;



	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
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


	public Long getPipelineStageNode(){
        return pipelineStageNode;
    }
    public void setPipelineStageNode(Long pipelineStageNode) {
        this.pipelineStageNode = pipelineStageNode;
    }


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
