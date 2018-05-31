package net.aicoder.devp.product.business.sys.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;
import com.yunkang.saas.common.framework.eo.BaseEntity;



/**
 * 系统元素间关系
 * @author icode
 */
@Entity
@Table
public class DevpSysElmConnector extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_SPRD_RID = "sprdRid";
	public static final String PROPERTY_SELM_RID = "selmRid";
	public static final String PROPERTY_DPRD_RID = "dprdRid";
	public static final String PROPERTY_DELM_RID = "delmRid";
	public static final String PROPERTY_SINST_RID = "sinstRid";
	public static final String PROPERTY_DINST_RID = "dinstRid";
	public static final String PROPERTY_SEQ = "seq";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_SUB_TYPE = "subType";
	public static final String PROPERTY_STEREOTYPE = "stereotype";
	public static final String PROPERTY_SCOPE = "scope";
	public static final String PROPERTY_DIRECTION = "direction";
	public static final String PROPERTY_SRC_MULTI = "srcMulti";
	public static final String PROPERTY_SRC_ROLE = "srcRole";
	public static final String PROPERTY_SRC_ROLE_TYPE = "srcRoleType";
	public static final String PROPERTY_DEST_MULTI = "destMulti";
	public static final String PROPERTY_DEST_ROLE = "destRole";
	public static final String PROPERTY_DEST_ROLE_TYPE = "destRoleType";
	public static final String PROPERTY_ATTR_RELATION = "attrRelation";
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
    * 对应关系代码
    * [对应关系代码]
    */
    @Column(name = "code")
	@Size(max = 255, message = "对应关系代码超长，最多255个字符")
	private String code;

    /**
    * 对应关系名称
    * [对应关系名称]
    */
    @Column(name = "name")
	@Size(max = 255, message = "对应关系名称超长，最多255个字符")
	private String name;

    /**
    * 对应关系别名
    * [对应关系别名]
    */
    @Column(name = "alias")
	@Size(max = 255, message = "对应关系别名超长，最多255个字符")
	private String alias;

    /**
    * 对应关系描述
    * [对应关系描述]
    */
    @Column(name = "description")
	@Size(max = 255, message = "对应关系描述超长，最多255个字符")
	private String description;

    /**
    * 来源产品编号
    * [来源产品编号]
    */
    @Column(name = "sprd_rid")
	@NotNull(message = "来源产品编号不能为空")
	private Long sprdRid;

    /**
    * 来源系统元素编号
    * [来源系统元素编号]
    */
    @Column(name = "selm_rid")
	@NotNull(message = "来源系统元素编号不能为空")
	private Long selmRid;

    /**
    * 目标产品编号
    * [目标产品编号]
    */
    @Column(name = "dprd_rid")
	@NotNull(message = "目标产品编号不能为空")
	private Long dprdRid;

    /**
    * 目标系统元素编号
    * [目标系统元素编号]
    */
    @Column(name = "delm_rid")
	@NotNull(message = "目标系统元素编号不能为空")
	private Long delmRid;

    /**
    * 来源系统元素实例编号
    * [来源系统元素实例编号]-缺省值为0
    */
    @Column(name = "sinst_rid")
	@NotNull(message = "来源系统元素实例编号不能为空")
	private Long sinstRid;

    /**
    * 目标系统元素实例编号
    * [目标系统元素实例编号]-缺省值为0
    */
    @Column(name = "dinst_rid")
	@NotNull(message = "目标系统元素实例编号不能为空")
	private Long dinstRid;

    /**
    * 顺序号
    * [顺序号]
    */
    @Column(name = "seq")
	private Integer seq;

    /**
    * 对应关系类型
    * [对应关系类型]-泛化(generalization)关系,关联(association)关系(关联,聚合,合成),依赖(dependency)关系，实现(realization)
    */
    @Column(name = "type")
	@Size(max = 255, message = "对应关系类型超长，最多255个字符")
	private String type;

    /**
    * 对应关系子类型
    * [对应关系子类型]
    */
    @Column(name = "sub_type")
	@Size(max = 255, message = "对应关系子类型超长，最多255个字符")
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
    * [范围]-可见范围
    */
    @Column(name = "scope")
	@Size(max = 255, message = "范围超长，最多255个字符")
	private String scope;

    /**
    * 方向
    * [方向]-->,<-,UnD,<->,
    */
    @Column(name = "direction")
	@Size(max = 255, message = "方向超长，最多255个字符")
	private String direction;

    /**
    * 来源对应数量
    * [来源对应数量]-0/1/* /0..1/0..* /1..*
    */
    @Column(name = "src_multi")
	@Size(max = 255, message = "来源对应数量超长，最多255个字符")
	private String srcMulti;

    /**
    * 来源角色
    * [来源角色]
    */
    @Column(name = "src_role")
	@Size(max = 255, message = "来源角色超长，最多255个字符")
	private String srcRole;

    /**
    * 来源角色类型
    * [来源角色类型]
    */
    @Column(name = "src_role_type")
	@Size(max = 255, message = "来源角色类型超长，最多255个字符")
	private String srcRoleType;

    /**
    * 目标对应数量
    * [目标对应数量]-0/1/* /0..1/0..* /1..*
    */
    @Column(name = "dest_multi")
	@Size(max = 255, message = "目标对应数量超长，最多255个字符")
	private String destMulti;

    /**
    * 目标角色
    * [目标角色]
    */
    @Column(name = "dest_role")
	@Size(max = 255, message = "目标角色超长，最多255个字符")
	private String destRole;

    /**
    * 目标角色类型
    * [目标角色类型]
    */
    @Column(name = "dest_role_type")
	@Size(max = 255, message = "目标角色类型超长，最多255个字符")
	private String destRoleType;

    /**
    * 属性对应关系
    * [属性对应关系]-描述对象间如何装配，如何转换
    */
    @Column(name = "attr_relation")
	@Size(max = 255, message = "属性对应关系超长，最多255个字符")
	private String attrRelation;

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

	public Long getSprdRid(){
		return sprdRid;
	}
	public void setSprdRid(Long sprdRid) {
		this.sprdRid = sprdRid;
	}

	public Long getSelmRid(){
		return selmRid;
	}
	public void setSelmRid(Long selmRid) {
		this.selmRid = selmRid;
	}

	public Long getDprdRid(){
		return dprdRid;
	}
	public void setDprdRid(Long dprdRid) {
		this.dprdRid = dprdRid;
	}

	public Long getDelmRid(){
		return delmRid;
	}
	public void setDelmRid(Long delmRid) {
		this.delmRid = delmRid;
	}

	public Long getSinstRid(){
		return sinstRid;
	}
	public void setSinstRid(Long sinstRid) {
		this.sinstRid = sinstRid;
	}

	public Long getDinstRid(){
		return dinstRid;
	}
	public void setDinstRid(Long dinstRid) {
		this.dinstRid = dinstRid;
	}

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
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

	public String getDirection(){
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getSrcMulti(){
		return srcMulti;
	}
	public void setSrcMulti(String srcMulti) {
		this.srcMulti = srcMulti;
	}

	public String getSrcRole(){
		return srcRole;
	}
	public void setSrcRole(String srcRole) {
		this.srcRole = srcRole;
	}

	public String getSrcRoleType(){
		return srcRoleType;
	}
	public void setSrcRoleType(String srcRoleType) {
		this.srcRoleType = srcRoleType;
	}

	public String getDestMulti(){
		return destMulti;
	}
	public void setDestMulti(String destMulti) {
		this.destMulti = destMulti;
	}

	public String getDestRole(){
		return destRole;
	}
	public void setDestRole(String destRole) {
		this.destRole = destRole;
	}

	public String getDestRoleType(){
		return destRoleType;
	}
	public void setDestRoleType(String destRoleType) {
		this.destRoleType = destRoleType;
	}

	public String getAttrRelation(){
		return attrRelation;
	}
	public void setAttrRelation(String attrRelation) {
		this.attrRelation = attrRelation;
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

