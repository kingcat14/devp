package net.aicoder.speedcloud.asset.business.asset.info.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;



/**
 * IT资产归属
 * @author icode
 */
@Entity()
@Table(name = "asset_asset_ownership")
//@DynamicUpdate
//@DynamicInsert
public class AssetOwnership extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_CATEGORY = "category";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_TYPE_CODE = "typeCode";
	public static final String PROPERTY_TYPE_NAME = "typeName";
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
    * 资产名称
    * 
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "资产名称超长，最多255个字符")
	private String name;

    /**
    * 资产代码
    * 
    */
    @Column(name = "code", nullable = true, updatable = true)
	@Size(max = 255, message = "资产代码超长，最多255个字符")
	private String code;

    /**
    * 资产大类
    * 
    */
    @Column(name = "category", nullable = true, updatable = true)
	private Long category;

    /**
    * 资产分类
    * 
    */
    @Column(name = "type", nullable = true, updatable = true)
	private Long type;

    /**
    * 资产分类代码
    * 
    */
    @Column(name = "type_code", nullable = true, updatable = true)
	@Size(max = 255, message = "资产分类代码超长，最多255个字符")
	private String typeCode;

    /**
    * 资产分类名称
    * 
    */
    @Column(name = "type_name", nullable = true, updatable = true)
	@Size(max = 255, message = "资产分类名称超长，最多255个字符")
	private String typeName;

    /**
    * 资产部门
    * 
    */
    @Column(name = "asset_dept", nullable = true, updatable = true)
	@Size(max = 255, message = "资产部门超长，最多255个字符")
	private String assetDept;

    /**
    * 资产负责人
    * 
    */
    @Column(name = "asset_manager", nullable = true, updatable = true)
	@Size(max = 255, message = "资产负责人超长，最多255个字符")
	private String assetManager;

    /**
    * 使用部门
    * 
    */
    @Column(name = "use_dept", nullable = true, updatable = true)
	@Size(max = 255, message = "使用部门超长，最多255个字符")
	private String useDept;

    /**
    * 使用负责人
    * 
    */
    @Column(name = "use_manager", nullable = true, updatable = true)
	@Size(max = 255, message = "使用负责人超长，最多255个字符")
	private String useManager;

    /**
    * 操作部门
    * 
    */
    @Column(name = "ops_dept", nullable = true, updatable = true)
	@Size(max = 255, message = "操作部门超长，最多255个字符")
	private String opsDept;

    /**
    * 操作负责人
    * 
    */
    @Column(name = "ops_manager", nullable = true, updatable = true)
	@Size(max = 255, message = "操作负责人超长，最多255个字符")
	private String opsManager;

    /**
    * 业务线
    * 
    */
    @Column(name = "biz_line", nullable = true, updatable = true)
	@Size(max = 255, message = "业务线超长，最多255个字符")
	private String bizLine;

    /**
    * 业务负责人
    * 
    */
    @Column(name = "biz_manager", nullable = true, updatable = true)
	@Size(max = 255, message = "业务负责人超长，最多255个字符")
	private String bizManager;

    /**
    * 启用时间
    * [启用时间]-启用时间(产品首次上线时间)
    */
    @Column(name = "golive_date", nullable = true, updatable = true)
	@Temporal(TemporalType.DATE)
	private Date goliveDate;

    /**
    * 主要客户
    * 
    */
    @Column(name = "major_cust", nullable = true, updatable = true)
	@Size(max = 255, message = "主要客户超长，最多255个字符")
	private String majorCust;

    /**
    * 客户负责人
    * 
    */
    @Column(name = "cust_manager", nullable = true, updatable = true)
	@Size(max = 255, message = "客户负责人超长，最多255个字符")
	private String custManager;

    /**
    * 客户使用情况
    * 
    */
    @Column(name = "cust_usage", nullable = true, updatable = true)
	@Size(max = 255, message = "客户使用情况超长，最多255个字符")
	private String custUsage;

    /**
    * 供应商
    * 
    */
    @Column(name = "acquisition_provider", nullable = true, updatable = true)
	@Size(max = 255, message = "供应商超长，最多255个字符")
	private String acquisitionProvider;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
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

	public Long getCategory(){
		return category;
	}
	public void setCategory(Long category) {
		this.category = category;
	}

	public Long getType(){
		return type;
	}
	public void setType(Long type) {
		this.type = type;
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

