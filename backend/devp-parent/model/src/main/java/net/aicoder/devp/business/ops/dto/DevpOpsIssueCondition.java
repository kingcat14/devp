package net.aicoder.devp.business.ops.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询问题记录使用的DTO")
public class DevpOpsIssueCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户编号", notes = "[租户编号]")
	private Long tid;
	@ApiModelProperty(value = "元素类型", notes = "[元素类型]")
	private String etype;
	@ApiModelProperty(value = "问题代码", notes = "[问题代码]")
	private String code;
	@ApiModelProperty(value = "问题名称", notes = "[问题名称]")
	private String name;
	@ApiModelProperty(value = "问题别名", notes = "[问题别名]")
	private String alias;
	@ApiModelProperty(value = "问题描述", notes = "[问题描述]")
	private String description;
	@ApiModelProperty(value = "记录状态", notes = "[记录状态]-0-失效;1-生效;缺省为1")
	private Integer recordState;
	@ApiModelProperty(value = "记录状态最大值")
	private Integer recordStateMax;
	@ApiModelProperty(value = "记录状态最小值")
	private Integer recordStateMin;
	@ApiModelProperty(value = "问题类型", notes = "[问题类型]")
	private String type;
	@ApiModelProperty(value = "问题说明", notes = "[问题说明]")
	private String issue;
	@ApiModelProperty(value = "问题回复", notes = "[问题回复]")
	private String reply;
	@ApiModelProperty(value = "处理状态", notes = "[处理状态]")
	private String status;
	@ApiModelProperty(value = "是否有附件", notes = "[是否有附件]-0:无，1:有")
	private Integer hasAttachment;
	@ApiModelProperty(value = "是否有附件最大值")
	private Integer hasAttachmentMax;
	@ApiModelProperty(value = "是否有附件最小值")
	private Integer hasAttachmentMin;
	@ApiModelProperty(value = "关联记录类型", notes = "[关联记录类型]")
	private String nexusType;
	@ApiModelProperty(value = "关联记录编号", notes = "[关联记录编号]")
	private Long nexusRid;
	@ApiModelProperty(value = "关联记录编号最大值")
	private Long nexusRidMax;
	@ApiModelProperty(value = "关联记录编号最小值")
	private Long nexusRidMin;
	@ApiModelProperty(value = "关联记录版本", notes = "[关联记录版本]-关联记录所对应的版本")
	private String nexusVersion;
	@ApiModelProperty(value = "关联记录阶段", notes = "[关联记录阶段]-关联记录所对应的阶段")
	private String nexusPhase;
	@ApiModelProperty(value = "顺序号", notes = "[顺序号]")
	private Integer seq;
	@ApiModelProperty(value = "顺序号最大值")
	private Integer seqMax;
	@ApiModelProperty(value = "顺序号最小值")
	private Integer seqMin;
	@ApiModelProperty(value = "参数定义标识", notes = "[参数定义标识]-扩展参数定义的标识")
	private String parasCode;
	@ApiModelProperty(value = "创建用户代码", notes = "[创建用户代码]")
	private String createUcode;
	@ApiModelProperty(value = "创建用户姓名", notes = "[创建用户姓名]")
	private String createUname;
	@ApiModelProperty(value = "修改用户代码", notes = "[修改用户代码]")
	private String modifyUcode;
	@ApiModelProperty(value = "修改用户姓名", notes = "[修改用户姓名]")
	private String modifyUname;
	@ApiModelProperty(value = "cmodify_ucode", notes = "")
	private String cmodifyUcode;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
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


	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	public String getIssue(){
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}


	public String getReply(){
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}


	public String getStatus(){
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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


	public String getNexusVersion(){
		return nexusVersion;
	}
	public void setNexusVersion(String nexusVersion) {
		this.nexusVersion = nexusVersion;
	}


	public String getNexusPhase(){
		return nexusPhase;
	}
	public void setNexusPhase(String nexusPhase) {
		this.nexusPhase = nexusPhase;
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


	public String getParasCode(){
		return parasCode;
	}
	public void setParasCode(String parasCode) {
		this.parasCode = parasCode;
	}


	public String getCreateUcode(){
		return createUcode;
	}
	public void setCreateUcode(String createUcode) {
		this.createUcode = createUcode;
	}


	public String getCreateUname(){
		return createUname;
	}
	public void setCreateUname(String createUname) {
		this.createUname = createUname;
	}


	public String getModifyUcode(){
		return modifyUcode;
	}
	public void setModifyUcode(String modifyUcode) {
		this.modifyUcode = modifyUcode;
	}


	public String getModifyUname(){
		return modifyUname;
	}
	public void setModifyUname(String modifyUname) {
		this.modifyUname = modifyUname;
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
