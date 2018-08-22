package net.aicoder.devp.business.ops.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotEmpty;
import com.yunkang.saas.common.framework.eo.BaseEntity;



/**
 * 问题记录
 * @author icode
 */
@Entity
@Table(appliesTo = "devp_ops_issue", comment = "[问题记录]")
//@DynamicUpdate
//@DynamicInsert
public class DevpOpsIssue extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ETYPE = "etype";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_RECORD_STATE = "recordState";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_ISSUE = "issue";
	public static final String PROPERTY_REPLY = "reply";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_HAS_ATTACHMENT = "hasAttachment";
	public static final String PROPERTY_NEXUS_TYPE = "nexusType";
	public static final String PROPERTY_NEXUS_RID = "nexusRid";
	public static final String PROPERTY_NEXUS_VERSION = "nexusVersion";
	public static final String PROPERTY_NEXUS_PHASE = "nexusPhase";
	public static final String PROPERTY_SEQ = "seq";
	public static final String PROPERTY_PARAS_CODE = "parasCode";
	public static final String PROPERTY_CMODIFY_UCODE = "cmodifyUcode";


    @Id
    @Column(name = "rid")
    private Long id;


    /**
    * 租户编号
    * [租户编号]
    */
    @Column(name = "tid", updatable = false)
	@NotNull(message = "租户编号不能为空")
	private Long tid;

    /**
    * 元素类型
    * [元素类型]
    */
    @Column(name = "etype")
	@NotNull(message = "元素类型不能为空")
	@Size(max = 255, message = "元素类型超长，最多255个字符")
	private String etype;

    /**
    * 问题代码
    * [问题代码]
    */
    @Column(name = "code")
	@Size(max = 255, message = "问题代码超长，最多255个字符")
	private String code;

    /**
    * 问题名称
    * [问题名称]
    */
    @Column(name = "name")
	@Size(max = 255, message = "问题名称超长，最多255个字符")
	private String name;

    /**
    * 问题别名
    * [问题别名]
    */
    @Column(name = "alias")
	@Size(max = 255, message = "问题别名超长，最多255个字符")
	private String alias;

    /**
    * 问题描述
    * [问题描述]
    */
    @Column(name = "description")
	@Size(max = 255, message = "问题描述超长，最多255个字符")
	private String description;

    /**
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    @Column(name = "record_state")
	private Integer recordState;

    /**
    * 问题类型
    * [问题类型]
    */
    @Column(name = "type")
	@Size(max = 255, message = "问题类型超长，最多255个字符")
	private String type;

    /**
    * 问题说明
    * [问题说明]
    */
    @Column(name = "issue")
	@Size(max = 255, message = "问题说明超长，最多255个字符")
	private String issue;

    /**
    * 问题回复
    * [问题回复]
    */
    @Column(name = "reply")
	@Size(max = 255, message = "问题回复超长，最多255个字符")
	private String reply;

    /**
    * 处理状态
    * [处理状态]
    */
    @Column(name = "status")
	@Size(max = 255, message = "处理状态超长，最多255个字符")
	private String status;

    /**
    * 是否有附件
    * [是否有附件]-0:无，1:有
    */
    @Column(name = "has_attachment")
	private Integer hasAttachment;

    /**
    * 关联记录类型
    * [关联记录类型]
    */
    @Column(name = "nexus_type")
	@NotNull(message = "关联记录类型不能为空")
	@Size(max = 255, message = "关联记录类型超长，最多255个字符")
	private String nexusType;

    /**
    * 关联记录编号
    * [关联记录编号]
    */
    @Column(name = "nexus_rid")
	@NotNull(message = "关联记录编号不能为空")
	private Long nexusRid;

    /**
    * 关联记录版本
    * [关联记录版本]-关联记录所对应的版本
    */
    @Column(name = "nexus_version")
	@Size(max = 255, message = "关联记录版本超长，最多255个字符")
	private String nexusVersion;

    /**
    * 关联记录阶段
    * [关联记录阶段]-关联记录所对应的阶段
    */
    @Column(name = "nexus_phase")
	@Size(max = 255, message = "关联记录阶段超长，最多255个字符")
	private String nexusPhase;

    /**
    * 顺序号
    * [顺序号]
    */
    @Column(name = "seq")
	private Integer seq;

    /**
    * 参数定义标识
    * [参数定义标识]-扩展参数定义的标识
    */
    @Column(name = "paras_code")
	@Size(max = 255, message = "参数定义标识超长，最多255个字符")
	private String parasCode;

    /**
    * 修改用户代码
    * [修改用户代码]
    */
    @Column(name = "cmodify_ucode")
	@Size(max = 255, message = "修改用户代码超长，最多255个字符")
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

	public String getCmodifyUcode(){
		return cmodifyUcode;
	}
	public void setCmodifyUcode(String cmodifyUcode) {
		this.cmodifyUcode = cmodifyUcode;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

