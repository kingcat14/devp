package net.aicoder.devp.business.ops.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.*;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Size;


@ApiModel(value = "查询资产定义使用的DTO")
public class DevpOpsAssetCmdbCondition implements Serializable{

	@ApiModelProperty(value = "租户编号")
	private Long tid;
	@ApiModelProperty(value = "租户编号最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户编号最小值")
	private Long tidMin;
	@ApiModelProperty(value = "元素类型")
	private String etype;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "代码")
	private String code;
	@ApiModelProperty(value = "别名")
	private String alias;
	@ApiModelProperty(value = "描述")
	private String description;
	@ApiModelProperty(value = "记录状态")
	private Integer recordState;
	@ApiModelProperty(value = "记录状态最大值")
	private Integer recordStateMax;
	@ApiModelProperty(value = "记录状态最小值")
	private Integer recordStateMin;
	@ApiModelProperty(value = "类型代码")
	private String typeCode;
	@ApiModelProperty(value = "类型名称")
	private String typeName;
	@ApiModelProperty(value = "构造型")
	private String stereotype;
	@ApiModelProperty(value = "范围")
	private String scope;
	@ApiModelProperty(value = "硬件型号")
	private String hardwareModel;
	@ApiModelProperty(value = "软件型号")
	private String softwareModel;
	@ApiModelProperty(value = "版本")
	private String version;
	@ApiModelProperty(value = "状态")
	private String status;
	@ApiModelProperty(value = "创建时间")
	private Date createDate;
	@ApiModelProperty(value = "起始创建时间")
	private Date createDateStart;
	@ApiModelProperty(value = "结束创建时间")
	private Date createDateEnd;
	@ApiModelProperty(value = "到期时间")
	private Date expireDate;
	@ApiModelProperty(value = "起始到期时间")
	private Date expireDateStart;
	@ApiModelProperty(value = "结束到期时间")
	private Date expireDateEnd;
	@ApiModelProperty(value = "所属项目")
	private String assetProject;
	@ApiModelProperty(value = "所属区域")
	private String assetArea;
	@ApiModelProperty(value = "资产位置")
	private String assetLocation;
	@ApiModelProperty(value = "内部访问地址")
	private String intAccessAddr;
	@ApiModelProperty(value = "外部访问地址")
	private String extAccessAddr;
	@ApiModelProperty(value = "获取方式")
	private String acquisitionMode;
	@ApiModelProperty(value = "获取方式说明")
	private String acquisitionDesc;
	@ApiModelProperty(value = "归属部门")
	private String assetDept;
	@ApiModelProperty(value = "资产负责人")
	private String assetManager;
	@ApiModelProperty(value = "使用部门")
	private String useDept;
	@ApiModelProperty(value = "使用负责人")
	private String useManager;
	@ApiModelProperty(value = "维护部门")
	private String opsDept;
	@ApiModelProperty(value = "维护负责人")
	private String opsManager;
	@ApiModelProperty(value = "业务线")
	private String bizLine;
	@ApiModelProperty(value = "业务代表")
	private String bizManager;
	@ApiModelProperty(value = "启用时间")
	private Date goliveDate;
	@ApiModelProperty(value = "起始启用时间")
	private Date goliveDateStart;
	@ApiModelProperty(value = "结束启用时间")
	private Date goliveDateEnd;
	@ApiModelProperty(value = "主要客户")
	private String majorCust;
	@ApiModelProperty(value = "客户代表")
	private String custManager;
	@ApiModelProperty(value = "使用情况")
	private String custUsage;
	@ApiModelProperty(value = "备注")
	private String notes;
	@ApiModelProperty(value = "关联产品租户编号")
	private Long prdTid;
	@ApiModelProperty(value = "关联产品租户编号最大值")
	private Long prdTidMax;
	@ApiModelProperty(value = "关联产品租户编号最小值")
	private Long prdTidMin;
	@ApiModelProperty(value = "关联产品记录编号")
	private Long prdRid;
	@ApiModelProperty(value = "关联产品记录编号最大值")
	private Long prdRidMax;
	@ApiModelProperty(value = "关联产品记录编号最小值")
	private Long prdRidMin;
	@ApiModelProperty(value = "参数定义标识")
	private String parasCode;
	@ApiModelProperty(value = "供应商")
	private String acquisitionProvider;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getTidMin(){
		return tidMin;
	}
	public void setTidMin(Long tidMin) {
		this.tidMin = tidMin;
	}

	public Long getTidMax(){
		return tidMax;
	}
	public void setTidMax(Long tidMax) {
		this.tidMax = tidMax;
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
	public Date getCreateDateStart(){
		return createDateStart;
	}
	public void setCreateDateStart(Date createDateStart) {
		this.createDateStart = createDateStart;
	}
	public Date getCreateDateEnd(){
		return createDateEnd;
	}
	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}


	public Date getExpireDate(){
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public Date getExpireDateStart(){
		return expireDateStart;
	}
	public void setExpireDateStart(Date expireDateStart) {
		this.expireDateStart = expireDateStart;
	}
	public Date getExpireDateEnd(){
		return expireDateEnd;
	}
	public void setExpireDateEnd(Date expireDateEnd) {
		this.expireDateEnd = expireDateEnd;
	}


	public String getAssetProject(){
		return assetProject;
	}
	public void setAssetProject(String assetProject) {
		this.assetProject = assetProject;
	}

	public String getAcquisitionProvider() {
		return acquisitionProvider;
	}

	public void setAcquisitionProvider(String acquisitionProvider) {
		this.acquisitionProvider = acquisitionProvider;
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
	public Date getGoliveDateStart(){
		return goliveDateStart;
	}
	public void setGoliveDateStart(Date goliveDateStart) {
		this.goliveDateStart = goliveDateStart;
	}
	public Date getGoliveDateEnd(){
		return goliveDateEnd;
	}
	public void setGoliveDateEnd(Date goliveDateEnd) {
		this.goliveDateEnd = goliveDateEnd;
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

	public Long getPrdTidMin(){
		return prdTidMin;
	}
	public void setPrdTidMin(Long prdTidMin) {
		this.prdTidMin = prdTidMin;
	}

	public Long getPrdTidMax(){
		return prdTidMax;
	}
	public void setPrdTidMax(Long prdTidMax) {
		this.prdTidMax = prdTidMax;
	}


	public Long getPrdRid(){
		return prdRid;
	}
	public void setPrdRid(Long prdRid) {
		this.prdRid = prdRid;
	}

	public Long getPrdRidMin(){
		return prdRidMin;
	}
	public void setPrdRidMin(Long prdRidMin) {
		this.prdRidMin = prdRidMin;
	}

	public Long getPrdRidMax(){
		return prdRidMax;
	}
	public void setPrdRidMax(Long prdRidMax) {
		this.prdRidMax = prdRidMax;
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
