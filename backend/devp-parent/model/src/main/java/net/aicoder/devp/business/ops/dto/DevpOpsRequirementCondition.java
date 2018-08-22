package net.aicoder.devp.business.ops.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询需求定义使用的DTO")
public class DevpOpsRequirementCondition implements Serializable{

	@ApiModelProperty(value = "租户编号")
	private Long tid;
	@ApiModelProperty(value = "租户编号最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户编号最小值")
	private Long tidMin;
	@ApiModelProperty(value = "元素类型")
	private String etype;
	@ApiModelProperty(value = "需求代码")
	private String code;
	@ApiModelProperty(value = "需求名称")
	private String name;
	@ApiModelProperty(value = "需求别名")
	private String alias;
	@ApiModelProperty(value = "需求描述")
	private String description;
	@ApiModelProperty(value = "关联记录类型")
	private String nexusType;
	@ApiModelProperty(value = "关联记录编号")
	private Long nexusRid;
	@ApiModelProperty(value = "关联记录编号最大值")
	private Long nexusRidMax;
	@ApiModelProperty(value = "关联记录编号最小值")
	private Long nexusRidMin;
	@ApiModelProperty(value = "顺序号")
	private Integer seq;
	@ApiModelProperty(value = "顺序号最大值")
	private Integer seqMax;
	@ApiModelProperty(value = "顺序号最小值")
	private Integer seqMin;
	@ApiModelProperty(value = "类型代码")
	private String typeCode;
	@ApiModelProperty(value = "类型名称")
	private String typeName;
	@ApiModelProperty(value = "内容")
	private String content;
	@ApiModelProperty(value = "是否有附件")
	private Integer hasAttachment;
	@ApiModelProperty(value = "是否有附件最大值")
	private Integer hasAttachmentMax;
	@ApiModelProperty(value = "是否有附件最小值")
	private Integer hasAttachmentMin;
	@ApiModelProperty(value = "构造型")
	private String stereotype;
	@ApiModelProperty(value = "访问控制范围")
	private String scope;
	@ApiModelProperty(value = "版本")
	private String version;
	@ApiModelProperty(value = "阶段")
	private String phase;
	@ApiModelProperty(value = "状态")
	private String status;
	@ApiModelProperty(value = "记录状态")
	private Integer recordState;
	@ApiModelProperty(value = "记录状态最大值")
	private Integer recordStateMax;
	@ApiModelProperty(value = "记录状态最小值")
	private Integer recordStateMin;


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


	public String getEtype(){
		return etype;
	}
	public void setEtype(String etype) {
		this.etype = etype;
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


	public String getNexusType(){
		return nexusType;
	}
	public void setNexusType(String nexusType) {
		this.nexusType = nexusType;
	}


	public Long getNexusRid(){
		return nexusRid;
	}
	public void setNexusRid(Long nexusRid) {
		this.nexusRid = nexusRid;
	}

	public Long getNexusRidMin(){
		return nexusRidMin;
	}
	public void setNexusRidMin(Long nexusRidMin) {
		this.nexusRidMin = nexusRidMin;
	}

	public Long getNexusRidMax(){
		return nexusRidMax;
	}
	public void setNexusRidMax(Long nexusRidMax) {
		this.nexusRidMax = nexusRidMax;
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


	public String getTypeCode(){
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}


	public String getTypeName(){
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public String getContent(){
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


	public Integer getHasAttachment(){
		return hasAttachment;
	}
	public void setHasAttachment(Integer hasAttachment) {
		this.hasAttachment = hasAttachment;
	}

	public Integer getHasAttachmentMin(){
		return hasAttachmentMin;
	}
	public void setHasAttachmentMin(Integer hasAttachmentMin) {
		this.hasAttachmentMin = hasAttachmentMin;
	}

	public Integer getHasAttachmentMax(){
		return hasAttachmentMax;
	}
	public void setHasAttachmentMax(Integer hasAttachmentMax) {
		this.hasAttachmentMax = hasAttachmentMax;
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


	public String getVersion(){
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}


	public String getPhase(){
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}


	public String getStatus(){
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
