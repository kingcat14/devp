package net.aicoder.devp.business.ops.domain;

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
 * 需求定义
 * @author icode
 */
@Entity
@Table(appliesTo = "devp_ops_requirement", comment = "[需求定义]")
//@DynamicUpdate
//@DynamicInsert
public class DevpOpsRequirement extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ETYPE = "etype";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_NEXUS_TYPE = "nexusType";
	public static final String PROPERTY_NEXUS_RID = "nexusRid";
	public static final String PROPERTY_SEQ = "seq";
	public static final String PROPERTY_TYPE_CODE = "typeCode";
	public static final String PROPERTY_TYPE_NAME = "typeName";
	public static final String PROPERTY_CONTENT = "content";
	public static final String PROPERTY_HAS_ATTACHMENT = "hasAttachment";
	public static final String PROPERTY_STEREOTYPE = "stereotype";
	public static final String PROPERTY_SCOPE = "scope";
	public static final String PROPERTY_VERSION = "version";
	public static final String PROPERTY_PHASE = "phase";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_RECORD_STATE = "recordState";
	public static final String PROPERTY_CREATE_UCODE = "createUcode";
	public static final String PROPERTY_CREATE_UNAME = "createUname";
	public static final String PROPERTY_CMODIFY_UCODE = "cmodifyUcode";
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
    * 元素类型
    * [元素类型]
    */
    @Column(name = "etype", nullable = false, updatable = true)
	@Size(max = 255, message = "元素类型超长，最多255个字符")
	private String etype;

    /**
    * 需求代码
    * [需求代码]
    */
    @Column(name = "code", nullable = true, updatable = true)
	@Size(max = 255, message = "需求代码超长，最多255个字符")
	private String code;

    /**
    * 需求名称
    * [需求名称]
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "需求名称超长，最多255个字符")
	private String name;

    /**
    * 需求别名
    * [需求别名]
    */
    @Column(name = "alias", nullable = true, updatable = true)
	@Size(max = 255, message = "需求别名超长，最多255个字符")
	private String alias;

    /**
    * 需求描述
    * [需求描述]
    */
    @Column(name = "description", nullable = true, updatable = true)
	@Size(max = 255, message = "需求描述超长，最多255个字符")
	private String description;

    /**
    * 关联记录类型
    * [关联记录类型]
    */
    @Column(name = "nexus_type", nullable = false, updatable = true)
	@Size(max = 255, message = "关联记录类型超长，最多255个字符")
	private String nexusType;

    /**
    * 关联记录编号
    * [关联记录编号]
    */
    @Column(name = "nexus_rid", nullable = false, updatable = true)
	private Long nexusRid;

    /**
    * 顺序号
    * [顺序号]
    */
    @Column(name = "seq", nullable = true, updatable = true)
	private Integer seq;

    /**
    * 类型代码
    * [类型代码]
    */
    @Column(name = "type_code", nullable = true, updatable = true)
	@Size(max = 255, message = "类型代码超长，最多255个字符")
	private String typeCode;

    /**
    * 类型名称
    * [类型名称]-冗余字段，方便显示
    */
    @Column(name = "type_name", nullable = true, updatable = true)
	@Size(max = 255, message = "类型名称超长，最多255个字符")
	private String typeName;

    /**
    * 内容
    * [内容]
    */
    @Column(name = "content", nullable = true, updatable = true, length=1999, columnDefinition = "blob")
	private String content;

    /**
    * 是否有附件
    * [是否有附件]-0:无，1:有
    */
    @Column(name = "has_attachment", nullable = true, updatable = true)
	private Integer hasAttachment;

    /**
    * 构造型
    * [构造型]-(保留)
    */
    @Column(name = "stereotype", nullable = true, updatable = true)
	@Size(max = 255, message = "构造型超长，最多255个字符")
	private String stereotype;

    /**
    * 访问控制范围
    * [访问控制范围]-(保留)
    */
    @Column(name = "scope", nullable = true, updatable = true)
	@Size(max = 255, message = "访问控制范围超长，最多255个字符")
	private String scope;

    /**
    * 版本
    * [版本]
    */
    @Column(name = "version", nullable = true, updatable = true)
	@Size(max = 255, message = "版本超长，最多255个字符")
	private String version;

    /**
    * 阶段
    * [阶段]
    */
    @Column(name = "phase", nullable = true, updatable = true)
	@Size(max = 255, message = "阶段超长，最多255个字符")
	private String phase;

    /**
    * 状态
    * [状态]
    */
    @Column(name = "status", nullable = true, updatable = true)
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;

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
    @Column(name = "cmodify_ucode", nullable = true, updatable = true)
	@Size(max = 255, message = "修改用户代码超长，最多255个字符")
	private String cmodifyUcode;

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

	public String getEtype(){
		return etype;
	}
	public void setEtype(String etype) {
		this.etype = etype;
	}

	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	public String getNexusType(){
		return nexusType;
	}
	public void setNexusType(String nexusType) {
		this.nexusType = nexusType;
	}

	public Long getNexusRid(){
		return nexusRid;
	}
	public void setNexusRid(Long nexusRid) {
		this.nexusRid = nexusRid;
	}

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
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

	public String getContent(){
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Integer getHasAttachment(){
		return hasAttachment;
	}
	public void setHasAttachment(Integer hasAttachment) {
		this.hasAttachment = hasAttachment;
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

	public String getCmodifyUcode(){
		return cmodifyUcode;
	}
	public void setCmodifyUcode(String cmodifyUcode) {
		this.cmodifyUcode = cmodifyUcode;
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

