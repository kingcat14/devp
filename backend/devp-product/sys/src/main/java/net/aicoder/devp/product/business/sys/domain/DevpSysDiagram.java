package net.aicoder.devp.product.business.sys.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;
import com.yunkang.saas.common.framework.eo.BaseEntity;



/**
 * UML图
 * @author icode
 */
@Entity
@Table
public class DevpSysDiagram extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_DGM_FLAG = "dgmFlag";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_SUB_TYPE = "subType";
	public static final String PROPERTY_STEREOTYPE = "stereotype";
	public static final String PROPERTY_SCOPE = "scope";
	public static final String PROPERTY_VERSION = "version";
	public static final String PROPERTY_PHASE = "phase";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_PRD_RID = "prdRid";
	public static final String PROPERTY_ELM_RID = "elmRid";
	public static final String PROPERTY_SEQ = "seq";
	public static final String PROPERTY_RECORD_STATE = "recordState";
	public static final String PROPERTY_CREATE_UCODE = "createUcode";
	public static final String PROPERTY_MODIFY_UCODE = "modifyUcode";


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
    * 系统元素所属类型标识
    * [系统元素所属类型标识]-CMP-组件,IDE-开发,DPY-部署
    */
    @Column(name = "dgm_flag")
	@NotNull(message = "系统元素所属类型标识不能为空")
	@Size(max = 255, message = "系统元素所属类型标识超长，最多255个字符")
	private String dgmFlag;

    /**
    * 类型
    * [类型]-CMP-组件图,IDE-开发图,DPY-部署图
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
    * 产品编号
    * [产品编号]
    */
    @Column(name = "prd_rid")
	@NotNull(message = "产品编号不能为空")
	private Long prdRid;

    /**
    * 系统元素编号
    * [系统元素编号]
    */
    @Column(name = "elm_rid")
	@NotNull(message = "系统元素编号不能为空")
	private Long elmRid;

    /**
    * 顺序号
    * [顺序号]
    */
    @Column(name = "seq")
	private Integer seq;

    /**
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    @Column(name = "record_state")
	private Integer recordState;

    /**
    * 创建用户代码
    * [创建用户代码]
    */
    @Column(name = "create_ucode")
	@Size(max = 255, message = "创建用户代码超长，最多255个字符")
	private String createUcode;

    /**
    * 修改用户代码
    * [修改用户代码]
    */
    @Column(name = "modify_ucode")
	@Size(max = 255, message = "修改用户代码超长，最多255个字符")
	private String modifyUcode;

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

	public String getDgmFlag(){
		return dgmFlag;
	}
	public void setDgmFlag(String dgmFlag) {
		this.dgmFlag = dgmFlag;
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

	public Long getPrdRid(){
		return prdRid;
	}
	public void setPrdRid(Long prdRid) {
		this.prdRid = prdRid;
	}

	public Long getElmRid(){
		return elmRid;
	}
	public void setElmRid(Long elmRid) {
		this.elmRid = elmRid;
	}

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
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

	public String getModifyUcode(){
		return modifyUcode;
	}
	public void setModifyUcode(String modifyUcode) {
		this.modifyUcode = modifyUcode;
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
