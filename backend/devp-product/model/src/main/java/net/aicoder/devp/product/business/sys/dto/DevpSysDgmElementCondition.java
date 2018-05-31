package net.aicoder.devp.product.business.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询UML图包含元素使用的DTO")
public class DevpSysDgmElementCondition implements Serializable{

	@ApiModelProperty(value = "租户编号")
	private Long tid;
	@ApiModelProperty(value = "租户编号最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户编号最小值")
	private Long tidMin;
	@ApiModelProperty(value = "系统元素名称")
	private String name;
	@ApiModelProperty(value = "系统元素代码")
	private String code;
	@ApiModelProperty(value = "系统元素别名")
	private String alias;
	@ApiModelProperty(value = "系统元素描述")
	private String description;
	@ApiModelProperty(value = "产品编号")
	private Long prdRid;
	@ApiModelProperty(value = "产品编号最大值")
	private Long prdRidMax;
	@ApiModelProperty(value = "产品编号最小值")
	private Long prdRidMin;
	@ApiModelProperty(value = "UML图编号")
	private Long dgmRid;
	@ApiModelProperty(value = "UML图编号最大值")
	private Long dgmRidMax;
	@ApiModelProperty(value = "UML图编号最小值")
	private Long dgmRidMin;
	@ApiModelProperty(value = "系统元素编号")
	private Long elmRid;
	@ApiModelProperty(value = "系统元素编号最大值")
	private Long elmRidMax;
	@ApiModelProperty(value = "系统元素编号最小值")
	private Long elmRidMin;
	@ApiModelProperty(value = "系统元素实例编号")
	private Long elmInstRid;
	@ApiModelProperty(value = "系统元素实例编号最大值")
	private Long elmInstRidMax;
	@ApiModelProperty(value = "系统元素实例编号最小值")
	private Long elmInstRidMin;
	@ApiModelProperty(value = "顺序号")
	private Integer seq;
	@ApiModelProperty(value = "顺序号最大值")
	private Integer seqMax;
	@ApiModelProperty(value = "顺序号最小值")
	private Integer seqMin;
	@ApiModelProperty(value = "类型")
	private String type;
	@ApiModelProperty(value = "子类型")
	private String subType;
	@ApiModelProperty(value = "构造型")
	private String stereotype;
	@ApiModelProperty(value = "范围")
	private String scope;
	@ApiModelProperty(value = "记录状态")
	private Integer recordState;
	@ApiModelProperty(value = "记录状态最大值")
	private Integer recordStateMax;
	@ApiModelProperty(value = "记录状态最小值")
	private Integer recordStateMin;
	@ApiModelProperty(value = "创建用户代码")
	private String createUcode;
	@ApiModelProperty(value = "修改用户代码")
	private String modifyUcode;


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


	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}


	public String getAlias(){
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}


	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public Long getPrdRid(){
		return prdRid;
	}
	public void setPrdRid(Long prdRid) {
		this.prdRid = prdRid;
	}

	public Long getPrdRidMin(){
		return prdRidMin;
	}
	public void setPrdRidMin(Long prdRidMin) {
		this.prdRidMin = prdRidMin;
	}

	public Long getPrdRidMax(){
		return prdRidMax;
	}
	public void setPrdRidMax(Long prdRidMax) {
		this.prdRidMax = prdRidMax;
	}


	public Long getDgmRid(){
		return dgmRid;
	}
	public void setDgmRid(Long dgmRid) {
		this.dgmRid = dgmRid;
	}

	public Long getDgmRidMin(){
		return dgmRidMin;
	}
	public void setDgmRidMin(Long dgmRidMin) {
		this.dgmRidMin = dgmRidMin;
	}

	public Long getDgmRidMax(){
		return dgmRidMax;
	}
	public void setDgmRidMax(Long dgmRidMax) {
		this.dgmRidMax = dgmRidMax;
	}


	public Long getElmRid(){
		return elmRid;
	}
	public void setElmRid(Long elmRid) {
		this.elmRid = elmRid;
	}

	public Long getElmRidMin(){
		return elmRidMin;
	}
	public void setElmRidMin(Long elmRidMin) {
		this.elmRidMin = elmRidMin;
	}

	public Long getElmRidMax(){
		return elmRidMax;
	}
	public void setElmRidMax(Long elmRidMax) {
		this.elmRidMax = elmRidMax;
	}


	public Long getElmInstRid(){
		return elmInstRid;
	}
	public void setElmInstRid(Long elmInstRid) {
		this.elmInstRid = elmInstRid;
	}

	public Long getElmInstRidMin(){
		return elmInstRidMin;
	}
	public void setElmInstRidMin(Long elmInstRidMin) {
		this.elmInstRidMin = elmInstRidMin;
	}

	public Long getElmInstRidMax(){
		return elmInstRidMax;
	}
	public void setElmInstRidMax(Long elmInstRidMax) {
		this.elmInstRidMax = elmInstRidMax;
	}


	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getSeqMin(){
		return seqMin;
	}
	public void setSeqMin(Integer seqMin) {
		this.seqMin = seqMin;
	}

	public Integer getSeqMax(){
		return seqMax;
	}
	public void setSeqMax(Integer seqMax) {
		this.seqMax = seqMax;
	}


	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	public String getSubType(){
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}


	public String getStereotype(){
		return stereotype;
	}
	public void setStereotype(String stereotype) {
		this.stereotype = stereotype;
	}


	public String getScope(){
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}


	public Integer getRecordState(){
		return recordState;
	}
	public void setRecordState(Integer recordState) {
		this.recordState = recordState;
	}

	public Integer getRecordStateMin(){
		return recordStateMin;
	}
	public void setRecordStateMin(Integer recordStateMin) {
		this.recordStateMin = recordStateMin;
	}

	public Integer getRecordStateMax(){
		return recordStateMax;
	}
	public void setRecordStateMax(Integer recordStateMax) {
		this.recordStateMax = recordStateMax;
	}


	public String getCreateUcode(){
		return createUcode;
	}
	public void setCreateUcode(String createUcode) {
		this.createUcode = createUcode;
	}


	public String getModifyUcode(){
		return modifyUcode;
	}
	public void setModifyUcode(String modifyUcode) {
		this.modifyUcode = modifyUcode;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
