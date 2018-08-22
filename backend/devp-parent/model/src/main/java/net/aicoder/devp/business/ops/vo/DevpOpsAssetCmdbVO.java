package net.aicoder.devp.business.ops.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* IT资产配置数据库的值对象
*/
@ApiModel(value = "展现IT资产配置数据库的值对象")
public class DevpOpsAssetCmdbVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    @ApiModelProperty(value = "租户编号", notes = "[租户编号]")
    private Long tid;


    /**etype*/
    @ApiModelProperty(value = "etype", notes = "")
    private String etype;


    /**name*/
    @ApiModelProperty(value = "name", notes = "")
    private String name;


    /**code*/
    @ApiModelProperty(value = "code", notes = "")
    private String code;


    /**alias*/
    @ApiModelProperty(value = "alias", notes = "")
    private String alias;


    /**description*/
    @ApiModelProperty(value = "description", notes = "")
    private String description;


    @ApiModelProperty(value = "记录状态", notes = "[记录状态]-0-失效;1-生效;缺省为1")
    private Integer recordState;


    /**type_code*/
    @ApiModelProperty(value = "type_code", notes = "")
    private String typeCode;


    /**type_name*/
    @ApiModelProperty(value = "type_name", notes = "")
    private String typeName;


    /**stereotype*/
    @ApiModelProperty(value = "stereotype", notes = "")
    private String stereotype;


    /**scope*/
    @ApiModelProperty(value = "scope", notes = "")
    private String scope;


    /**hardware_model*/
    @ApiModelProperty(value = "hardware_model", notes = "")
    private String hardwareModel;


    /**software_model*/
    @ApiModelProperty(value = "software_model", notes = "")
    private String softwareModel;


    /**version*/
    @ApiModelProperty(value = "version", notes = "")
    private String version;


    /**status*/
    @ApiModelProperty(value = "status", notes = "")
    private String status;


    /**创建时间*/
    @ApiModelProperty(value = "创建时间", notes = "[创建时间]-启用时间(产品首次上线时间)")
    private Date createDate;


    /**到期时间*/
    @ApiModelProperty(value = "到期时间", notes = "[到期时间]-到期或报废时间")
    private Date expireDate;


    /**asset_project*/
    @ApiModelProperty(value = "asset_project", notes = "")
    private String assetProject;


    /**asset_area*/
    @ApiModelProperty(value = "asset_area", notes = "")
    private String assetArea;


    /**asset_location*/
    @ApiModelProperty(value = "asset_location", notes = "")
    private String assetLocation;


    /**int_access_addr*/
    @ApiModelProperty(value = "int_access_addr", notes = "")
    private String intAccessAddr;


    /**ext_access_addr*/
    @ApiModelProperty(value = "ext_access_addr", notes = "")
    private String extAccessAddr;


    /**acquisition_mode*/
    @ApiModelProperty(value = "acquisition_mode", notes = "")
    private String acquisitionMode;


    /**acquisition_desc*/
    @ApiModelProperty(value = "acquisition_desc", notes = "")
    private String acquisitionDesc;


    /**asset_dept*/
    @ApiModelProperty(value = "asset_dept", notes = "")
    private String assetDept;


    /**asset_manager*/
    @ApiModelProperty(value = "asset_manager", notes = "")
    private String assetManager;


    /**use_dept*/
    @ApiModelProperty(value = "use_dept", notes = "")
    private String useDept;


    /**use_manager*/
    @ApiModelProperty(value = "use_manager", notes = "")
    private String useManager;


    /**ops_dept*/
    @ApiModelProperty(value = "ops_dept", notes = "")
    private String opsDept;


    /**ops_manager*/
    @ApiModelProperty(value = "ops_manager", notes = "")
    private String opsManager;


    /**biz_line*/
    @ApiModelProperty(value = "biz_line", notes = "")
    private String bizLine;


    /**biz_manager*/
    @ApiModelProperty(value = "biz_manager", notes = "")
    private String bizManager;


    /**启用时间*/
    @ApiModelProperty(value = "启用时间", notes = "[启用时间]-启用时间(产品首次上线时间)")
    private Date goliveDate;


    /**major_cust*/
    @ApiModelProperty(value = "major_cust", notes = "")
    private String majorCust;


    /**cust_manager*/
    @ApiModelProperty(value = "cust_manager", notes = "")
    private String custManager;


    /**cust_usage*/
    @ApiModelProperty(value = "cust_usage", notes = "")
    private String custUsage;


    /**notes*/
    @ApiModelProperty(value = "notes", notes = "")
    private String notes;


    @ApiModelProperty(value = "关联产品租户编号", notes = "[关联产品租户编号]")
    private Long prdTid;


    @ApiModelProperty(value = "关联产品记录编号", notes = "[关联产品记录编号]")
    private Long prdRid;


    /**paras_code*/
    @ApiModelProperty(value = "paras_code", notes = "")
    private String parasCode;


    /**create_ucode*/
    @ApiModelProperty(value = "create_ucode", notes = "")
    private String createUcode;


    /**create_uname*/
    @ApiModelProperty(value = "create_uname", notes = "")
    private String createUname;


    /**modify_ucode*/
    @ApiModelProperty(value = "modify_ucode", notes = "")
    private String modifyUcode;


    /**modify_uname*/
    @ApiModelProperty(value = "modify_uname", notes = "")
    private String modifyUname;


    /**acquisition_provider*/
    @ApiModelProperty(value = "acquisition_provider", notes = "")
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