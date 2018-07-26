package net.aicoder.devp.deploy.business.deploy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 部署关联资源实例定义的值对象
*/
@ApiModel(value = "展现部署关联资源实例定义的值对象")
public class DevpSysDpyResInstVO {

    @ApiModelProperty(value = "记录id")
   private Long id;

    /**
    * 元素类型
    * [元素类型]-SYS_DPY_RES_INST // 关联资源实例
    */
    @ApiModelProperty(value = "元素类型")
    private String etype;

    /**
    * 系统元素名称
    * [系统元素名称]
    */
    @ApiModelProperty(value = "系统元素名称")
    private String name;

    /**
    * 系统元素代码
    * [系统元素代码]
    */
    @ApiModelProperty(value = "系统元素代码")
    private String code;

    /**
    * 系统元素别名
    * [系统元素别名]
    */
    @ApiModelProperty(value = "系统元素别名")
    private String alias;

    /**
    * 系统元素描述
    * [系统元素描述]
    */
    @ApiModelProperty(value = "系统元素描述")
    private String description;

    /**
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    @ApiModelProperty(value = "记录状态")
    private Integer recordState;

    /**
    * 类型
    * [类型]
    */
    @ApiModelProperty(value = "类型")
    private String type;

    /**
    * 子类型
    * [子类型]
    */
    @ApiModelProperty(value = "子类型")
    private String subType;

    /**
    * 构造型
    * [构造型]
    */
    @ApiModelProperty(value = "构造型")
    private String stereotype;

    /**
    * 范围
    * [范围]
    */
    @ApiModelProperty(value = "范围")
    private String scope;

    /**
    * 版本
    * [版本]
    */
    @ApiModelProperty(value = "版本")
    private String version;

    /**
    * 阶段
    * [阶段]
    */
    @ApiModelProperty(value = "阶段")
    private String phase;

    /**
    * 状态
    * [状态]
    */
    @ApiModelProperty(value = "状态")
    private String status;

    /**
    * 备注
    * [备注]
    */
    @ApiModelProperty(value = "备注")
    private String notes;

    /**
    * 产品编号
    * [产品编号]
    */
    @ApiModelProperty(value = "产品编号")
    private Long prdRid;

    /**
    * 关联资源编号
    * [关联资源编号]
    */
    @ApiModelProperty(value = "关联资源编号")
    private Long resRid;

    /**
    * 父包编号
    * [父包编号]-可以组织成一棵树
    */
    @ApiModelProperty(value = "父包编号")
    private Long parentRid;

    /**
    * 顺序号
    * [顺序号]
    */
    @ApiModelProperty(value = "顺序号")
    private Integer seq;

    /**
    * 关联IT资产编号
    * [关联IT资产编号]
    */
    @ApiModelProperty(value = "关联IT资产编号")
    private Long assetRid;

    /**
    * 关联IT资产元素类型
    * [关联IT资产元素类型]
    */
    @ApiModelProperty(value = "关联IT资产元素类型")
    private String assetEtype;

    /**
    * 关联IT资产类型代码
    * [关联IT资产类型代码]
    */
    @ApiModelProperty(value = "关联IT资产类型代码")
    private String assetTypeCode;


    public String getEtype(){
        return etype;
    }
    public void setEtype(String etype) {
        this.etype = etype;
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode(){
        return code;
    }
    public void setCode(String code) {
        this.code = code;
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
    public String getSubType(){
        return subType;
    }
    public void setSubType(String subType) {
        this.subType = subType;
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
    public String getNotes(){
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public Long getPrdRid(){
        return prdRid;
    }
    public void setPrdRid(Long prdRid) {
        this.prdRid = prdRid;
    }
    public Long getResRid(){
        return resRid;
    }
    public void setResRid(Long resRid) {
        this.resRid = resRid;
    }
    public Long getParentRid(){
        return parentRid;
    }
    public void setParentRid(Long parentRid) {
        this.parentRid = parentRid;
    }
    public Integer getSeq(){
        return seq;
    }
    public void setSeq(Integer seq) {
        this.seq = seq;
    }
    public Long getAssetRid(){
        return assetRid;
    }
    public void setAssetRid(Long assetRid) {
        this.assetRid = assetRid;
    }
    public String getAssetEtype(){
        return assetEtype;
    }
    public void setAssetEtype(String assetEtype) {
        this.assetEtype = assetEtype;
    }
    public String getAssetTypeCode(){
        return assetTypeCode;
    }
    public void setAssetTypeCode(String assetTypeCode) {
        this.assetTypeCode = assetTypeCode;
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