package net.aicoder.speedcloud.asset.business.asset.info.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


@ApiModel(value = "查询IT资产使用的DTO")
public class AssetCmdbCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户编号", notes = "[租户编号]")
	private Long tid;
	@ApiModelProperty(value = "资产条码")
	private String barcode;
	@ApiModelProperty(value = "资产名称", notes = "")
	private String name;
	@ApiModelProperty(value = "资产代码", notes = "")
	private String code;
	@ApiModelProperty(value = "资产别名", notes = "")
	private String alias;
    @ApiModelProperty(value = "资产大类")
    private Long category;
	@ApiModelProperty(value = "资产大类代码")
	private String categoryCode;
    @ApiModelProperty(value = "资产分类")
    private Long type;
	@ApiModelProperty(value = "资产分类代码")
	private String typeCode;
	@ApiModelProperty(value = "计量单位")
	private String unit;
	@ApiModelProperty(value = "描述", notes = "")
	private String description;
	@ApiModelProperty(value = "状态", notes = "")
	private String status;
	@ApiModelProperty(value = "创建时间", notes = "[创建时间]-启用时间(产品首次上线时间)")
	private Date createDate;
	@ApiModelProperty(value = "起始创建时间")
	private Date createDateStart;
	@ApiModelProperty(value = "结束创建时间")
	private Date createDateEnd;
	@ApiModelProperty(value = "到期时间", notes = "[到期时间]-到期或报废时间")
	private Date expireDate;
	@ApiModelProperty(value = "起始到期时间")
	private Date expireDateStart;
	@ApiModelProperty(value = "结束到期时间")
	private Date expireDateEnd;
	@ApiModelProperty(value = "所在区域", notes = "")
	private String assetArea;
	@ApiModelProperty(value = "所在地址", notes = "")
	private String assetLocation;
	@ApiModelProperty(value = "获取模式", notes = "")
	private String acquisitionMode;
	@ApiModelProperty(value = "获取描述", notes = "")
	private String acquisitionDesc;
	@ApiModelProperty(value = "启用时间", notes = "[启用时间]-启用时间(产品首次上线时间)")
	private Date goliveDate;
	@ApiModelProperty(value = "起始启用时间")
	private Date goliveDateStart;
	@ApiModelProperty(value = "结束启用时间")
	private Date goliveDateEnd;
	@ApiModelProperty(value = "notes", notes = "")
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


	public String getCategoryCode(){
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
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


	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
