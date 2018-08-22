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
@ApiModel(value = "修改需求定义使用的DTO")
public class DevpOpsRequirementEditDto {


    /**
	 * 租户编号
	 * [租户编号]
     */
	@NotNull(message = "租户编号不能为空")
	@ApiModelProperty(value = "租户编号", required = true)
	private Long tid;


    /**
	 * 元素类型
	 * [元素类型]
     */
	@NotNull(message = "元素类型不能为空")
	@ApiModelProperty(value = "元素类型", required = true)
	@Size(max = 255, message = "元素类型超长，最多255个字符")
	private String etype;


    /**
	 * 需求代码
	 * [需求代码]
     */
	@ApiModelProperty(value = "需求代码", required = false)
	@Size(max = 255, message = "需求代码超长，最多255个字符")
	private String code;


    /**
	 * 需求名称
	 * [需求名称]
     */
	@ApiModelProperty(value = "需求名称", required = false)
	@Size(max = 255, message = "需求名称超长，最多255个字符")
	private String name;


    /**
	 * 需求别名
	 * [需求别名]
     */
	@ApiModelProperty(value = "需求别名", required = false)
	@Size(max = 255, message = "需求别名超长，最多255个字符")
	private String alias;


    /**
	 * 需求描述
	 * [需求描述]
     */
	@ApiModelProperty(value = "需求描述", required = false)
	@Size(max = 255, message = "需求描述超长，最多255个字符")
	private String description;


    /**
	 * 关联记录类型
	 * [关联记录类型]
     */
	@NotNull(message = "关联记录类型不能为空")
	@ApiModelProperty(value = "关联记录类型", required = true)
	@Size(max = 255, message = "关联记录类型超长，最多255个字符")
	private String nexusType;


    /**
	 * 关联记录编号
	 * [关联记录编号]
     */
	@NotNull(message = "关联记录编号不能为空")
	@ApiModelProperty(value = "关联记录编号", required = true)
	private Long nexusRid;


    /**
	 * 顺序号
	 * [顺序号]
     */
	@ApiModelProperty(value = "顺序号", required = false)
	private Integer seq;


    /**
	 * 类型代码
	 * [类型代码]
     */
	@ApiModelProperty(value = "类型代码", required = false)
	@Size(max = 255, message = "类型代码超长，最多255个字符")
	private String typeCode;


    /**
	 * 类型名称
	 * [类型名称]-冗余字段，方便显示
     */
	@ApiModelProperty(value = "类型名称", required = false)
	@Size(max = 255, message = "类型名称超长，最多255个字符")
	private String typeName;


    /**
	 * 内容
	 * [内容]
     */
	@ApiModelProperty(value = "内容", required = false)
	@Size(max = 255, message = "内容超长，最多255个字符")
	private String content;


    /**
	 * 是否有附件
	 * [是否有附件]-0:无，1:有
     */
	@ApiModelProperty(value = "是否有附件", required = false)
	private Integer hasAttachment;


    /**
	 * 构造型
	 * [构造型]-(保留)
     */
	@ApiModelProperty(value = "构造型", required = false)
	@Size(max = 255, message = "构造型超长，最多255个字符")
	private String stereotype;


    /**
	 * 访问控制范围
	 * [访问控制范围]-(保留)
     */
	@ApiModelProperty(value = "访问控制范围", required = false)
	@Size(max = 255, message = "访问控制范围超长，最多255个字符")
	private String scope;


    /**
	 * 版本
	 * [版本]
     */
	@ApiModelProperty(value = "版本", required = false)
	@Size(max = 255, message = "版本超长，最多255个字符")
	private String version;


    /**
	 * 阶段
	 * [阶段]
     */
	@ApiModelProperty(value = "阶段", required = false)
	@Size(max = 255, message = "阶段超长，最多255个字符")
	private String phase;


    /**
	 * 状态
	 * [状态]
     */
	@ApiModelProperty(value = "状态", required = false)
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;


    /**
	 * 记录状态
	 * [记录状态]-0-失效;1-生效;缺省为1
     */
	@ApiModelProperty(value = "记录状态", required = false)
	private Integer recordState;


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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
