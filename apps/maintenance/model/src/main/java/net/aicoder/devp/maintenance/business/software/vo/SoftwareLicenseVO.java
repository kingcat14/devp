package net.aicoder.devp.maintenance.business.software.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import com.yunkang.saas.platform.business.common.vo.SimpleConfigVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 服务许可的值对象
*/
@ApiModel(value = "展现服务许可的值对象")
public class SoftwareLicenseVO {

    @ApiModelProperty(value = "记录id")
    private Long id;

    
    private Long tid;

    
    /**元素类型*/
    @ApiModelProperty(value = "元素类型", notes = "[元素类型]-服务器主机ASSET_HOST, 网络设备ASSET_NETWORK, 基础软件ASSET_INFRA_SW, 服务及许可ASSET_SVC_LIC, 业务软件ASSET_BIZ_SW, 其它ASSET_OTHERS")
    private String etype;

    
    /**名称*/
    @ApiModelProperty(value = "名称", notes = "[名称]-资产名称")
    private String name;

    
    /**代码*/
    @ApiModelProperty(value = "代码", notes = "[代码]-资产代码")
    private String code;

    
    /**别名*/
    @ApiModelProperty(value = "别名", notes = "[别名]-资产别名")
    private String alias;

    
    /**描述*/
    @ApiModelProperty(value = "描述", notes = "[描述]-资产描述")
    private String description;

    
    private Integer recordState;

    
    /**类型代码*/
    @ApiModelProperty(value = "类型代码", notes = "[类型代码]")
    private String typeCode;

    
    /**类型名称*/
    @ApiModelProperty(value = "类型名称", notes = "[类型名称]-冗余字段，方便显示")
    private String typeName;

    
    /**硬件型号*/
    @ApiModelProperty(value = "硬件型号", notes = "[硬件型号]-硬件型号")
    private String hardwareModel;

    
    /**软件型号*/
    @ApiModelProperty(value = "软件型号", notes = "[软件型号]-软件型号,如：操作系统类型")
    private String softwareModel;

    
    /**版本*/
    @ApiModelProperty(value = "版本", notes = "[版本]-当前版本")
    private String version;

    
    /**状态*/
    @ApiModelProperty(value = "状态", notes = "[状态]-未到货,使用中,备用件,维修中,已借出,已报废")
    private String status;
    private SimpleConfigVO statusVO;

    
    /**创建时间*/
    @ApiModelProperty(value = "创建时间", notes = "[创建时间]-启用时间(产品首次上线时间)")
    private Date createDate;

    
    /**到期时间*/
    @ApiModelProperty(value = "到期时间", notes = "[到期时间]-到期或报废时间")
    private Date expireDate;

    
    /**所属项目*/
    @ApiModelProperty(value = "所属项目", notes = "[所属项目]")
    private String assetProject;

    
    /**所属区域*/
    @ApiModelProperty(value = "所属区域", notes = "[所属区域]")
    private String assetArea;

    
    /**资产位置*/
    @ApiModelProperty(value = "资产位置", notes = "[资产位置]")
    private String assetLocation;

    
    /**内部访问地址*/
    @ApiModelProperty(value = "内部访问地址", notes = "[内部访问地址]-内部访问地址，如：内网IP")
    private String intAccessAddr;

    
    /**外部访问地址*/
    @ApiModelProperty(value = "外部访问地址", notes = "[外部访问地址]-外部访问地址，如：外网IP")
    private String extAccessAddr;

    
    /**获取方式*/
    @ApiModelProperty(value = "获取方式", notes = "[获取方式]-自主开发,外包开发,联合开发,产品采购,产品租用,其它")
    private String acquisitionMode;

    
    /**获取方式说明*/
    @ApiModelProperty(value = "获取方式说明", notes = "[获取方式说明]")
    private String acquisitionDesc;

    
    /**归属部门*/
    @ApiModelProperty(value = "归属部门", notes = "[归属部门]")
    private String assetDept;

    
    /**资产负责人*/
    @ApiModelProperty(value = "资产负责人", notes = "[资产负责人]")
    private String assetManager;

    
    /**使用部门*/
    @ApiModelProperty(value = "使用部门", notes = "[使用部门]")
    private String useDept;

    
    /**使用负责人*/
    @ApiModelProperty(value = "使用负责人", notes = "[使用负责人]")
    private String useManager;

    
    /**维护部门*/
    @ApiModelProperty(value = "维护部门", notes = "[维护部门]")
    private String opsDept;

    
    /**维护负责人*/
    @ApiModelProperty(value = "维护负责人", notes = "[维护负责人]")
    private String opsManager;

    
    /**业务线*/
    @ApiModelProperty(value = "业务线", notes = "[业务线]")
    private String bizLine;

    
    /**业务代表*/
    @ApiModelProperty(value = "业务代表", notes = "[业务代表]")
    private String bizManager;

    
    /**启用时间*/
    @ApiModelProperty(value = "启用时间", notes = "[启用时间]-启用时间(产品首次上线时间)")
    private Date goliveDate;

    
    /**主要客户*/
    @ApiModelProperty(value = "主要客户", notes = "[主要客户]")
    private String majorCust;

    
    /**客户代表*/
    @ApiModelProperty(value = "客户代表", notes = "[客户代表]")
    private String custManager;

    
    /**使用情况*/
    @ApiModelProperty(value = "使用情况", notes = "[使用情况]-客户使用情况，如客户流量、使用频度等")
    private String custUsage;

    
    /**备注*/
    @ApiModelProperty(value = "备注", notes = "[备注]")
    private String notes;

    
    /**供应商*/
    @ApiModelProperty(value = "供应商")
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
    public SimpleConfigVO getStatusVO(){
        return statusVO;
    }
    public void setStatusVO(SimpleConfigVO statusVO) {
        this.statusVO = statusVO;
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