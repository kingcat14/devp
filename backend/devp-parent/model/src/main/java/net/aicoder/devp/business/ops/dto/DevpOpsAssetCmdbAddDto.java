package net.aicoder.devp.business.ops.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
 * 资产定义
 * @author icode
 */
@ApiModel(value = "新增资产定义使用的DTO")
public class DevpOpsAssetCmdbAddDto {

    /**
	 * 租户编号
	 * [租户编号]
     */
	@NotNull(message = "租户编号不能为空")
	@ApiModelProperty(value = "租户编号", required = true)
	private Long tid;

    /**
	 * 元素类型
	 * [元素类型]-服务器主机ASSET_HOST,
网络设备ASSET_NETWORK,
基础软件ASSET_INFRA_SW,
服务及许可ASSET_SVC_LIC,
业务软件ASSET_BIZ_SW,
其它ASSET_OTHERS
     */
	@NotNull(message = "元素类型不能为空")
	@ApiModelProperty(value = "元素类型", required = true)
	@Size(max = 255, message = "元素类型超长，最多255个字符")
	private String etype;

    /**
	 * 名称
	 * [名称]-资产名称
     */
	@NotNull(message = "名称不能为空")
	@ApiModelProperty(value = "名称", required = true)
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;

    /**
	 * 代码
	 * [代码]-资产代码
     */
	@ApiModelProperty(value = "代码", required = false)
	@Size(max = 255, message = "代码超长，最多255个字符")
	private String code;

    /**
	 * 别名
	 * [别名]-资产别名
     */
	@ApiModelProperty(value = "别名", required = false)
	@Size(max = 255, message = "别名超长，最多255个字符")
	private String alias;

    /**
	 * 描述
	 * [描述]-资产描述
     */
	@ApiModelProperty(value = "描述", required = false)
	@Size(max = 255, message = "描述超长，最多255个字符")
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
	 * 构造型
	 * [构造型]-(保留)
     */
	@ApiModelProperty(value = "构造型", required = false)
	@Size(max = 255, message = "构造型超长，最多255个字符")
	private String stereotype;

    /**
	 * 范围
	 * [范围]-(保留)
     */
	@ApiModelProperty(value = "范围", required = false)
	@Size(max = 255, message = "范围超长，最多255个字符")
	private String scope;

    /**
	 * 硬件型号
	 * [硬件型号]-硬件型号
     */
	@ApiModelProperty(value = "硬件型号", required = false)
	@Size(max = 255, message = "硬件型号超长，最多255个字符")
	private String hardwareModel;

    /**
	 * 软件型号
	 * [软件型号]-软件型号,如：操作系统类型
     */
	@ApiModelProperty(value = "软件型号", required = false)
	@Size(max = 255, message = "软件型号超长，最多255个字符")
	private String softwareModel;

    /**
	 * 版本
	 * [版本]-当前版本
     */
	@ApiModelProperty(value = "版本", required = false)
	@Size(max = 255, message = "版本超长，最多255个字符")
	private String version;

    /**
	 * 状态
	 * [状态]-未到货,使用中,备用件,维修中,已借出,已报废
     */
	@ApiModelProperty(value = "状态", required = false)
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;

    /**
	 * 创建时间
	 * [创建时间]-启用时间(产品首次上线时间)
     */
	@ApiModelProperty(value = "创建时间", required = false)
	@Temporal(TemporalType.DATE)
	private Date createDate;

    /**
	 * 到期时间
	 * [到期时间]-到期或报废时间
     */
	@ApiModelProperty(value = "到期时间", required = false)
	@Temporal(TemporalType.DATE)
	private Date expireDate;

    /**
	 * 所属项目
	 * [所属项目]
     */
	@ApiModelProperty(value = "所属项目", required = false)
	@Size(max = 255, message = "所属项目超长，最多255个字符")
	private String assetProject;

    /**
	 * 所属区域
	 * [所属区域]
     */
	@ApiModelProperty(value = "所属区域", required = false)
	@Size(max = 255, message = "所属区域超长，最多255个字符")
	private String assetArea;

    /**
	 * 资产位置
	 * [资产位置]
     */
	@ApiModelProperty(value = "资产位置", required = false)
	@Size(max = 255, message = "资产位置超长，最多255个字符")
	private String assetLocation;

    /**
	 * 内部访问地址
	 * [内部访问地址]-内部访问地址，如：内网IP
     */
	@ApiModelProperty(value = "内部访问地址", required = false)
	@Size(max = 255, message = "内部访问地址超长，最多255个字符")
	private String intAccessAddr;

    /**
	 * 外部访问地址
	 * [外部访问地址]-外部访问地址，如：外网IP
     */
	@ApiModelProperty(value = "外部访问地址", required = false)
	@Size(max = 255, message = "外部访问地址超长，最多255个字符")
	private String extAccessAddr;

    /**
	 * 获取方式
	 * [获取方式]-自主开发,外包开发,联合开发,产品采购,产品租用,其它
     */
	@ApiModelProperty(value = "获取方式", required = false)
	@Size(max = 255, message = "获取方式超长，最多255个字符")
	private String acquisitionMode;

    /**
	 * 获取方式说明
	 * [获取方式说明]
     */
	@ApiModelProperty(value = "获取方式说明", required = false)
	@Size(max = 255, message = "获取方式说明超长，最多255个字符")
	private String acquisitionDesc;

    /**
	 * 归属部门
	 * [归属部门]
     */
	@ApiModelProperty(value = "归属部门", required = false)
	@Size(max = 255, message = "归属部门超长，最多255个字符")
	private String assetDept;

    /**
	 * 资产负责人
	 * [资产负责人]
     */
	@ApiModelProperty(value = "资产负责人", required = false)
	@Size(max = 255, message = "资产负责人超长，最多255个字符")
	private String assetManager;

    /**
	 * 使用部门
	 * [使用部门]
     */
	@ApiModelProperty(value = "使用部门", required = false)
	@Size(max = 255, message = "使用部门超长，最多255个字符")
	private String useDept;

    /**
	 * 使用负责人
	 * [使用负责人]
     */
	@ApiModelProperty(value = "使用负责人", required = false)
	@Size(max = 255, message = "使用负责人超长，最多255个字符")
	private String useManager;

    /**
	 * 维护部门
	 * [维护部门]
     */
	@ApiModelProperty(value = "维护部门", required = false)
	@Size(max = 255, message = "维护部门超长，最多255个字符")
	private String opsDept;

    /**
	 * 维护负责人
	 * [维护负责人]
     */
	@ApiModelProperty(value = "维护负责人", required = false)
	@Size(max = 255, message = "维护负责人超长，最多255个字符")
	private String opsManager;

    /**
	 * 业务线
	 * [业务线]
     */
	@ApiModelProperty(value = "业务线", required = false)
	@Size(max = 255, message = "业务线超长，最多255个字符")
	private String bizLine;

    /**
	 * 业务代表
	 * [业务代表]
     */
	@ApiModelProperty(value = "业务代表", required = false)
	@Size(max = 255, message = "业务代表超长，最多255个字符")
	private String bizManager;

    /**
	 * 启用时间
	 * [启用时间]-启用时间(产品首次上线时间)
     */
	@ApiModelProperty(value = "启用时间", required = false)
	@Temporal(TemporalType.DATE)
	private Date goliveDate;

    /**
	 * 主要客户
	 * [主要客户]
     */
	@ApiModelProperty(value = "主要客户", required = false)
	@Size(max = 255, message = "主要客户超长，最多255个字符")
	private String majorCust;

    /**
	 * 客户代表
	 * [客户代表]
     */
	@ApiModelProperty(value = "客户代表", required = false)
	@Size(max = 255, message = "客户代表超长，最多255个字符")
	private String custManager;

    /**
	 * 使用情况
	 * [使用情况]-客户使用情况，如客户流量、使用频度等
     */
	@ApiModelProperty(value = "使用情况", required = false)
	@Size(max = 255, message = "使用情况超长，最多255个字符")
	private String custUsage;

	/**
	 * 供应商
	 */
	@ApiModelProperty(value = "供应商", required = false)
	@Size(max = 255, message = "供应商超长，最多255个字符")
	private String acquisitionProvider;
    /**
	 * 备注
	 * [备注]
     */
	@ApiModelProperty(value = "备注", required = false)
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String notes;

    /**
	 * 关联产品租户编号
	 * [关联产品租户编号]
     */
	@ApiModelProperty(value = "关联产品租户编号", required = false)
	private Long prdTid;

    /**
	 * 关联产品记录编号
	 * [关联产品记录编号]
     */
	@ApiModelProperty(value = "关联产品记录编号", required = false)
	private Long prdRid;

    /**
	 * 参数定义标识
	 * [参数定义标识]-扩展参数定义的标识
     */
	@ApiModelProperty(value = "参数定义标识", required = false)
	@Size(max = 255, message = "参数定义标识超长，最多255个字符")
	private String parasCode;


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

	public String getHardwareModel(){
		return hardwareModel;
	}
	public void setHardwareModel(String hardwareModel) {
		this.hardwareModel = hardwareModel;
	}

	public String getSoftwareModel(){
		return softwareModel;
	}
	public void setSoftwareModel(String softwareModel) {
		this.softwareModel = softwareModel;
	}

	public String getVersion(){
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate(){
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getExpireDate(){
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getAssetProject(){
		return assetProject;
	}
	public void setAssetProject(String assetProject) {
		this.assetProject = assetProject;
	}

	public String getAssetArea(){
		return assetArea;
	}
	public void setAssetArea(String assetArea) {
		this.assetArea = assetArea;
	}

	public String getAssetLocation(){
		return assetLocation;
	}
	public void setAssetLocation(String assetLocation) {
		this.assetLocation = assetLocation;
	}

	public String getIntAccessAddr(){
		return intAccessAddr;
	}
	public void setIntAccessAddr(String intAccessAddr) {
		this.intAccessAddr = intAccessAddr;
	}

	public String getExtAccessAddr(){
		return extAccessAddr;
	}
	public void setExtAccessAddr(String extAccessAddr) {
		this.extAccessAddr = extAccessAddr;
	}

	public String getAcquisitionMode(){
		return acquisitionMode;
	}
	public void setAcquisitionMode(String acquisitionMode) {
		this.acquisitionMode = acquisitionMode;
	}

	public String getAcquisitionDesc(){
		return acquisitionDesc;
	}
	public void setAcquisitionDesc(String acquisitionDesc) {
		this.acquisitionDesc = acquisitionDesc;
	}

	public String getAssetDept(){
		return assetDept;
	}
	public void setAssetDept(String assetDept) {
		this.assetDept = assetDept;
	}

	public String getAssetManager(){
		return assetManager;
	}
	public void setAssetManager(String assetManager) {
		this.assetManager = assetManager;
	}

	public String getAcquisitionProvider() {
		return acquisitionProvider;
	}
	public void setAcquisitionProvider(String acquisitionProvider) {
		this.acquisitionProvider = acquisitionProvider;
	}

	public String getUseDept(){
		return useDept;
	}
	public void setUseDept(String useDept) {
		this.useDept = useDept;
	}

	public String getUseManager(){
		return useManager;
	}
	public void setUseManager(String useManager) {
		this.useManager = useManager;
	}

	public String getOpsDept(){
		return opsDept;
	}
	public void setOpsDept(String opsDept) {
		this.opsDept = opsDept;
	}

	public String getOpsManager(){
		return opsManager;
	}
	public void setOpsManager(String opsManager) {
		this.opsManager = opsManager;
	}

	public String getBizLine(){
		return bizLine;
	}
	public void setBizLine(String bizLine) {
		this.bizLine = bizLine;
	}

	public String getBizManager(){
		return bizManager;
	}
	public void setBizManager(String bizManager) {
		this.bizManager = bizManager;
	}

	public Date getGoliveDate(){
		return goliveDate;
	}
	public void setGoliveDate(Date goliveDate) {
		this.goliveDate = goliveDate;
	}

	public String getMajorCust(){
		return majorCust;
	}
	public void setMajorCust(String majorCust) {
		this.majorCust = majorCust;
	}

	public String getCustManager(){
		return custManager;
	}
	public void setCustManager(String custManager) {
		this.custManager = custManager;
	}

	public String getCustUsage(){
		return custUsage;
	}
	public void setCustUsage(String custUsage) {
		this.custUsage = custUsage;
	}

	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Long getPrdTid(){
		return prdTid;
	}
	public void setPrdTid(Long prdTid) {
		this.prdTid = prdTid;
	}

	public Long getPrdRid(){
		return prdRid;
	}
	public void setPrdRid(Long prdRid) {
		this.prdRid = prdRid;
	}

	public String getParasCode(){
		return parasCode;
	}
	public void setParasCode(String parasCode) {
		this.parasCode = parasCode;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
