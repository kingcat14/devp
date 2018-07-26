package net.aicoder.devp.deploy.business.ops.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 附件定义的值对象
*/
@ApiModel(value = "展现附件定义的值对象")
public class DevpOpsAttachmentVO {

    @ApiModelProperty(value = "记录id")
   private Long id;

    /**
    * 租户编号
    * [租户编号]
    */
    @ApiModelProperty(value = "租户编号")
    private Long tid;

    /**
    * 元素类型
    * [元素类型]
    */
    @ApiModelProperty(value = "元素类型")
    private String etype;

    /**
    * 附件代码
    * [附件代码]
    */
    @ApiModelProperty(value = "附件代码")
    private String code;

    /**
    * 附件名称
    * [附件名称]
    */
    @ApiModelProperty(value = "附件名称")
    private String name;

    /**
    * 附件别名
    * [附件别名]
    */
    @ApiModelProperty(value = "附件别名")
    private String alias;

    /**
    * 附件描述
    * [附件描述]
    */
    @ApiModelProperty(value = "附件描述")
    private String description;

    /**
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    @ApiModelProperty(value = "记录状态")
    private Integer recordState;

    /**
    * 类型代码
    * [类型代码]
    */
    @ApiModelProperty(value = "类型代码")
    private String typeCode;

    /**
    * 类型名称
    * [类型名称]-冗余字段，方便显示
    */
    @ApiModelProperty(value = "类型名称")
    private String typeName;

    /**
    * 文件类型
    * [文件类型]
    */
    @ApiModelProperty(value = "文件类型")
    private String fileType;

    /**
    * 访问方式
    * [访问方式]
    */
    @ApiModelProperty(value = "访问方式")
    private String accessType;

    /**
    * 访问域
    * [访问域]
    */
    @ApiModelProperty(value = "访问域")
    private String domain;

    /**
    * 访问地址
    * [访问地址]
    */
    @ApiModelProperty(value = "访问地址")
    private String address;

    /**
    * 附件版本
    * [附件版本]-当前版本
    */
    @ApiModelProperty(value = "附件版本")
    private String fileVersion;

    /**
    * 关联记录类型
    * [关联记录类型]
    */
    @ApiModelProperty(value = "关联记录类型")
    private String nexusType;

    /**
    * 关联记录编号
    * [关联记录编号]
    */
    @ApiModelProperty(value = "关联记录编号")
    private Long nexusRid;

    /**
    * 顺序号
    * [顺序号]
    */
    @ApiModelProperty(value = "顺序号")
    private Integer seq;

    /**
    * 修改用户代码
    * [修改用户代码]
    */
    @ApiModelProperty(value = "修改用户代码")
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