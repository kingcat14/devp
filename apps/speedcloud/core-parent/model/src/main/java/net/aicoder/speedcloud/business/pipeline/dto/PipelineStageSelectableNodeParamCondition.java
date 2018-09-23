package net.aicoder.speedcloud.business.pipeline.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询阶段可选节点参数使用的DTO")
public class PipelineStageSelectableNodeParamCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "租户id最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户id最小值")
	private Long tidMin;
	@ApiModelProperty(value = "节点id")
	private Long nodeId;
	@ApiModelProperty(value = "节点id最大值")
	private Long nodeIdMax;
	@ApiModelProperty(value = "节点id最小值")
	private Long nodeIdMin;
	@ApiModelProperty(value = "节点类型")
	private String nodeType;
	@ApiModelProperty(value = "参数名称")
	private String name;
	@ApiModelProperty(value = "参数类型", notes = "字符或者枚举")
	private String type;
	@ApiModelProperty(value = "默认值")
	private String defaultValue;
	@ApiModelProperty(value = "展现顺序")
	private Integer viewOrder;
	@ApiModelProperty(value = "展现顺序最大值")
	private Integer viewOrderMax;
	@ApiModelProperty(value = "展现顺序最小值")
	private Integer viewOrderMin;
	@ApiModelProperty(value = "参数描述")
	private String description;
	@ApiModelProperty(value = "可删除")
	private Boolean deletable;
	@ApiModelProperty(value = "可选值", notes = "类型为枚举时可选的值，分号分隔")
	private String enumValue;


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


	public String getNodeType(){
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
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

	public Integer getViewOrderMin(){
		return viewOrderMin;
	}
	public void setViewOrderMin(Integer viewOrderMin) {
		this.viewOrderMin = viewOrderMin;
	}

	public Integer getViewOrderMax(){
		return viewOrderMax;
	}
	public void setViewOrderMax(Integer viewOrderMax) {
		this.viewOrderMax = viewOrderMax;
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




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
