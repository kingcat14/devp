package net.aicoder.speedcloud.asset.business.asset.info.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;



/**
 * IT资产
 * @author icode
 */
@Entity()
@Table(name = "asset_asset_cmdb")
//@DynamicUpdate
//@DynamicInsert
public class AssetCmdb extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_BARCODE = "barcode";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_CATEGORY = "category";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_UNIT = "unit";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_CREATE_DATE = "createDate";
	public static final String PROPERTY_EXPIRE_DATE = "expireDate";
	public static final String PROPERTY_ASSET_AREA = "assetArea";
	public static final String PROPERTY_ASSET_LOCATION = "assetLocation";
	public static final String PROPERTY_ACQUISITION_MODE = "acquisitionMode";
	public static final String PROPERTY_ACQUISITION_DESC = "acquisitionDesc";
	public static final String PROPERTY_GOLIVE_DATE = "goliveDate";
	public static final String PROPERTY_NOTES = "notes";


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
    * 资产条码
    * 
    */
    @Column(name = "barcode", nullable = true, updatable = true)
	@Size(max = 255, message = "资产条码超长，最多255个字符")
	private String barcode;

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
    * 资产别名
    * 
    */
    @Column(name = "alias", nullable = true, updatable = true)
	@Size(max = 255, message = "资产别名超长，最多255个字符")
	private String alias;

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
    * 计量单位
    * 
    */
    @Column(name = "unit", nullable = true, updatable = true)
	@Size(max = 255, message = "计量单位超长，最多255个字符")
	private String unit;

    /**
    * 描述
    * 
    */
    @Column(name = "description", nullable = true, updatable = true)
	@Size(max = 255, message = "描述超长，最多255个字符")
	private String description;

    /**
    * 状态
    * 
    */
    @Column(name = "status", nullable = true, updatable = true)
	@Size(max = 255, message = "状态超长，最多255个字符")
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
    * 所在区域
    * 
    */
    @Column(name = "asset_area", nullable = true, updatable = true)
	@Size(max = 255, message = "所在区域超长，最多255个字符")
	private String assetArea;

    /**
    * 所在地址
    * 
    */
    @Column(name = "asset_location", nullable = true, updatable = true)
	@Size(max = 255, message = "所在地址超长，最多255个字符")
	private String assetLocation;

    /**
    * 获取模式
    * 
    */
    @Column(name = "acquisition_mode", nullable = true, updatable = true)
	@Size(max = 255, message = "获取模式超长，最多255个字符")
	private String acquisitionMode;

    /**
    * 获取描述
    * 
    */
    @Column(name = "acquisition_desc", nullable = true, updatable = true)
	@Size(max = 255, message = "获取描述超长，最多255个字符")
	private String acquisitionDesc;

    /**
    * 启用时间
    * [启用时间]-启用时间(产品首次上线时间)
    */
    @Column(name = "golive_date", nullable = true, updatable = true)
	@Temporal(TemporalType.DATE)
	private Date goliveDate;

    /**
    * notes
    * 
    */
    @Column(name = "notes", nullable = true, updatable = true)
	@Size(max = 255, message = "notes超长，最多255个字符")
	private String notes;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getBarcode(){
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
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

	public String getUnit(){
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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

	public Date getGoliveDate(){
		return goliveDate;
	}
	public void setGoliveDate(Date goliveDate) {
		this.goliveDate = goliveDate;
	}

	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
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

