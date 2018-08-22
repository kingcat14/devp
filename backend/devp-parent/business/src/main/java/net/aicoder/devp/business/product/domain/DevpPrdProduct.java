package net.aicoder.devp.business.product.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotEmpty;
import com.yunkang.saas.common.framework.eo.GenericBaseEntity;



/**
 * 产品
 * @author icode
 */
@Entity
@Table(appliesTo = "devp_prd_product", comment = "[产品]")
//@DynamicUpdate
//@DynamicInsert
public class DevpPrdProduct extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ETYPE = "etype";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
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
	public static final String PROPERTY_CREATE_UNAME = "createUname";
	public static final String PROPERTY_CMODIFY_UCODE = "cmodifyUcode";
	public static final String PROPERTY_MODIFY_UNAME = "modifyUname";


    @Id
    @Column(name = "rid")
    private Long id;


    /**
    * 租户编号
    * [租户编号]
    */
    @Column(name = "tid", nullable = false, updatable = true)
	private Long tid;

    /**
    * etype
    * 
    */
    @Column(name = "etype", nullable = true, updatable = true)
	@Size(max = 255, message = "etype超长，最多255个字符")
	private String etype;

    /**
    * 产品名称
    * [产品名称]
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "产品名称超长，最多255个字符")
	private String name;

    /**
    * 产品代码
    * [产品代码]
    */
    @Column(name = "code", nullable = true, updatable = true)
	@Size(max = 255, message = "产品代码超长，最多255个字符")
	private String code;

    /**
    * 产品别名
    * [产品别名]
    */
    @Column(name = "alias", nullable = true, updatable = true)
	@Size(max = 255, message = "产品别名超长，最多255个字符")
	private String alias;

    /**
    * 产品描述
    * [产品描述]
    */
    @Column(name = "description", nullable = true, updatable = true)
	@Size(max = 255, message = "产品描述超长，最多255个字符")
	private String description;

    /**
    * 产品类型
    * [产品类型]-系统平台、平台应用、中台服务、后台服务、技术组件、独立应用、工具
    */
    @Column(name = "type", nullable = true, updatable = true)
	@Size(max = 255, message = "产品类型超长，最多255个字符")
	private String type;

    /**
    * 构造型
    * [构造型]
    */
    @Column(name = "stereotype", nullable = true, updatable = true)
	@Size(max = 255, message = "构造型超长，最多255个字符")
	private String stereotype;

    /**
    * 范围
    * [范围]-访问控制范围:共享产品，租户内共享,私有产品
    */
    @Column(name = "scope", nullable = true, updatable = true)
	@Size(max = 255, message = "范围超长，最多255个字符")
	private String scope;

    /**
    * 所属部门
    * [所属部门]
    */
    @Column(name = "prd_dept", nullable = true, updatable = true)
	@Size(max = 255, message = "所属部门超长，最多255个字符")
	private String prdDept;

    /**
    * 产品负责人
    * [产品负责人]
    */
    @Column(name = "prd_owner", nullable = true, updatable = true)
	@Size(max = 255, message = "产品负责人超长，最多255个字符")
	private String prdOwner;

    /**
    * 开发负责人
    * [开发负责人]
    */
    @Column(name = "dev_manager", nullable = true, updatable = true)
	@Size(max = 255, message = "开发负责人超长，最多255个字符")
	private String devManager;

    /**
    * 维护负责人
    * [维护负责人]
    */
    @Column(name = "ops_manager", nullable = true, updatable = true)
	@Size(max = 255, message = "维护负责人超长，最多255个字符")
	private String opsManager;

    /**
    * 业务线
    * [业务线]
    */
    @Column(name = "biz_line", nullable = true, updatable = true)
	@Size(max = 255, message = "业务线超长，最多255个字符")
	private String bizLine;

    /**
    * 业务代表
    * [业务代表]
    */
    @Column(name = "biz_manager", nullable = true, updatable = true)
	@Size(max = 255, message = "业务代表超长，最多255个字符")
	private String bizManager;

    /**
    * 启用时间
    * [启用时间]-启用时间(产品首次上线时间)
    */
    @Column(name = "golive", nullable = true, updatable = true)
	@Temporal(TemporalType.DATE)
	private Date golive;

    /**
    * 主要客户
    * [主要客户]
    */
    @Column(name = "major_cust", nullable = true, updatable = true)
	@Size(max = 255, message = "主要客户超长，最多255个字符")
	private String majorCust;

    /**
    * 客户代表
    * [客户代表]
    */
    @Column(name = "cust_manager", nullable = true, updatable = true)
	@Size(max = 255, message = "客户代表超长，最多255个字符")
	private String custManager;

    /**
    * 客户使用情况
    * [客户使用情况]-客户使用情况，如客户流量、使用频度等
    */
    @Column(name = "cust_usage", nullable = true, updatable = true)
	@Size(max = 255, message = "客户使用情况超长，最多255个字符")
	private String custUsage;

    /**
    * 获取方式
    * [获取方式]-自主开发,外包开发,联合开发,产品采购,产品租用,其它
    */
    @Column(name = "acquisition_mode", nullable = true, updatable = true)
	@Size(max = 255, message = "获取方式超长，最多255个字符")
	private String acquisitionMode;

    /**
    * 获取方式说明
    * [获取方式说明]
    */
    @Column(name = "acquisition_desc", nullable = true, updatable = true)
	@Size(max = 255, message = "获取方式说明超长，最多255个字符")
	private String acquisitionDesc;

    /**
    * 版本
    * [版本]-当前版本
    */
    @Column(name = "version", nullable = true, updatable = true)
	@Size(max = 255, message = "版本超长，最多255个字符")
	private String version;

    /**
    * 阶段
    * [阶段]-产品调研,产品设计,产品开发,试运行,产品维护,产品停用
    */
    @Column(name = "phase", nullable = true, updatable = true)
	@Size(max = 255, message = "阶段超长，最多255个字符")
	private String phase;

    /**
    * 状态
    * [状态]-未开始,进行中,已完成,暂停,取消
    */
    @Column(name = "status", nullable = true, updatable = true)
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;

    /**
    * 备注
    * [备注]
    */
    @Column(name = "notes", nullable = true, updatable = true)
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String notes;

    /**
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    @Column(name = "record_state", nullable = true, updatable = true)
	private Integer recordState;

    /**
    * 创建用户代码
    * [创建用户代码]
    */
    @Column(name = "create_ucode", nullable = true, updatable = true)
	@Size(max = 255, message = "创建用户代码超长，最多255个字符")
	private String createUcode;

    /**
    * 创建用户姓名
    * [创建用户姓名]
    */
    @Column(name = "create_uname", nullable = true, updatable = true)
	@Size(max = 255, message = "创建用户姓名超长，最多255个字符")
	private String createUname;

    /**
    * 修改用户代码
    * [修改用户代码]
    */
    @Column(name = "cmodify_ucode", nullable = true, updatable = true)
	@Size(max = 255, message = "修改用户代码超长，最多255个字符")
	private String cmodifyUcode;

    /**
    * 修改用户姓名
    * [修改用户姓名]
    */
    @Column(name = "modify_uname", nullable = true, updatable = true)
	@Size(max = 255, message = "修改用户姓名超长，最多255个字符")
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

