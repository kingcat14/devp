package net.aicoder.devp.business.product.domain;

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
 * 产品干系人
 * @author icode
 */
@Entity
@Table(appliesTo = "devp_prd_person", comment = "[产品干系人]")
//@DynamicUpdate
//@DynamicInsert
public class DevpPrdPerson extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ETYPE = "etype";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_NEXUS_TYPE = "nexusType";
	public static final String PROPERTY_NEXUS_RID = "nexusRid";
	public static final String PROPERTY_SEQ = "seq";
	public static final String PROPERTY_UID = "uid";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_ROLE = "role";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_USER_TID = "userTid";
	public static final String PROPERTY_ORG_RID = "orgRid";
	public static final String PROPERTY_ORG_NAME = "orgName";
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
    * etype
    * 
    */
    @Column(name = "etype", nullable = true, updatable = true)
	@Size(max = 255, message = "etype超长，最多255个字符")
	private String etype;

    /**
    * 用户代码
    * [用户代码]
    */
    @Column(name = "code", nullable = true, updatable = true)
	@Size(max = 255, message = "用户代码超长，最多255个字符")
	private String code;

    /**
    * 用户名称
    * [用户名称]
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "用户名称超长，最多255个字符")
	private String name;

    /**
    * 用户别名
    * [用户别名]
    */
    @Column(name = "alias", nullable = true, updatable = true)
	@Size(max = 255, message = "用户别名超长，最多255个字符")
	private String alias;

    /**
    * 用户描述
    * [用户描述]
    */
    @Column(name = "description", nullable = true, updatable = true)
	@Size(max = 255, message = "用户描述超长，最多255个字符")
	private String description;

    /**
    * 关联元素类型
    * [关联元素类型]-prdline-产品线、product-产品
    */
    @Column(name = "nexus_type", nullable = false, updatable = true)
	@Size(max = 255, message = "关联元素类型超长，最多255个字符")
	private String nexusType;

    /**
    * 关联元素编号
    * [关联元素编号]
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
    * 用户编号
    * [用户编号]
    */
    @Column(name = "uid", nullable = false, updatable = true)
	private Long uid;

    /**
    * 用户类型
    * [用户类型]
    */
    @Column(name = "type", nullable = true, updatable = true)
	@Size(max = 255, message = "用户类型超长，最多255个字符")
	private String type;

    /**
    * 用户类型
    * [用户类型]
    */
    @Column(name = "role", nullable = true, updatable = true)
	@Size(max = 255, message = "用户类型超长，最多255个字符")
	private String role;

    /**
    * 状态
    * [状态]
    */
    @Column(name = "status", nullable = true, updatable = true)
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;

    /**
    * 用户租户编号
    * [用户租户编号]-为未来交易撮合预留
    */
    @Column(name = "user_tid", nullable = true, updatable = true)
	private Long userTid;

    /**
    * 组织编号
    * [组织编号]
    */
    @Column(name = "org_rid", nullable = true, updatable = true)
	private Long orgRid;

    /**
    * 组织名称
    * [组织名称]
    */
    @Column(name = "org_name", nullable = true, updatable = true)
	@Size(max = 255, message = "组织名称超长，最多255个字符")
	private String orgName;

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

	public Long getUid(){
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getRole(){
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Long getUserTid(){
		return userTid;
	}
	public void setUserTid(Long userTid) {
		this.userTid = userTid;
	}

	public Long getOrgRid(){
		return orgRid;
	}
	public void setOrgRid(Long orgRid) {
		this.orgRid = orgRid;
	}

	public String getOrgName(){
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

