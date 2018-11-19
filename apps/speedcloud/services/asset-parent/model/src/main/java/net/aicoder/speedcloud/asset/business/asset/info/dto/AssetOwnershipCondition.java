package net.aicoder.speedcloud.asset.business.asset.info.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


@ApiModel(value = "查询IT资产归属使用的DTO")
public class AssetOwnershipCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户编号", notes = "[租户编号]")
	private Long tid;
	@ApiModelProperty(value = "租户编号最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户编号最小值")
	private Long tidMin;
	@ApiModelProperty(value = "资产名称", notes = "")
	private String name;
	@ApiModelProperty(value = "资产代码", notes = "")
	private String code;
    @ApiModelProperty(value = "资产大类")
    private Long category;
    @ApiModelProperty(value = "资产分类")
    private Long type;
	@ApiModelProperty(value = "资产分类代码", notes = "")
	private String typeCode;
	@ApiModelProperty(value = "资产分类名称", notes = "")
	private String typeName;
	@ApiModelProperty(value = "资产部门", notes = "")
	private String assetDept;
	@ApiModelProperty(value = "资产负责人", notes = "")
	private String assetManager;
	@ApiModelProperty(value = "使用部门", notes = "")
	private String useDept;
	@ApiModelProperty(value = "使用负责人", notes = "")
	private String useManager;
	@ApiModelProperty(value = "操作部门", notes = "")
	private String opsDept;
	@ApiModelProperty(value = "操作负责人", notes = "")
	private String opsManager;
	@ApiModelProperty(value = "业务线", notes = "")
	private String bizLine;
	@ApiModelProperty(value = "业务负责人", notes = "")
	private String bizManager;
	@ApiModelProperty(value = "启用时间", notes = "[启用时间]-启用时间(产品首次上线时间)")
	private Date goliveDate;
	@ApiModelProperty(value = "起始启用时间")
	private Date goliveDateStart;
	@ApiModelProperty(value = "结束启用时间")
	private Date goliveDateEnd;
	@ApiModelProperty(value = "主要客户", notes = "")
	private String majorCust;
	@ApiModelProperty(value = "客户负责人", notes = "")
	private String custManager;
	@ApiModelProperty(value = "客户使用情况", notes = "")
	private String custUsage;
	@ApiModelProperty(value = "供应商", notes = "")
	private String acquisitionProvider;


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




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
