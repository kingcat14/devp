package net.aicoder.devp.business.ops.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询附件定义使用的DTO")
public class DevpOpsAttachmentCondition implements Serializable{

	@ApiModelProperty(value = "租户编号")
	private Long tid;
	@ApiModelProperty(value = "租户编号最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户编号最小值")
	private Long tidMin;
	@ApiModelProperty(value = "元素类型")
	private String etype;
	@ApiModelProperty(value = "附件代码")
	private String code;
	@ApiModelProperty(value = "附件名称")
	private String name;
	@ApiModelProperty(value = "附件别名")
	private String alias;
	@ApiModelProperty(value = "附件描述")
	private String description;
	@ApiModelProperty(value = "记录状态")
	private Integer recordState;
	@ApiModelProperty(value = "记录状态最大值")
	private Integer recordStateMax;
	@ApiModelProperty(value = "记录状态最小值")
	private Integer recordStateMin;
	@ApiModelProperty(value = "类型代码")
	private String typeCode;
	@ApiModelProperty(value = "类型名称")
	private String typeName;
	@ApiModelProperty(value = "文件类型")
	private String fileType;
	@ApiModelProperty(value = "访问方式")
	private String accessType;
	@ApiModelProperty(value = "访问域")
	private String domain;
	@ApiModelProperty(value = "访问地址")
	private String address;
	@ApiModelProperty(value = "附件版本")
	private String fileVersion;
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
	@ApiModelProperty(value = "修改用户代码")
	private String cmodifyUcode;


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


	public String getFileType(){
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public String getAccessType(){
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}


	public String getDomain(){
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}


	public String getAddress(){
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


	public String getFileVersion(){
		return fileVersion;
	}
	public void setFileVersion(String fileVersion) {
		this.fileVersion = fileVersion;
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


	public String getCmodifyUcode(){
		return cmodifyUcode;
	}
	public void setCmodifyUcode(String cmodifyUcode) {
		this.cmodifyUcode = cmodifyUcode;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
