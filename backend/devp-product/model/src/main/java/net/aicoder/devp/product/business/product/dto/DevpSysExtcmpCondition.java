package net.aicoder.devp.product.business.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询产品包含的外部组件使用的DTO")
public class DevpSysExtcmpCondition implements Serializable{

	@ApiModelProperty(value = "租户编号")
	private Long tid;
	@ApiModelProperty(value = "租户编号最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户编号最小值")
	private Long tidMin;
	@ApiModelProperty(value = "代码")
	private String code;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "别名")
	private String alias;
	@ApiModelProperty(value = "描述")
	private String description;
	@ApiModelProperty(value = "产品编号")
	private Long prdRid;
	@ApiModelProperty(value = "产品编号最大值")
	private Long prdRidMax;
	@ApiModelProperty(value = "产品编号最小值")
	private Long prdRidMin;
	@ApiModelProperty(value = "外部产品编号")
	private Long extPrdRid;
	@ApiModelProperty(value = "外部产品编号最大值")
	private Long extPrdRidMax;
	@ApiModelProperty(value = "外部产品编号最小值")
	private Long extPrdRidMin;
	@ApiModelProperty(value = "外部系统元素编号")
	private Long extElmRid;
	@ApiModelProperty(value = "外部系统元素编号最大值")
	private Long extElmRidMax;
	@ApiModelProperty(value = "外部系统元素编号最小值")
	private Long extElmRidMin;
	@ApiModelProperty(value = "顺序号")
	private Integer seq;
	@ApiModelProperty(value = "顺序号最大值")
	private Integer seqMax;
	@ApiModelProperty(value = "顺序号最小值")
	private Integer seqMin;
	@ApiModelProperty(value = "类型")
	private String type;
	@ApiModelProperty(value = "构造型")
	private String stereotype;
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


	public Long getExtPrdRid(){
		return extPrdRid;
	}
	public void setExtPrdRid(Long extPrdRid) {
		this.extPrdRid = extPrdRid;
	}

	public Long getExtPrdRidMin(){
		return extPrdRidMin;
	}
	public void setExtPrdRidMin(Long extPrdRidMin) {
		this.extPrdRidMin = extPrdRidMin;
	}

	public Long getExtPrdRidMax(){
		return extPrdRidMax;
	}
	public void setExtPrdRidMax(Long extPrdRidMax) {
		this.extPrdRidMax = extPrdRidMax;
	}


	public Long getExtElmRid(){
		return extElmRid;
	}
	public void setExtElmRid(Long extElmRid) {
		this.extElmRid = extElmRid;
	}

	public Long getExtElmRidMin(){
		return extElmRidMin;
	}
	public void setExtElmRidMin(Long extElmRidMin) {
		this.extElmRidMin = extElmRidMin;
	}

	public Long getExtElmRidMax(){
		return extElmRidMax;
	}
	public void setExtElmRidMax(Long extElmRidMax) {
		this.extElmRidMax = extElmRidMax;
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


	public String getStereotype(){
		return stereotype;
	}
	public void setStereotype(String stereotype) {
		this.stereotype = stereotype;
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
