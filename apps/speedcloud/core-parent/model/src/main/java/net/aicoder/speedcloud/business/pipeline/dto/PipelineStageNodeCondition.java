package net.aicoder.speedcloud.business.pipeline.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询阶段执行节点使用的DTO")
public class PipelineStageNodeCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "租户id最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户id最小值")
	private Long tidMin;
	@ApiModelProperty(value = "名称")
	private String name;
    @ApiModelProperty(value = "阶段")
    private Long stage;
	@ApiModelProperty(value = "节点类型")
	private String nodeType;
	@ApiModelProperty(value = "节点节点ID")
	private Long nodeId;
	@ApiModelProperty(value = "节点节点ID最大值")
	private Long nodeIdMax;
	@ApiModelProperty(value = "节点节点ID最小值")
	private Long nodeIdMin;
	@ApiModelProperty(value = "执行排序")
	private Integer execOrder;
	@ApiModelProperty(value = "执行排序最大值")
	private Integer execOrderMax;
	@ApiModelProperty(value = "执行排序最小值")
	private Integer execOrderMin;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getTidMin(){
		return tidMin;
	}
	public void setTidMin(Long tidMin) {
		this.tidMin = tidMin;
	}

	public Long getTidMax(){
		return tidMax;
	}
	public void setTidMax(Long tidMax) {
		this.tidMax = tidMax;
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

	public Long getNodeIdMin(){
		return nodeIdMin;
	}
	public void setNodeIdMin(Long nodeIdMin) {
		this.nodeIdMin = nodeIdMin;
	}

	public Long getNodeIdMax(){
		return nodeIdMax;
	}
	public void setNodeIdMax(Long nodeIdMax) {
		this.nodeIdMax = nodeIdMax;
	}


	public Integer getExecOrder(){
		return execOrder;
	}
	public void setExecOrder(Integer execOrder) {
		this.execOrder = execOrder;
	}

	public Integer getExecOrderMin(){
		return execOrderMin;
	}
	public void setExecOrderMin(Integer execOrderMin) {
		this.execOrderMin = execOrderMin;
	}

	public Integer getExecOrderMax(){
		return execOrderMax;
	}
	public void setExecOrderMax(Integer execOrderMax) {
		this.execOrderMax = execOrderMax;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
