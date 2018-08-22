package net.aicoder.devp.business.ops.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
 * 附件定义
 * @author icode
 */
@ApiModel(value = "新增附件定义使用的DTO")
public class DevpOpsAttachmentAddDto {

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
	@ApiModelProperty(value = "元素类型", required = false)
	@Size(max = 255, message = "元素类型超长，最多255个字符")
	private String etype;

    /**
	 * 附件代码
	 * [附件代码]
     */
	@ApiModelProperty(value = "附件代码", required = false)
	@Size(max = 255, message = "附件代码超长，最多255个字符")
	private String code;

    /**
	 * 附件名称
	 * [附件名称]
     */
	@ApiModelProperty(value = "附件名称", required = false)
	@Size(max = 255, message = "附件名称超长，最多255个字符")
	private String name;

    /**
	 * 附件别名
	 * [附件别名]
     */
	@ApiModelProperty(value = "附件别名", required = false)
	@Size(max = 255, message = "附件别名超长，最多255个字符")
	private String alias;

    /**
	 * 附件描述
	 * [附件描述]
     */
	@ApiModelProperty(value = "附件描述", required = false)
	@Size(max = 255, message = "附件描述超长，最多255个字符")
	private String description;

    /**
	 * 记录状态
	 * [记录状态]-0-失效;1-生效;缺省为1
     */
	@ApiModelProperty(value = "记录状态", required = false)
	private Integer recordState;

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
	 * 文件类型
	 * [文件类型]
     */
	@ApiModelProperty(value = "文件类型", required = false)
	@Size(max = 255, message = "文件类型超长，最多255个字符")
	private String fileType;

    /**
	 * 访问方式
	 * [访问方式]
     */
	@ApiModelProperty(value = "访问方式", required = false)
	@Size(max = 255, message = "访问方式超长，最多255个字符")
	private String accessType;

    /**
	 * 访问域
	 * [访问域]
     */
	@ApiModelProperty(value = "访问域", required = false)
	@Size(max = 255, message = "访问域超长，最多255个字符")
	private String domain;

    /**
	 * 访问地址
	 * [访问地址]
     */
	@ApiModelProperty(value = "访问地址", required = false)
	@Size(max = 255, message = "访问地址超长，最多255个字符")
	private String address;

    /**
	 * 附件版本
	 * [附件版本]-当前版本
     */
	@ApiModelProperty(value = "附件版本", required = false)
	@Size(max = 255, message = "附件版本超长，最多255个字符")
	private String fileVersion;

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
	 * 修改用户代码
	 * [修改用户代码]
     */
	@ApiModelProperty(value = "修改用户代码", required = false)
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

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
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
