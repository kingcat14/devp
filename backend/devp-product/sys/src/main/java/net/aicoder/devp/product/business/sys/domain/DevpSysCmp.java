package net.aicoder.devp.product.business.sys.domain;

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
 * 系统组件
 * @author icode
 */
@Entity
@Table(appliesTo = "devp_sys_cmp", comment = "[系统组件]")
//@DynamicUpdate
//@DynamicInsert
public class DevpSysCmp extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ETYPE = "etype";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_RECORD_STATE = "recordState";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_SUB_TYPE = "subType";
	public static final String PROPERTY_STEREOTYPE = "stereotype";
	public static final String PROPERTY_SCOPE = "scope";
	public static final String PROPERTY_VERSION = "version";
	public static final String PROPERTY_PHASE = "phase";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_NOTES = "notes";
	public static final String PROPERTY_INSTALLABLE = "installable";
	public static final String PROPERTY_SHARED_COMPONENT = "sharedComponent";
	public static final String PROPERTY_SHARED_SERVICE = "sharedService";
	public static final String PROPERTY_PRD_RID = "prdRid";
	public static final String PROPERTY_PARENT_RID = "parentRid";
	public static final String PROPERTY_SEQ = "seq";


    @Id
    @Column(name = "rid")
    private Long id;


    /**
    * 租户编号
    * [租户编号]
    */
    @Column(name = "tid")
	@NotNull(message = "租户编号不能为空")
	private Long tid;

    /**
    * 元素类型
    * [元素类型]- SYSTEM// 系统
 SUB_SYS// 子系统
 SYS_CMP// 组件
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
	@NotNull(message = "系统元素名称不能为空")
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
    * 构造型
    * [构造型]
    */
    @Column(name = "stereotype")
	@Size(max = 255, message = "构造型超长，最多255个字符")
	private String stereotype;

    /**
    * 范围
    * [范围]-访问控制范围:产品内可见，同模块可见，模块内可见
    */
    @Column(name = "scope")
	@Size(max = 255, message = "范围超长，最多255个字符")
	private String scope;

    /**
    * 版本
    * [版本]-当前版本
    */
    @Column(name = "version")
	@Size(max = 255, message = "版本超长，最多255个字符")
	private String version;

    /**
    * 阶段
    * [阶段]-系统调研,系统设计,系统开发,试运行,系统运维,已停用
    */
    @Column(name = "phase")
	@Size(max = 255, message = "阶段超长，最多255个字符")
	private String phase;

    /**
    * 状态
    * [状态]-未开始,进行中,已完成,暂停,取消
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
    * 可安装组件
    * [可安装组件]-是否为可安装组件，0:不可独立安装；1：可安装
    */
    @Column(name = "installable")
	private Integer installable;

    /**
    * 共享组件
    * [共享组件]-是否为共享组件，0:不共享；1：共享
    */
    @Column(name = "shared_component")
	private Integer sharedComponent;

    /**
    * 共享服务
    * [共享服务]-是否提供共享服务，0:不共享；1：共享
    */
    @Column(name = "shared_service")
	private Integer sharedService;

    /**
    * 产品编号
    * [产品编号]
    */
    @Column(name = "prd_rid")
	@NotNull(message = "产品编号不能为空")
	private Long prdRid;

    /**
    * 父包编号
    * [父包编号]
    */
    @Column(name = "parent_rid")
	private Long parentRid;

    /**
    * 顺序号
    * [顺序号]
    */
    @Column(name = "seq")
	private Integer seq;

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

	public Integer getInstallable(){
		return installable;
	}
	public void setInstallable(Integer installable) {
		this.installable = installable;
	}

	public Integer getSharedComponent(){
		return sharedComponent;
	}
	public void setSharedComponent(Integer sharedComponent) {
		this.sharedComponent = sharedComponent;
	}

	public Integer getSharedService(){
		return sharedService;
	}
	public void setSharedService(Integer sharedService) {
		this.sharedService = sharedService;
	}

	public Long getPrdRid(){
		return prdRid;
	}
	public void setPrdRid(Long prdRid) {
		this.prdRid = prdRid;
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

