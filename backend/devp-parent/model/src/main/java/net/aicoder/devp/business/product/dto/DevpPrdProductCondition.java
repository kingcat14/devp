package net.aicoder.devp.business.product.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.*;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询产品使用的DTO")
public class DevpPrdProductCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户编号", notes = "[租户编号]")
	private Long tid;
	@ApiModelProperty(value = "租户编号最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户编号最小值")
	private Long tidMin;
	@ApiModelProperty(value = "etype", notes = "")
	private String etype;
	@ApiModelProperty(value = "产品名称", notes = "[产品名称]")
	private String name;
	@ApiModelProperty(value = "产品代码", notes = "[产品代码]")
	private String code;
	@ApiModelProperty(value = "产品别名", notes = "[产品别名]")
	private String alias;
	@ApiModelProperty(value = "产品描述", notes = "[产品描述]")
	private String description;
	@ApiModelProperty(value = "产品类型", notes = "[产品类型]-系统平台、平台应用、中台服务、后台服务、技术组件、独立应用、工具")
	private String type;
	@ApiModelProperty(value = "构造型", notes = "[构造型]")
	private String stereotype;
	@ApiModelProperty(value = "范围", notes = "[范围]-访问控制范围:共享产品，租户内共享,私有产品")
	private String scope;
	@ApiModelProperty(value = "所属部门", notes = "[所属部门]")
	private String prdDept;
	@ApiModelProperty(value = "产品负责人", notes = "[产品负责人]")
	private String prdOwner;
	@ApiModelProperty(value = "开发负责人", notes = "[开发负责人]")
	private String devManager;
	@ApiModelProperty(value = "维护负责人", notes = "[维护负责人]")
	private String opsManager;
	@ApiModelProperty(value = "业务线", notes = "[业务线]")
	private String bizLine;
	@ApiModelProperty(value = "业务代表", notes = "[业务代表]")
	private String bizManager;
	@ApiModelProperty(value = "启用时间", notes = "[启用时间]-启用时间(产品首次上线时间)")
	private Date golive;
	@ApiModelProperty(value = "起始启用时间")
	private Date goliveStart;
	@ApiModelProperty(value = "结束启用时间")
	private Date goliveEnd;
	@ApiModelProperty(value = "主要客户", notes = "[主要客户]")
	private String majorCust;
	@ApiModelProperty(value = "客户代表", notes = "[客户代表]")
	private String custManager;
	@ApiModelProperty(value = "客户使用情况", notes = "[客户使用情况]-客户使用情况，如客户流量、使用频度等")
	private String custUsage;
	@ApiModelProperty(value = "获取方式", notes = "[获取方式]-自主开发,外包开发,联合开发,产品采购,产品租用,其它")
	private String acquisitionMode;
	@ApiModelProperty(value = "获取方式说明", notes = "[获取方式说明]")
	private String acquisitionDesc;
	@ApiModelProperty(value = "版本", notes = "[版本]-当前版本")
	private String version;
	@ApiModelProperty(value = "阶段", notes = "[阶段]-产品调研,产品设计,产品开发,试运行,产品维护,产品停用")
	private String phase;
	@ApiModelProperty(value = "状态", notes = "[状态]-未开始,进行中,已完成,暂停,取消")
	private String status;
	@ApiModelProperty(value = "备注", notes = "[备注]")
	private String notes;
	@ApiModelProperty(value = "记录状态", notes = "[记录状态]-0-失效;1-生效;缺省为1")
	private Integer recordState;
	@ApiModelProperty(value = "记录状态最大值")
	private Integer recordStateMax;
	@ApiModelProperty(value = "记录状态最小值")
	private Integer recordStateMin;
	@ApiModelProperty(value = "创建用户代码", notes = "[创建用户代码]")
	private String createUcode;
	@ApiModelProperty(value = "创建用户姓名", notes = "[创建用户姓名]")
	private String createUname;
	@ApiModelProperty(value = "修改用户代码", notes = "[修改用户代码]")
	private String cmodifyUcode;
	@ApiModelProperty(value = "修改用户姓名", notes = "[修改用户姓名]")
	private String modifyUname;


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
