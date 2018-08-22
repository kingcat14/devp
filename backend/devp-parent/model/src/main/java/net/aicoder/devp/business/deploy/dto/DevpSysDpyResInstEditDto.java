package net.aicoder.devp.business.deploy.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 部署关联资源实例
 * @author icode
 */
@ApiModel(value = "修改部署关联资源实例使用的DTO")
public class DevpSysDpyResInstEditDto {


    /**
	 * 租户编号
	 * [租户编号]
     */
	@NotNull(message = "租户编号不能为空")
	@ApiModelProperty(value = "租户编号", required = true)
	private Long tid;


    /**
	 * 元素类型
	 * [元素类型]-SYS_DPY_RES_INST // 关联资源实例
     */
	@NotNull(message = "元素类型不能为空")
	@ApiModelProperty(value = "元素类型", required = true)
	@Size(max = 255, message = "元素类型超长，最多255个字符")
	private String etype;


    /**
	 * 系统元素名称
	 * [系统元素名称]
     */
	@ApiModelProperty(value = "系统元素名称", required = false)
	@Size(max = 255, message = "系统元素名称超长，最多255个字符")
	private String name;


    /**
	 * 系统元素代码
	 * [系统元素代码]
     */
	@ApiModelProperty(value = "系统元素代码", required = false)
	@Size(max = 255, message = "系统元素代码超长，最多255个字符")
	private String code;


    /**
	 * 系统元素别名
	 * [系统元素别名]
     */
	@ApiModelProperty(value = "系统元素别名", required = false)
	@Size(max = 255, message = "系统元素别名超长，最多255个字符")
	private String alias;


    /**
	 * 系统元素描述
	 * [系统元素描述]
     */
	@ApiModelProperty(value = "系统元素描述", required = false)
	@Size(max = 255, message = "系统元素描述超长，最多255个字符")
	private String description;


    /**
	 * 记录状态
	 * [记录状态]-0-失效;1-生效;缺省为1
     */
	@ApiModelProperty(value = "记录状态", required = false)
	private Integer recordState;


    /**
	 * 资源实例标识
	 * [资源实例标识]-同一个部署方案中不能重复
     */
	@ApiModelProperty(value = "资源实例标识", required = false)
	@Size(max = 255, message = "资源实例标识超长，最多255个字符")
	private String flag;


    /**
	 * 类型
	 * [类型]
     */
	@ApiModelProperty(value = "类型", required = false)
	@Size(max = 255, message = "类型超长，最多255个字符")
	private String type;


    /**
	 * 子类型
	 * [子类型]
     */
	@ApiModelProperty(value = "子类型", required = false)
	@Size(max = 255, message = "子类型超长，最多255个字符")
	private String subType;


    /**
	 * 部署模式
	 * [部署模式]-主机/容器化/第三方提供/不适用
     */
	@ApiModelProperty(value = "部署模式", required = false)
	@Size(max = 255, message = "部署模式超长，最多255个字符")
	private String dpyModel;


    /**
	 * 部署说明
	 * [部署说明]
     */
	@ApiModelProperty(value = "部署说明", required = false)
	@Size(max = 255, message = "部署说明超长，最多255个字符")
	private String dpyDescription;


    /**
	 * 访问地址
	 * [访问地址]-内部访问地址，如：内网IP
     */
	@ApiModelProperty(value = "访问地址", required = false)
	@Size(max = 255, message = "访问地址超长，最多255个字符")
	private String accessAddr;


    /**
	 * 状态
	 * [状态]
     */
	@ApiModelProperty(value = "状态", required = false)
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;


    /**
	 * 备注
	 * [备注]
     */
	@ApiModelProperty(value = "备注", required = false)
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String notes;


    /**
	 * 产品编号
	 * [产品编号]
     */
	@NotNull(message = "产品编号不能为空")
	@ApiModelProperty(value = "产品编号", required = true)
	private Long prdRid;


    /**
	 * 部署方案编号
	 * [部署方案编号]
     */
	@NotNull(message = "部署方案编号不能为空")
	@ApiModelProperty(value = "部署方案编号", required = true)
	private Long schemeRid;


    /**
	 * 关联资源编号
	 * [关联资源编号]
     */
	@NotNull(message = "关联资源编号不能为空")
	@ApiModelProperty(value = "关联资源编号", required = true)
	private Long resRid;


    /**
	 * 父包编号
	 * [父包编号]-可以组织成一棵树
     */
	@ApiModelProperty(value = "父包编号", required = false)
	private Long parentRid;


    /**
	 * 顺序号
	 * [顺序号]
     */
	@ApiModelProperty(value = "顺序号", required = false)
	private Integer seq;


    /**
	 * 关联IT资产编号
	 * [关联IT资产编号]
     */
	@ApiModelProperty(value = "关联IT资产编号", required = false)
	private Long assetRid;


    /**
	 * 关联IT资产元素类型
	 * [关联IT资产元素类型]
     */
	@ApiModelProperty(value = "关联IT资产元素类型", required = false)
	@Size(max = 255, message = "关联IT资产元素类型超长，最多255个字符")
	private String assetEtype;


    /**
	 * 关联IT资产类型代码
	 * [关联IT资产类型代码]
     */
	@ApiModelProperty(value = "关联IT资产类型代码", required = false)
	@Size(max = 255, message = "关联IT资产类型代码超长，最多255个字符")
	private String assetTypeCode;


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

	public String getFlag(){
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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

	public String getDpyModel(){
		return dpyModel;
	}
	public void setDpyModel(String dpyModel) {
		this.dpyModel = dpyModel;
	}

	public String getDpyDescription(){
		return dpyDescription;
	}
	public void setDpyDescription(String dpyDescription) {
		this.dpyDescription = dpyDescription;
	}

	public String getAccessAddr(){
		return accessAddr;
	}
	public void setAccessAddr(String accessAddr) {
		this.accessAddr = accessAddr;
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

	public Long getSchemeRid(){
		return schemeRid;
	}
	public void setSchemeRid(Long schemeRid) {
		this.schemeRid = schemeRid;
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
