package net.aicoder.speedcloud.business.pipeline.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 阶段执行节点
 * @author icode
 */
@ApiModel(value = "新增阶段执行节点使用的DTO")
public class PipelineStageNodeAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**名称*/
	@ApiModelProperty(value = "名称", required = false)
	private String name;

    /**阶段*/
	@ApiModelProperty(value = "阶段", required = false)
	private Long stage;

    /**节点类型*/
	@ApiModelProperty(value = "节点类型", required = false)
    private String nodeType;

    /**节点节点ID*/
	@ApiModelProperty(value = "节点节点ID", required = false)
	private Long nodeId;

    /**执行排序*/
	@ApiModelProperty(value = "执行排序", required = false)
	private Integer execOrder;


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

	public Long getStage(){
        return stage;
    }
    public void setStage(Long stage) {
        this.stage = stage;
    }

    public String getNodeType(){
        return nodeType;
    }
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

	public Long getNodeId(){
		return nodeId;
	}
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public Integer getExecOrder(){
		return execOrder;
	}
	public void setExecOrder(Integer execOrder) {
		this.execOrder = execOrder;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
