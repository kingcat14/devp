package net.aicoder.devp.business.ops.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 需求定义
 * @author icode
 */
@ApiModel(value = "新增需求定义使用的DTO")
public class DevpOpsRequirementAddDto {

    /**租户编号*/
	@ApiModelProperty(value = "租户编号", required = false, notes = "[租户编号]")
	private Long tid;

    /**元素类型*/
	@ApiModelProperty(value = "元素类型", required = false, notes = "[元素类型]")
	private String etype;

    /**需求代码*/
	@ApiModelProperty(value = "需求代码", required = false, notes = "[需求代码]")
	private String code;

    /**需求名称*/
	@ApiModelProperty(value = "需求名称", required = false, notes = "[需求名称]")
	private String name;

    /**需求别名*/
	@ApiModelProperty(value = "需求别名", required = false, notes = "[需求别名]")
	private String alias;

    /**需求描述*/
	@ApiModelProperty(value = "需求描述", required = false, notes = "[需求描述]")
	private String description;

    /**关联记录类型*/
	@ApiModelProperty(value = "关联记录类型", required = false, notes = "[关联记录类型]")
	private String nexusType;

    /**关联记录编号*/
	@ApiModelProperty(value = "关联记录编号", required = false, notes = "[关联记录编号]")
	private Long nexusRid;

    /**顺序号*/
	@ApiModelProperty(value = "顺序号", required = false, notes = "[顺序号]")
	private Integer seq;

    /**类型代码*/
	@ApiModelProperty(value = "类型代码", required = false, notes = "[类型代码]")
	private String typeCode;

    /**类型名称*/
	@ApiModelProperty(value = "类型名称", required = false, notes = "[类型名称]-冗余字段，方便显示")
	private String typeName;

    /**内容*/
	@ApiModelProperty(value = "内容", required = false, notes = "[内容]")
	private String content;

    /**是否有附件*/
	@ApiModelProperty(value = "是否有附件", required = false, notes = "[是否有附件]-0:无，1:有")
	private Integer hasAttachment;

    /**构造型*/
	@ApiModelProperty(value = "构造型", required = false, notes = "[构造型]-(保留)")
	private String stereotype;

    /**访问控制范围*/
	@ApiModelProperty(value = "访问控制范围", required = false, notes = "[访问控制范围]-(保留)")
	private String scope;

    /**版本*/
	@ApiModelProperty(value = "版本", required = false, notes = "[版本]")
	private String version;

    /**阶段*/
	@ApiModelProperty(value = "阶段", required = false, notes = "[阶段]")
	private String phase;

    /**状态*/
	@ApiModelProperty(value = "状态", required = false, notes = "[状态]")
	private String status;

    /**记录状态*/
	@ApiModelProperty(value = "记录状态", required = false, notes = "[记录状态]-0-失效;1-生效;缺省为1")
	private Integer recordState;

    /**创建用户代码*/
	@ApiModelProperty(value = "创建用户代码", required = false, notes = "[创建用户代码]")
	private String createUcode;

    /**创建用户姓名*/
	@ApiModelProperty(value = "创建用户姓名", required = false, notes = "[创建用户姓名]")
	private String createUname;

    /**修改用户代码*/
	@ApiModelProperty(value = "修改用户代码", required = false, notes = "[修改用户代码]")
	private String cmodifyUcode;

    /**修改用户姓名*/
	@ApiModelProperty(value = "修改用户姓名", required = false, notes = "[修改用户姓名]")
	private String modifyUname;


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

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
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

	public String getCmodifyUcode(){
		return cmodifyUcode;
	}
	public void setCmodifyUcode(String cmodifyUcode) {
		this.cmodifyUcode = cmodifyUcode;
	}

	public String getModifyUname(){
		return modifyUname;
	}
	public void setModifyUname(String modifyUname) {
		this.modifyUname = modifyUname;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
