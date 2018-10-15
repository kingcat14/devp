package net.aicoder.devp.business.sys.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
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
 * 功能模块
 * @author icode
 */
@Entity
@Table(appliesTo = "devp_dev_funa_module", comment = "[功能模块]")
//@DynamicUpdate
//@DynamicInsert
public class DevpDevFunaModule extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_MDU_FLAG = "mduFlag";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_STEREOTYPE = "stereotype";
	public static final String PROPERTY_SCOPE = "scope";
	public static final String PROPERTY_ACTOR = "actor";
	public static final String PROPERTY_PRIORITY = "priority";
	public static final String PROPERTY_VERSION = "version";
	public static final String PROPERTY_PHASE = "phase";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_PRD_RID = "prdRid";
	public static final String PROPERTY_PARENT_RID = "parentRid";
	public static final String PROPERTY_SEQ = "seq";
	public static final String PROPERTY_NOTES = "notes";
	public static final String PROPERTY_RECORD_STATE = "recordState";
	public static final String PROPERTY_CREATE_UCODE = "createUcode";
	public static final String PROPERTY_CREATE_UNAME = "createUname";
	public static final String PROPERTY_MODIFY_UCODE = "modifyUcode";
	public static final String PROPERTY_MODIFY_UNAME = "modifyUname";


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
    * 模块名称
    * [模块名称]
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "模块名称超长，最多255个字符")
	private String name;

    /**
    * 模块代码
    * [模块代码]
    */
    @Column(name = "code", nullable = true, updatable = true)
	@Size(max = 255, message = "模块代码超长，最多255个字符")
	private String code;

    /**
    * 模块别名
    * [模块别名]
    */
    @Column(name = "alias", nullable = true, updatable = true)
	@Size(max = 255, message = "模块别名超长，最多255个字符")
	private String alias;

    /**
    * 模块描述
    * [模块描述]
    */
    @Column(name = "description", nullable = true, updatable = true)
	@Size(max = 255, message = "模块描述超长，最多255个字符")
	private String description;

    /**
    * 模块标志
    * [模块标志]-0:产品;1:模块;2:功能
    */
    @Column(name = "mdu_flag", nullable = true, updatable = true)
	private Integer mduFlag;

    /**
    * 类型
    * [类型]
    */
    @Column(name = "type", nullable = true, updatable = true)
	@Size(max = 255, message = "类型超长，最多255个字符")
	private String type;

    /**
    * 构造型
    * [构造型]
    */
    @Column(name = "stereotype", nullable = true, updatable = true)
	@Size(max = 255, message = "构造型超长，最多255个字符")
	private String stereotype;

    /**
    * 访问控制范围
    * [访问控制范围]-访问控制范围:产品内可见，同模块可见，模块内可见
    */
    @Column(name = "scope", nullable = true, updatable = true)
	@Size(max = 255, message = "访问控制范围超长，最多255个字符")
	private String scope;

    /**
    * 使用者
    * [使用者]
    */
    @Column(name = "actor", nullable = true, updatable = true)
	@Size(max = 255, message = "使用者超长，最多255个字符")
	private String actor;

    /**
    * 优先级
    * [优先级]
    */
    @Column(name = "priority", nullable = true, updatable = true)
	@Size(max = 255, message = "优先级超长，最多255个字符")
	private String priority;

    /**
    * 版本
    * [版本]-当前版本
    */
    @Column(name = "version", nullable = true, updatable = true)
	@Size(max = 255, message = "版本超长，最多255个字符")
	private String version;

    /**
    * 阶段
    * [阶段]-需求调研，需求分析,软件设计,软件开发，系统测试，功能交付
    */
    @Column(name = "phase", nullable = true, updatable = true)
	@Size(max = 255, message = "阶段超长，最多255个字符")
	private String phase;

    /**
    * 状态
    * [状态]-未开始,进行中,已完成,暂停,取消
    */
    @Column(name = "status", nullable = true, updatable = true)
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;

    /**
    * 产品编号
    * [产品编号]
    */
    @Column(name = "prd_rid", nullable = true, updatable = true)
	private Long prdRid;

    /**
    * 父模块编号
    * [父模块编号]
    */
    @Column(name = "parent_rid", nullable = true, updatable = true)
	private Long parentRid;

    /**
    * 顺序号
    * [顺序号]
    */
    @Column(name = "seq", nullable = true, updatable = true)
	private Integer seq;

    /**
    * 备注
    * [备注]
    */
    @Column(name = "notes", nullable = true, updatable = true)
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String notes;

    /**
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    @Column(name = "record_state", nullable = true, updatable = true)
	private Integer recordState;

    /**
    * 创建用户代码
    * [创建用户代码]
    */
    @Column(name = "create_ucode", nullable = true, updatable = true)
	@Size(max = 255, message = "创建用户代码超长，最多255个字符")
	private String createUcode;

    /**
    * 创建用户姓名
    * [创建用户姓名]
    */
    @Column(name = "create_uname", nullable = true, updatable = true)
	@Size(max = 255, message = "创建用户姓名超长，最多255个字符")
	private String createUname;

    /**
    * 修改用户代码
    * [修改用户代码]
    */
    @Column(name = "modify_ucode", nullable = true, updatable = true)
	@Size(max = 255, message = "修改用户代码超长，最多255个字符")
	private String modifyUcode;

    /**
    * 修改用户姓名
    * [修改用户姓名]
    */
    @Column(name = "modify_uname", nullable = true, updatable = true)
	@Size(max = 255, message = "修改用户姓名超长，最多255个字符")
	private String modifyUname;

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

	public Integer getMduFlag(){
		return mduFlag;
	}
	public void setMduFlag(Integer mduFlag) {
		this.mduFlag = mduFlag;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
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

	public String getActor(){
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getPriority(){
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
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

	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
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

