package net.aicoder.devp.business.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


/**
 * 产品定义
 * @author icode
 */
@ApiModel(value = "新增产品定义使用的DTO")
public class DevpPrdProductAddDto {

    /**
	 * 租户编号
	 * 
     */
	@NotNull(message = "租户编号不能为空")
	@ApiModelProperty(value = "租户编号", required = true)
	private Long tid;

    /**
	 * 产品代码
	 * 
     */
	@NotNull(message = "产品代码不能为空")
	@ApiModelProperty(value = "产品代码", required = true)
	@Size(max = 255, message = "产品代码超长，最多255个字符")
	private String code;

    /**
	 * 产品名称
	 * 
     */
	@NotNull(message = "产品名称不能为空")
	@ApiModelProperty(value = "产品名称", required = true)
	@Size(max = 255, message = "产品名称超长，最多255个字符")
	private String name;

    /**
	 * 产品别名
	 * 
     */
	@ApiModelProperty(value = "产品别名", required = false)
	@Size(max = 255, message = "产品别名超长，最多255个字符")
	private String alias;

    /**
	 * 产品描述
	 * 
     */
	@ApiModelProperty(value = "产品描述", required = false)
	@Size(max = 255, message = "产品描述超长，最多255个字符")
	private String description;

    /**
	 * 产品类型
	 * 
     */
	@ApiModelProperty(value = "产品类型", required = false)
	@Size(max = 255, message = "产品类型超长，最多255个字符")
	private String type;

    /**
	 * 构造型
	 * 
     */
	@ApiModelProperty(value = "构造型", required = false)
	@Size(max = 255, message = "构造型超长，最多255个字符")
	private String stereotype;

    /**
	 * 范围
	 * 
     */
	@ApiModelProperty(value = "范围", required = false)
	@Size(max = 255, message = "范围超长，最多255个字符")
	private String scope;

    /**
	 * 所属部门
	 * 
     */
	@ApiModelProperty(value = "所属部门", required = false)
	@Size(max = 255, message = "所属部门超长，最多255个字符")
	private String prdDept;

    /**
	 * 产品负责人
	 * 
     */
	@ApiModelProperty(value = "产品负责人", required = false)
	@Size(max = 255, message = "产品负责人超长，最多255个字符")
	private String prdOwner;

    /**
	 * 开发负责人
	 * 
     */
	@ApiModelProperty(value = "开发负责人", required = false)
	@Size(max = 255, message = "开发负责人超长，最多255个字符")
	private String devManager;

    /**
	 * 维护负责人
	 * 
     */
	@ApiModelProperty(value = "维护负责人", required = false)
	@Size(max = 255, message = "维护负责人超长，最多255个字符")
	private String opsManager;

    /**
	 * 业务线
	 * 
     */
	@ApiModelProperty(value = "业务线", required = false)
	@Size(max = 255, message = "业务线超长，最多255个字符")
	private String bizLine;

    /**
	 * 业务代表
	 * 
     */
	@ApiModelProperty(value = "业务代表", required = false)
	@Size(max = 255, message = "业务代表超长，最多255个字符")
	private String bizManager;

    /**
	 * 启用时间
	 * 
     */
	@ApiModelProperty(value = "启用时间", required = false)
	@Temporal(TemporalType.DATE)
	private Date golive;

    /**
	 * 主要客户
	 * 
     */
	@ApiModelProperty(value = "主要客户", required = false)
	@Size(max = 255, message = "主要客户超长，最多255个字符")
	private String majorCust;

    /**
	 * 客户代表
	 * 
     */
	@ApiModelProperty(value = "客户代表", required = false)
	@Size(max = 255, message = "客户代表超长，最多255个字符")
	private String custManager;

    /**
	 * 客户使用情况
	 * 
     */
	@ApiModelProperty(value = "客户使用情况", required = false)
	@Size(max = 255, message = "客户使用情况超长，最多255个字符")
	private String custUsage;

    /**
	 * 获取方式
	 * 
     */
	@ApiModelProperty(value = "获取方式", required = false)
	@Size(max = 255, message = "获取方式超长，最多255个字符")
	private String acquisitionMode;

    /**
	 * 获取方式说明
	 * 
     */
	@ApiModelProperty(value = "获取方式说明", required = false)
	@Size(max = 255, message = "获取方式说明超长，最多255个字符")
	private String acquisitionDesc;

    /**
	 * 版本
	 * 
     */
	@ApiModelProperty(value = "版本", required = false)
	@Size(max = 255, message = "版本超长，最多255个字符")
	private String version;

    /**
	 * 阶段
	 * 
     */
	@ApiModelProperty(value = "阶段", required = false)
	@Size(max = 255, message = "阶段超长，最多255个字符")
	private String phase;

    /**
	 * 状态
	 * 
     */
	@ApiModelProperty(value = "状态", required = false)
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;

    /**
	 * 备注
	 * 
     */
	@ApiModelProperty(value = "备注", required = false)
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String notes;

    /**
	 * 记录状态
	 * 
     */
	@ApiModelProperty(value = "记录状态", required = false)
	private Integer recordState;

    /**
	 * 创建用户代码
	 * 
     */
	@ApiModelProperty(value = "创建用户代码", required = false)
	@Size(max = 255, message = "创建用户代码超长，最多255个字符")
	private String createUcode;

    /**
	 * 修改用户代码
	 * 
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
