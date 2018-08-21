package net.aicoder.devp.product.business.product.domain;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;
import com.yunkang.saas.common.framework.eo.BaseEntity;



/**
 * 产品定义
 * @author icode
 */
@Entity
@Table
public class DevpPrdProduct extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_STEREOTYPE = "stereotype";
	public static final String PROPERTY_SCOPE = "scope";
	public static final String PROPERTY_PRD_DEPT = "prdDept";
	public static final String PROPERTY_PRD_OWNER = "prdOwner";
	public static final String PROPERTY_DEV_MANAGER = "devManager";
	public static final String PROPERTY_OPS_MANAGER = "opsManager";
	public static final String PROPERTY_BIZ_LINE = "bizLine";
	public static final String PROPERTY_BIZ_MANAGER = "bizManager";
	public static final String PROPERTY_GOLIVE = "golive";
	public static final String PROPERTY_MAJOR_CUST = "majorCust";
	public static final String PROPERTY_CUST_MANAGER = "custManager";
	public static final String PROPERTY_CUST_USAGE = "custUsage";
	public static final String PROPERTY_ACQUISITION_MODE = "acquisitionMode";
	public static final String PROPERTY_ACQUISITION_DESC = "acquisitionDesc";
	public static final String PROPERTY_VERSION = "version";
	public static final String PROPERTY_PHASE = "phase";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_NOTES = "notes";
	public static final String PROPERTY_RECORD_STATE = "recordState";
	public static final String PROPERTY_CREATE_UCODE = "createUcode";
	public static final String PROPERTY_CMODIFY_UCODE = "cmodifyUcode";


    @Id
    @Column(name = "rid")
    private Long id;


    /**
    * 租户编号
    * 
    */
    @Column(name = "tid", updatable = false)
	@NotNull(message = "租户编号不能为空")
	private Long tid;

    /**
    * 产品代码
    * 
    */
    @Column(name = "code")
	@NotNull(message = "产品代码不能为空")
	@Size(max = 255, message = "产品代码超长，最多255个字符")
	private String code;

    /**
    * 产品名称
    * 
    */
    @Column(name = "name")
	@NotNull(message = "产品名称不能为空")
	@Size(max = 255, message = "产品名称超长，最多255个字符")
	private String name;

    /**
    * 产品别名
    * 
    */
    @Column(name = "alias")
	@Size(max = 255, message = "产品别名超长，最多255个字符")
	private String alias;

    /**
    * 产品描述
    * 
    */
    @Column(name = "description")
	@Size(max = 255, message = "产品描述超长，最多255个字符")
	private String description;

    /**
    * 产品类型
    * 
    */
    @Column(name = "type")
	@Size(max = 255, message = "产品类型超长，最多255个字符")
	private String type;

    /**
    * 构造型
    * 
    */
    @Column(name = "stereotype")
	@Size(max = 255, message = "构造型超长，最多255个字符")
	private String stereotype;

    /**
    * 范围
    * 
    */
    @Column(name = "scope")
	@Size(max = 255, message = "范围超长，最多255个字符")
	private String scope;

    /**
    * 所属部门
    * 
    */
    @Column(name = "prd_dept")
	@Size(max = 255, message = "所属部门超长，最多255个字符")
	private String prdDept;

    /**
    * 产品负责人
    * 
    */
    @Column(name = "prd_owner")
	@Size(max = 255, message = "产品负责人超长，最多255个字符")
	private String prdOwner;

    /**
    * 开发负责人
    * 
    */
    @Column(name = "dev_manager")
	@Size(max = 255, message = "开发负责人超长，最多255个字符")
	private String devManager;

    /**
    * 维护负责人
    * 
    */
    @Column(name = "ops_manager")
	@Size(max = 255, message = "维护负责人超长，最多255个字符")
	private String opsManager;

    /**
    * 业务线
    * 
    */
    @Column(name = "biz_line")
	@Size(max = 255, message = "业务线超长，最多255个字符")
	private String bizLine;

    /**
    * 业务代表
    * 
    */
    @Column(name = "biz_manager")
	@Size(max = 255, message = "业务代表超长，最多255个字符")
	private String bizManager;

    /**
    * 启用时间
    * 
    */
    @Column(name = "golive")
	@Temporal(TemporalType.DATE)
	private Date golive;

    /**
    * 主要客户
    * 
    */
    @Column(name = "major_cust")
	@Size(max = 255, message = "主要客户超长，最多255个字符")
	private String majorCust;

    /**
    * 客户代表
    * 
    */
    @Column(name = "cust_manager")
	@Size(max = 255, message = "客户代表超长，最多255个字符")
	private String custManager;

    /**
    * 客户使用情况
    * 
    */
    @Column(name = "cust_usage")
	@Size(max = 255, message = "客户使用情况超长，最多255个字符")
	private String custUsage;

    /**
    * 获取方式
    * 
    */
    @Column(name = "acquisition_mode")
	@Size(max = 255, message = "获取方式超长，最多255个字符")
	private String acquisitionMode;

    /**
    * 获取方式说明
    * 
    */
    @Column(name = "acquisition_desc")
	@Size(max = 255, message = "获取方式说明超长，最多255个字符")
	private String acquisitionDesc;

    /**
    * 版本
    * 
    */
    @Column(name = "version")
	@Size(max = 255, message = "版本超长，最多255个字符")
	private String version;

    /**
    * 阶段
    * 
    */
    @Column(name = "phase")
	@Size(max = 255, message = "阶段超长，最多255个字符")
	private String phase;

    /**
    * 状态
    * 
    */
    @Column(name = "status")
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;

    /**
    * 备注
    * 
    */
    @Column(name = "notes")
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String notes;

    /**
    * 记录状态
    * 
    */
    @Column(name = "record_state")
	private Integer recordState;

    /**
    * 创建用户代码
    * 
    */
    @Column(name = "create_ucode")
	@Size(max = 255, message = "创建用户代码超长，最多255个字符")
	private String createUcode;

    /**
    * 修改用户代码
    * 
    */
    @Column(name = "cmodify_ucode")
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

