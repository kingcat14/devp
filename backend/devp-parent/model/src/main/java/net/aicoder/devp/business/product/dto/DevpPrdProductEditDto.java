package net.aicoder.devp.business.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 产品
 * @author icode
 */
@ApiModel(value = "修改产品使用的DTO")
public class DevpPrdProductEditDto {


	/**租户编号*/
	@ApiModelProperty(value = "租户编号", required = false, notes = "[租户编号]")
	private Long tid;


	/**etype*/
	@ApiModelProperty(value = "etype", required = false, notes = "")
	private String etype;


	/**产品名称*/
	@ApiModelProperty(value = "产品名称", required = false, notes = "[产品名称]")
	private String name;


	/**产品代码*/
	@ApiModelProperty(value = "产品代码", required = false, notes = "[产品代码]")
	private String code;


	/**产品别名*/
	@ApiModelProperty(value = "产品别名", required = false, notes = "[产品别名]")
	private String alias;


	/**产品描述*/
	@ApiModelProperty(value = "产品描述", required = false, notes = "[产品描述]")
	private String description;


	/**产品类型*/
	@ApiModelProperty(value = "产品类型", required = false, notes = "[产品类型]-系统平台、平台应用、中台服务、后台服务、技术组件、独立应用、工具")
	private String type;


	/**构造型*/
	@ApiModelProperty(value = "构造型", required = false, notes = "[构造型]")
	private String stereotype;


	/**范围*/
	@ApiModelProperty(value = "范围", required = false, notes = "[范围]-访问控制范围:共享产品，租户内共享,私有产品")
	private String scope;


	/**所属部门*/
	@ApiModelProperty(value = "所属部门", required = false, notes = "[所属部门]")
	private String prdDept;


	/**产品负责人*/
	@ApiModelProperty(value = "产品负责人", required = false, notes = "[产品负责人]")
	private String prdOwner;


	/**开发负责人*/
	@ApiModelProperty(value = "开发负责人", required = false, notes = "[开发负责人]")
	private String devManager;


	/**维护负责人*/
	@ApiModelProperty(value = "维护负责人", required = false, notes = "[维护负责人]")
	private String opsManager;


	/**业务线*/
	@ApiModelProperty(value = "业务线", required = false, notes = "[业务线]")
	private String bizLine;


	/**业务代表*/
	@ApiModelProperty(value = "业务代表", required = false, notes = "[业务代表]")
	private String bizManager;


	/**启用时间*/
	@ApiModelProperty(value = "启用时间", required = false, notes = "[启用时间]-启用时间(产品首次上线时间)")
	@Temporal(TemporalType.DATE)
	private Date golive;


	/**主要客户*/
	@ApiModelProperty(value = "主要客户", required = false, notes = "[主要客户]")
	private String majorCust;


	/**客户代表*/
	@ApiModelProperty(value = "客户代表", required = false, notes = "[客户代表]")
	private String custManager;


	/**客户使用情况*/
	@ApiModelProperty(value = "客户使用情况", required = false, notes = "[客户使用情况]-客户使用情况，如客户流量、使用频度等")
	private String custUsage;


	/**获取方式*/
	@ApiModelProperty(value = "获取方式", required = false, notes = "[获取方式]-自主开发,外包开发,联合开发,产品采购,产品租用,其它")
	private String acquisitionMode;


	/**获取方式说明*/
	@ApiModelProperty(value = "获取方式说明", required = false, notes = "[获取方式说明]")
	private String acquisitionDesc;


	/**版本*/
	@ApiModelProperty(value = "版本", required = false, notes = "[版本]-当前版本")
	private String version;


	/**阶段*/
	@ApiModelProperty(value = "阶段", required = false, notes = "[阶段]-产品调研,产品设计,产品开发,试运行,产品维护,产品停用")
	private String phase;


	/**状态*/
	@ApiModelProperty(value = "状态", required = false, notes = "[状态]-未开始,进行中,已完成,暂停,取消")
	private String status;


	/**备注*/
	@ApiModelProperty(value = "备注", required = false, notes = "[备注]")
	private String notes;


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
