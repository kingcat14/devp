package net.aicoder.devp.deploy.business.deploy.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotEmpty;
import com.yunkang.saas.common.framework.eo.BaseEntity;



/**
 * 部署关联资源实例
 * @author icode
 */
@Entity
@Table(appliesTo = "devp_sys_dpy_res_inst", comment = "[部署关联资源实例]")
//@DynamicUpdate
//@DynamicInsert
public class DevpSysDpyResInst extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ETYPE = "etype";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_RECORD_STATE = "recordState";
	public static final String PROPERTY_FLAG = "flag";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_SUB_TYPE = "subType";
	public static final String PROPERTY_DPY_MODEL = "dpyModel";
	public static final String PROPERTY_DPY_DESCRIPTION = "dpyDescription";
	public static final String PROPERTY_ACCESS_ADDR = "accessAddr";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_NOTES = "notes";
	public static final String PROPERTY_PRD_RID = "prdRid";
	public static final String PROPERTY_SCHEME_RID = "schemeRid";
	public static final String PROPERTY_RES_RID = "resRid";
	public static final String PROPERTY_PARENT_RID = "parentRid";
	public static final String PROPERTY_SEQ = "seq";
	public static final String PROPERTY_ASSET_RID = "assetRid";
	public static final String PROPERTY_ASSET_ETYPE = "assetEtype";
	public static final String PROPERTY_ASSET_TYPE_CODE = "assetTypeCode";


    @Id
    @Column(name = "rid")
    private Long id;


    /**
    * 租户编号
    * [租户编号]
    */
    @Column(name = "tid", updatable = false)
	@NotNull(message = "租户编号不能为空")
	private Long tid;

    /**
    * 元素类型
    * [元素类型]-SYS_DPY_RES_INST // 关联资源实例
    */
    @Column(name = "etype")
	@NotNull(message = "元素类型不能为空")
	@Size(max = 255, message = "元素类型超长，最多255个字符")
	private String etype;

    /**
    * 系统元素名称
    * [系统元素名称]
    */
    @Column(name = "name")
	@Size(max = 255, message = "系统元素名称超长，最多255个字符")
	private String name;

    /**
    * 系统元素代码
    * [系统元素代码]
    */
    @Column(name = "code")
	@Size(max = 255, message = "系统元素代码超长，最多255个字符")
	private String code;

    /**
    * 系统元素别名
    * [系统元素别名]
    */
    @Column(name = "alias")
	@Size(max = 255, message = "系统元素别名超长，最多255个字符")
	private String alias;

    /**
    * 系统元素描述
    * [系统元素描述]
    */
    @Column(name = "description")
	@Size(max = 255, message = "系统元素描述超长，最多255个字符")
	private String description;

    /**
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    @Column(name = "record_state")
	private Integer recordState;

    /**
    * 资源实例标识
    * [资源实例标识]-同一个部署方案中不能重复
    */
    @Column(name = "flag")
	@Size(max = 255, message = "资源实例标识超长，最多255个字符")
	private String flag;

    /**
    * 类型
    * [类型]
    */
    @Column(name = "type")
	@Size(max = 255, message = "类型超长，最多255个字符")
	private String type;

    /**
    * 子类型
    * [子类型]
    */
    @Column(name = "sub_type")
	@Size(max = 255, message = "子类型超长，最多255个字符")
	private String subType;

    /**
    * 部署模式
    * [部署模式]-主机/容器化/第三方提供/不适用
    */
    @Column(name = "dpy_model")
	@Size(max = 255, message = "部署模式超长，最多255个字符")
	private String dpyModel;

    /**
    * 部署说明
    * [部署说明]
    */
    @Column(name = "dpy_description")
	@Size(max = 255, message = "部署说明超长，最多255个字符")
	private String dpyDescription;

    /**
    * 访问地址
    * [访问地址]-内部访问地址，如：内网IP
    */
    @Column(name = "access_addr")
	@Size(max = 255, message = "访问地址超长，最多255个字符")
	private String accessAddr;

    /**
    * 状态
    * [状态]
    */
    @Column(name = "status")
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;

    /**
    * 备注
    * [备注]
    */
    @Column(name = "notes")
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String notes;

    /**
    * 产品编号
    * [产品编号]
    */
    @Column(name = "prd_rid")
	@NotNull(message = "产品编号不能为空")
	private Long prdRid;

    /**
    * 部署方案编号
    * [部署方案编号]
    */
    @Column(name = "scheme_rid")
	@NotNull(message = "部署方案编号不能为空")
	private Long schemeRid;

    /**
    * 关联资源编号
    * [关联资源编号]
    */
    @Column(name = "res_rid")
	@NotNull(message = "关联资源编号不能为空")
	private Long resRid;

    /**
    * 父包编号
    * [父包编号]-可以组织成一棵树
    */
    @Column(name = "parent_rid")
	private Long parentRid;

    /**
    * 顺序号
    * [顺序号]
    */
    @Column(name = "seq")
	private Integer seq;

    /**
    * 关联IT资产编号
    * [关联IT资产编号]
    */
    @Column(name = "asset_rid")
	private Long assetRid;

    /**
    * 关联IT资产元素类型
    * [关联IT资产元素类型]
    */
    @Column(name = "asset_etype")
	@Size(max = 255, message = "关联IT资产元素类型超长，最多255个字符")
	private String assetEtype;

    /**
    * 关联IT资产类型代码
    * [关联IT资产类型代码]
    */
    @Column(name = "asset_type_code")
	@Size(max = 255, message = "关联IT资产类型代码超长，最多255个字符")
	private String assetTypeCode;

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

	public String getFlag(){
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getSubType(){
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getDpyModel(){
		return dpyModel;
	}
	public void setDpyModel(String dpyModel) {
		this.dpyModel = dpyModel;
	}

	public String getDpyDescription(){
		return dpyDescription;
	}
	public void setDpyDescription(String dpyDescription) {
		this.dpyDescription = dpyDescription;
	}

	public String getAccessAddr(){
		return accessAddr;
	}
	public void setAccessAddr(String accessAddr) {
		this.accessAddr = accessAddr;
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

	public Long getPrdRid(){
		return prdRid;
	}
	public void setPrdRid(Long prdRid) {
		this.prdRid = prdRid;
	}

	public Long getSchemeRid(){
		return schemeRid;
	}
	public void setSchemeRid(Long schemeRid) {
		this.schemeRid = schemeRid;
	}

	public Long getResRid(){
		return resRid;
	}
	public void setResRid(Long resRid) {
		this.resRid = resRid;
	}

	public Long getParentRid(){
		return parentRid;
	}
	public void setParentRid(Long parentRid) {
		this.parentRid = parentRid;
	}

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Long getAssetRid(){
		return assetRid;
	}
	public void setAssetRid(Long assetRid) {
		this.assetRid = assetRid;
	}

	public String getAssetEtype(){
		return assetEtype;
	}
	public void setAssetEtype(String assetEtype) {
		this.assetEtype = assetEtype;
	}

	public String getAssetTypeCode(){
		return assetTypeCode;
	}
	public void setAssetTypeCode(String assetTypeCode) {
		this.assetTypeCode = assetTypeCode;
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

