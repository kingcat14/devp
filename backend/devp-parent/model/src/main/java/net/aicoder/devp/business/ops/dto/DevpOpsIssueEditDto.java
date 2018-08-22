package net.aicoder.devp.business.ops.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 问题记录
 * @author icode
 */
@ApiModel(value = "修改问题记录使用的DTO")
public class DevpOpsIssueEditDto {


	/**租户编号*/
	@ApiModelProperty(value = "租户编号", required = false, notes = "[租户编号]")
	private Long tid;


	/**元素类型*/
	@ApiModelProperty(value = "元素类型", required = false, notes = "[元素类型]")
	private String etype;


	/**问题代码*/
	@ApiModelProperty(value = "问题代码", required = false, notes = "[问题代码]")
	private String code;


	/**问题名称*/
	@ApiModelProperty(value = "问题名称", required = false, notes = "[问题名称]")
	private String name;


	/**问题别名*/
	@ApiModelProperty(value = "问题别名", required = false, notes = "[问题别名]")
	private String alias;


	/**问题描述*/
	@ApiModelProperty(value = "问题描述", required = false, notes = "[问题描述]")
	private String description;


	/**记录状态*/
	@ApiModelProperty(value = "记录状态", required = false, notes = "[记录状态]-0-失效;1-生效;缺省为1")
	private Integer recordState;


	/**问题类型*/
	@ApiModelProperty(value = "问题类型", required = false, notes = "[问题类型]")
	private String type;


	/**问题说明*/
	@ApiModelProperty(value = "问题说明", required = false, notes = "[问题说明]")
	private String issue;


	/**问题回复*/
	@ApiModelProperty(value = "问题回复", required = false, notes = "[问题回复]")
	private String reply;


	/**处理状态*/
	@ApiModelProperty(value = "处理状态", required = false, notes = "[处理状态]")
	private String status;


	/**是否有附件*/
	@ApiModelProperty(value = "是否有附件", required = false, notes = "[是否有附件]-0:无，1:有")
	private Integer hasAttachment;


	/**关联记录类型*/
	@ApiModelProperty(value = "关联记录类型", required = false, notes = "[关联记录类型]")
	private String nexusType;


	/**关联记录编号*/
	@ApiModelProperty(value = "关联记录编号", required = false, notes = "[关联记录编号]")
	private Long nexusRid;


	/**关联记录版本*/
	@ApiModelProperty(value = "关联记录版本", required = false, notes = "[关联记录版本]-关联记录所对应的版本")
	private String nexusVersion;


	/**关联记录阶段*/
	@ApiModelProperty(value = "关联记录阶段", required = false, notes = "[关联记录阶段]-关联记录所对应的阶段")
	private String nexusPhase;


	/**顺序号*/
	@ApiModelProperty(value = "顺序号", required = false, notes = "[顺序号]")
	private Integer seq;


	/**参数定义标识*/
	@ApiModelProperty(value = "参数定义标识", required = false, notes = "[参数定义标识]-扩展参数定义的标识")
	private String parasCode;


	/**创建用户代码*/
	@ApiModelProperty(value = "创建用户代码", required = false, notes = "[创建用户代码]")
	private String createUcode;


	/**创建用户姓名*/
	@ApiModelProperty(value = "创建用户姓名", required = false, notes = "[创建用户姓名]")
	private String createUname;


	/**修改用户代码*/
	@ApiModelProperty(value = "修改用户代码", required = false, notes = "[修改用户代码]")
	private String modifyUcode;


	/**修改用户姓名*/
	@ApiModelProperty(value = "修改用户姓名", required = false, notes = "[修改用户姓名]")
	private String modifyUname;


	/**cmodify_ucode*/
	@ApiModelProperty(value = "cmodify_ucode", required = false, notes = "")
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
