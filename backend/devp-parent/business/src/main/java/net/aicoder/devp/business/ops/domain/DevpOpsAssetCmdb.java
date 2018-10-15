package net.aicoder.devp.business.ops.domain;

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
 * IT资产配置数据库
 * @author icode
 */
@Entity
@Table(appliesTo = "devp_ops_asset_cmdb", comment = "[IT资产配置数据库]")
//@DynamicUpdate
//@DynamicInsert
public class DevpOpsAssetCmdb extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ETYPE = "etype";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_RECORD_STATE = "recordState";
	public static final String PROPERTY_TYPE_CODE = "typeCode";
	public static final String PROPERTY_TYPE_NAME = "typeName";
	public static final String PROPERTY_STEREOTYPE = "stereotype";
	public static final String PROPERTY_SCOPE = "scope";
	public static final String PROPERTY_HARDWARE_MODEL = "hardwareModel";
	public static final String PROPERTY_SOFTWARE_MODEL = "softwareModel";
	public static final String PROPERTY_VERSION = "version";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_CREATE_DATE = "createDate";
	public static final String PROPERTY_EXPIRE_DATE = "expireDate";
	public static final String PROPERTY_ASSET_PROJECT = "assetProject";
	public static final String PROPERTY_ASSET_AREA = "assetArea";
	public static final String PROPERTY_ASSET_LOCATION = "assetLocation";
	public static final String PROPERTY_INT_ACCESS_ADDR = "intAccessAddr";
	public static final String PROPERTY_EXT_ACCESS_ADDR = "extAccessAddr";
	public static final String PROPERTY_ACQUISITION_MODE = "acquisitionMode";
	public static final String PROPERTY_ACQUISITION_DESC = "acquisitionDesc";
	public static final String PROPERTY_ASSET_DEPT = "assetDept";
	public static final String PROPERTY_ASSET_MANAGER = "assetManager";
	public static final String PROPERTY_USE_DEPT = "useDept";
	public static final String PROPERTY_USE_MANAGER = "useManager";
	public static final String PROPERTY_OPS_DEPT = "opsDept";
	public static final String PROPERTY_OPS_MANAGER = "opsManager";
	public static final String PROPERTY_BIZ_LINE = "bizLine";
	public static final String PROPERTY_BIZ_MANAGER = "bizManager";
	public static final String PROPERTY_GOLIVE_DATE = "goliveDate";
	public static final String PROPERTY_MAJOR_CUST = "majorCust";
	public static final String PROPERTY_CUST_MANAGER = "custManager";
	public static final String PROPERTY_CUST_USAGE = "custUsage";
	public static final String PROPERTY_NOTES = "notes";
	public static final String PROPERTY_PRD_TID = "prdTid";
	public static final String PROPERTY_PRD_RID = "prdRid";
	public static final String PROPERTY_PARAS_CODE = "parasCode";
	public static final String PROPERTY_CREATE_UCODE = "createUcode";
	public static final String PROPERTY_CREATE_UNAME = "createUname";
	public static final String PROPERTY_MODIFY_UCODE = "modifyUcode";
	public static final String PROPERTY_MODIFY_UNAME = "modifyUname";
	public static final String PROPERTY_ACQUISITION_PROVIDER = "acquisitionProvider";


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
    * name
    * 
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "name超长，最多255个字符")
	private String name;

    /**
    * code
    * 
    */
    @Column(name = "code", nullable = true, updatable = true)
	@Size(max = 255, message = "code超长，最多255个字符")
	private String code;

    /**
    * alias
    * 
    */
    @Column(name = "alias", nullable = true, updatable = true)
	@Size(max = 255, message = "alias超长，最多255个字符")
	private String alias;

    /**
    * description
    * 
    */
    @Column(name = "description", nullable = true, updatable = true)
	@Size(max = 255, message = "description超长，最多255个字符")
	private String description;

    /**
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    @Column(name = "record_state", nullable = true, updatable = true)
	private Integer recordState;

    /**
    * type_code
    * 
    */
    @Column(name = "type_code", nullable = true, updatable = true)
	@Size(max = 255, message = "type_code超长，最多255个字符")
	private String typeCode;

    /**
    * type_name
    * 
    */
    @Column(name = "type_name", nullable = true, updatable = true)
	@Size(max = 255, message = "type_name超长，最多255个字符")
	private String typeName;

    /**
    * stereotype
    * 
    */
    @Column(name = "stereotype", nullable = true, updatable = true)
	@Size(max = 255, message = "stereotype超长，最多255个字符")
	private String stereotype;

    /**
    * scope
    * 
    */
    @Column(name = "scope", nullable = true, updatable = true)
	@Size(max = 255, message = "scope超长，最多255个字符")
	private String scope;

    /**
    * hardware_model
    * 
    */
    @Column(name = "hardware_model", nullable = true, updatable = true)
	@Size(max = 255, message = "hardware_model超长，最多255个字符")
	private String hardwareModel;

    /**
    * software_model
    * 
    */
    @Column(name = "software_model", nullable = true, updatable = true)
	@Size(max = 255, message = "software_model超长，最多255个字符")
	private String softwareModel;

    /**
    * version
    * 
    */
    @Column(name = "version", nullable = true, updatable = true)
	@Size(max = 255, message = "version超长，最多255个字符")
	private String version;

    /**
    * status
    * 
    */
    @Column(name = "status", nullable = true, updatable = true)
	@Size(max = 255, message = "status超长，最多255个字符")
	private String status;

    /**
    * 创建时间
    * [创建时间]-启用时间(产品首次上线时间)
    */
    @Column(name = "create_date", nullable = true, updatable = true)
	@Temporal(TemporalType.DATE)
	private Date createDate;

    /**
    * 到期时间
    * [到期时间]-到期或报废时间
    */
    @Column(name = "expire_date", nullable = true, updatable = true)
	@Temporal(TemporalType.DATE)
	private Date expireDate;

    /**
    * asset_project
    * 
    */
    @Column(name = "asset_project", nullable = true, updatable = true)
	@Size(max = 255, message = "asset_project超长，最多255个字符")
	private String assetProject;

    /**
    * asset_area
    * 
    */
    @Column(name = "asset_area", nullable = true, updatable = true)
	@Size(max = 255, message = "asset_area超长，最多255个字符")
	private String assetArea;

    /**
    * asset_location
    * 
    */
    @Column(name = "asset_location", nullable = true, updatable = true)
	@Size(max = 255, message = "asset_location超长，最多255个字符")
	private String assetLocation;

    /**
    * int_access_addr
    * 
    */
    @Column(name = "int_access_addr", nullable = true, updatable = true)
	@Size(max = 255, message = "int_access_addr超长，最多255个字符")
	private String intAccessAddr;

    /**
    * ext_access_addr
    * 
    */
    @Column(name = "ext_access_addr", nullable = true, updatable = true)
	@Size(max = 255, message = "ext_access_addr超长，最多255个字符")
	private String extAccessAddr;

    /**
    * acquisition_mode
    * 
    */
    @Column(name = "acquisition_mode", nullable = true, updatable = true)
	@Size(max = 255, message = "acquisition_mode超长，最多255个字符")
	private String acquisitionMode;

    /**
    * acquisition_desc
    * 
    */
    @Column(name = "acquisition_desc", nullable = true, updatable = true)
	@Size(max = 255, message = "acquisition_desc超长，最多255个字符")
	private String acquisitionDesc;

    /**
    * asset_dept
    * 
    */
    @Column(name = "asset_dept", nullable = true, updatable = true)
	@Size(max = 255, message = "asset_dept超长，最多255个字符")
	private String assetDept;

    /**
    * asset_manager
    * 
    */
    @Column(name = "asset_manager", nullable = true, updatable = true)
	@Size(max = 255, message = "asset_manager超长，最多255个字符")
	private String assetManager;

    /**
    * use_dept
    * 
    */
    @Column(name = "use_dept", nullable = true, updatable = true)
	@Size(max = 255, message = "use_dept超长，最多255个字符")
	private String useDept;

    /**
    * use_manager
    * 
    */
    @Column(name = "use_manager", nullable = true, updatable = true)
	@Size(max = 255, message = "use_manager超长，最多255个字符")
	private String useManager;

    /**
    * ops_dept
    * 
    */
    @Column(name = "ops_dept", nullable = true, updatable = true)
	@Size(max = 255, message = "ops_dept超长，最多255个字符")
	private String opsDept;

    /**
    * ops_manager
    * 
    */
    @Column(name = "ops_manager", nullable = true, updatable = true)
	@Size(max = 255, message = "ops_manager超长，最多255个字符")
	private String opsManager;

    /**
    * biz_line
    * 
    */
    @Column(name = "biz_line", nullable = true, updatable = true)
	@Size(max = 255, message = "biz_line超长，最多255个字符")
	private String bizLine;

    /**
    * biz_manager
    * 
    */
    @Column(name = "biz_manager", nullable = true, updatable = true)
	@Size(max = 255, message = "biz_manager超长，最多255个字符")
	private String bizManager;

    /**
    * 启用时间
    * [启用时间]-启用时间(产品首次上线时间)
    */
    @Column(name = "golive_date", nullable = true, updatable = true)
	@Temporal(TemporalType.DATE)
	private Date goliveDate;

    /**
    * major_cust
    * 
    */
    @Column(name = "major_cust", nullable = true, updatable = true)
	@Size(max = 255, message = "major_cust超长，最多255个字符")
	private String majorCust;

    /**
    * cust_manager
    * 
    */
    @Column(name = "cust_manager", nullable = true, updatable = true)
	@Size(max = 255, message = "cust_manager超长，最多255个字符")
	private String custManager;

    /**
    * cust_usage
    * 
    */
    @Column(name = "cust_usage", nullable = true, updatable = true)
	@Size(max = 255, message = "cust_usage超长，最多255个字符")
	private String custUsage;

    /**
    * notes
    * 
    */
    @Column(name = "notes", nullable = true, updatable = true)
	@Size(max = 255, message = "notes超长，最多255个字符")
	private String notes;

    /**
    * 关联产品租户编号
    * [关联产品租户编号]
    */
    @Column(name = "prd_tid", nullable = true, updatable = true)
	private Long prdTid;

    /**
    * 关联产品记录编号
    * [关联产品记录编号]
    */
    @Column(name = "prd_rid", nullable = true, updatable = true)
	private Long prdRid;

    /**
    * paras_code
    * 
    */
    @Column(name = "paras_code", nullable = true, updatable = true)
	@Size(max = 255, message = "paras_code超长，最多255个字符")
	private String parasCode;

    /**
    * create_ucode
    * 
    */
    @Column(name = "create_ucode", nullable = true, updatable = true)
	@Size(max = 255, message = "create_ucode超长，最多255个字符")
	private String createUcode;

    /**
    * create_uname
    * 
    */
    @Column(name = "create_uname", nullable = true, updatable = true)
	@Size(max = 255, message = "create_uname超长，最多255个字符")
	private String createUname;

    /**
    * modify_ucode
    * 
    */
    @Column(name = "modify_ucode", nullable = true, updatable = true)
	@Size(max = 255, message = "modify_ucode超长，最多255个字符")
	private String modifyUcode;

    /**
    * modify_uname
    * 
    */
    @Column(name = "modify_uname", nullable = true, updatable = true)
	@Size(max = 255, message = "modify_uname超长，最多255个字符")
	private String modifyUname;

    /**
    * acquisition_provider
    * 
    */
    @Column(name = "acquisition_provider", nullable = true, updatable = true)
	@Size(max = 255, message = "acquisition_provider超长，最多255个字符")
	private String acquisitionProvider;

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

	public String getModifyUcode(){
		return modifyUcode;
	}
	public void setModifyUcode(String modifyUcode) {
		this.modifyUcode = modifyUcode;
	}

	public String getModifyUname(){
		return modifyUname;
	}
	public void setModifyUname(String modifyUname) {
		this.modifyUname = modifyUname;
	}

	public String getAcquisitionProvider(){
		return acquisitionProvider;
	}
	public void setAcquisitionProvider(String acquisitionProvider) {
		this.acquisitionProvider = acquisitionProvider;
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

