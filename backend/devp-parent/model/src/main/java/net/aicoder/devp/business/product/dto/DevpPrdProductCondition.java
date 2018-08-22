package net.aicoder.devp.business.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "查询产品定义使用的DTO")
public class DevpPrdProductCondition implements Serializable{

	@ApiModelProperty(value = "租户编号")
	private Long tid;
	@ApiModelProperty(value = "租户编号最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户编号最小值")
	private Long tidMin;
	@ApiModelProperty(value = "产品代码")
	private String code;
	@ApiModelProperty(value = "产品名称")
	private String name;
	@ApiModelProperty(value = "产品别名")
	private String alias;
	@ApiModelProperty(value = "产品描述")
	private String description;
	@ApiModelProperty(value = "产品类型")
	private String type;
	@ApiModelProperty(value = "构造型")
	private String stereotype;
	@ApiModelProperty(value = "范围")
	private String scope;
	@ApiModelProperty(value = "所属部门")
	private String prdDept;
	@ApiModelProperty(value = "产品负责人")
	private String prdOwner;
	@ApiModelProperty(value = "开发负责人")
	private String devManager;
	@ApiModelProperty(value = "维护负责人")
	private String opsManager;
	@ApiModelProperty(value = "业务线")
	private String bizLine;
	@ApiModelProperty(value = "业务代表")
	private String bizManager;
	@ApiModelProperty(value = "启用时间")
	private Date golive;
	@ApiModelProperty(value = "起始启用时间")
	private Date goliveStart;
	@ApiModelProperty(value = "结束启用时间")
	private Date goliveEnd;
	@ApiModelProperty(value = "主要客户")
	private String majorCust;
	@ApiModelProperty(value = "客户代表")
	private String custManager;
	@ApiModelProperty(value = "客户使用情况")
	private String custUsage;
	@ApiModelProperty(value = "获取方式")
	private String acquisitionMode;
	@ApiModelProperty(value = "获取方式说明")
	private String acquisitionDesc;
	@ApiModelProperty(value = "版本")
	private String version;
	@ApiModelProperty(value = "阶段")
	private String phase;
	@ApiModelProperty(value = "状态")
	private String status;
	@ApiModelProperty(value = "备注")
	private String notes;
	@ApiModelProperty(value = "记录状态")
	private Integer recordState;
	@ApiModelProperty(value = "记录状态最大值")
	private Integer recordStateMax;
	@ApiModelProperty(value = "记录状态最小值")
	private Integer recordStateMin;
	@ApiModelProperty(value = "创建用户代码")
	private String createUcode;
	@ApiModelProperty(value = "修改用户代码")
	private String cmodifyUcode;


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


	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
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


	public String getPrdDept(){
		return prdDept;
	}
	public void setPrdDept(String prdDept) {
		this.prdDept = prdDept;
	}


	public String getPrdOwner(){
		return prdOwner;
	}
	public void setPrdOwner(String prdOwner) {
		this.prdOwner = prdOwner;
	}


	public String getDevManager(){
		return devManager;
	}
	public void setDevManager(String devManager) {
		this.devManager = devManager;
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


	public Date getGolive(){
		return golive;
	}
	public void setGolive(Date golive) {
		this.golive = golive;
	}
	public Date getGoliveStart(){
		return goliveStart;
	}
	public void setGoliveStart(Date goliveStart) {
		this.goliveStart = goliveStart;
	}
	public Date getGoliveEnd(){
		return goliveEnd;
	}
	public void setGoliveEnd(Date goliveEnd) {
		this.goliveEnd = goliveEnd;
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


	public String getCreateUcode(){
		return createUcode;
	}
	public void setCreateUcode(String createUcode) {
		this.createUcode = createUcode;
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
