package net.aicoder.devp.product.business.product.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;
import com.yunkang.saas.common.framework.eo.BaseEntity;



/**
 * 产品干系人
 * @author icode
 */
@Entity
@Table
public class DevpPrdPerson extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
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
	public static final String PROPERTY_MODIFY_UCODE = "modifyUcode";


    @Id
    @Column(name = "rid")
    private Long id;


    /**
    * 租户编号
    * 
    */
    @Column(name = "tid", updatable = false)
	@NotNull(message = "租户编号不能为空")
	private Long tid;

    /**
    * 用户代码
    * 
    */
    @Column(name = "code")
	@NotNull(message = "用户代码不能为空")
	@Size(max = 255, message = "用户代码超长，最多255个字符")
	private String code;

    /**
    * 用户名称
    * 
    */
    @Column(name = "name")
	@NotNull(message = "用户名称不能为空")
	@Size(max = 255, message = "用户名称超长，最多255个字符")
	private String name;

    /**
    * 用户别名
    * 
    */
    @Column(name = "alias")
	@Size(max = 255, message = "用户别名超长，最多255个字符")
	private String alias;

    /**
    * 用户描述
    * 
    */
    @Column(name = "description")
	@Size(max = 255, message = "用户描述超长，最多255个字符")
	private String description;

    /**
    * 关联元素类型
    * 
    */
    @Column(name = "nexus_type")
	@NotNull(message = "关联元素类型不能为空")
	@Size(max = 255, message = "关联元素类型超长，最多255个字符")
	private String nexusType;

    /**
    * 关联元素编号
    * 
    */
    @Column(name = "nexus_rid")
	@NotNull(message = "关联元素编号不能为空")
	private Long nexusRid;

    /**
    * 顺序号
    * 
    */
    @Column(name = "seq")
	private Integer seq;

    /**
    * 用户编号
    * 
    */
    @Column(name = "uid")
	@NotNull(message = "用户编号不能为空")
	private Long uid;

    /**
    * 用户类型
    * 
    */
    @Column(name = "type")
	@Size(max = 255, message = "用户类型超长，最多255个字符")
	private String type;

    /**
    * 用户角色
    * 
    */
    @Column(name = "role")
	@Size(max = 255, message = "用户角色超长，最多255个字符")
	private String role;

    /**
    * 状态
    * 
    */
    @Column(name = "status")
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;

    /**
    * 用户租户编号
    * 
    */
    @Column(name = "user_tid")
	private Long userTid;

    /**
    * 组织编号
    * 
    */
    @Column(name = "org_rid")
	private Long orgRid;

    /**
    * 组织名称
    * 
    */
    @Column(name = "org_name")
	@Size(max = 255, message = "组织名称超长，最多255个字符")
	private String orgName;

    /**
    * 记录状态
    * 
    */
    @Column(name = "record_state")
	private Integer recordState;

    /**
    * 创建用户代码
    * 
    */
    @Column(name = "create_ucode")
	@Size(max = 255, message = "创建用户代码超长，最多255个字符")
	private String createUcode;

    /**
    * 修改用户代码
    * 
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

