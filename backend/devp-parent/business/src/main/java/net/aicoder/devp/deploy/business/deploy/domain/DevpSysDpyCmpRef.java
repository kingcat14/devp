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
 * 系统元素间关系
 * @author icode
 */
@Entity
@Table(appliesTo = "devp_sys_dpy_cmp_ref", comment = "[系统元素间关系]")
//@DynamicUpdate
//@DynamicInsert
public class DevpSysDpyCmpRef extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ETYPE = "etype";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_RECORD_STATE = "recordState";
	public static final String PROPERTY_PRD_RID = "prdRid";
	public static final String PROPERTY_SCHEME_RID = "schemeRid";
	public static final String PROPERTY_CMP_RID = "cmpRid";
	public static final String PROPERTY_REF_ETYPE = "refEtype";
	public static final String PROPERTY_REF_PRD_RID = "refPrdRid";
	public static final String PROPERTY_REF_ELM_RID = "refElmRid";
	public static final String PROPERTY_REF_INST_RID = "refInstRid";
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
    * [元素类型]-SYS_DPY_CMP_REF // 部署组件关联元素
    */
    @Column(name = "etype")
	@NotNull(message = "元素类型不能为空")
	@Size(max = 255, message = "元素类型超长，最多255个字符")
	private String etype;

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
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    @Column(name = "record_state")
	private Integer recordState;

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
    * 组件编号
    * [组件编号]
    */
    @Column(name = "cmp_rid")
	@NotNull(message = "组件编号不能为空")
	private Long cmpRid;

    /**
    * 关联元素类型
    * [关联元素类型]
    */
    @Column(name = "ref_etype")
	@NotNull(message = "关联元素类型不能为空")
	private Long refEtype;

    /**
    * 关联产品编号
    * [关联产品编号]
    */
    @Column(name = "ref_prd_rid")
	@NotNull(message = "关联产品编号不能为空")
	private Long refPrdRid;

    /**
    * 关联元素编号
    * [关联元素编号]
    */
    @Column(name = "ref_elm_rid")
	@NotNull(message = "关联元素编号不能为空")
	private Long refElmRid;

    /**
    * 关联元素实例编号
    * [关联元素实例编号]-缺省值为0
    */
    @Column(name = "ref_inst_rid")
	@NotNull(message = "关联元素实例编号不能为空")
	private Long refInstRid;

    /**
    * 顺序号
    * [顺序号]
    */
    @Column(name = "seq")
	private Integer seq;

    /**
    * 类型
    * [类型]-关联类型：部署到、连接、调用
    */
    @Column(name = "type")
	@Size(max = 255, message = "类型超长，最多255个字符")
	private String type;

    /**
    * 子类型
    * [子类型]-连接时：双向[-o)-]，请求[)-],提供[-o]
    */
    @Column(name = "sub_type")
	@Size(max = 255, message = "子类型超长，最多255个字符")
	private String subType;

    /**
    * 构造型
    * [构造型]-(保留)
    */
    @Column(name = "stereotype")
	@Size(max = 255, message = "构造型超长，最多255个字符")
	private String stereotype;

    /**
    * 范围
    * [范围]-(保留)
    */
    @Column(name = "scope")
	@Size(max = 255, message = "范围超长，最多255个字符")
	private String scope;

    /**
    * 方向
    * [方向]-(保留)
    */
    @Column(name = "direction")
	@Size(max = 255, message = "方向超长，最多255个字符")
	private String direction;

    /**
    * 来源对应数量
    * [来源对应数量]-0/1/ * /0..1/0..* /1..*
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
    * [目标对应数量]-0/1/ * /0..1/0..* /1..*
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
    * [属性对应关系]-(保留)
    */
    @Column(name = "attr_relation")
	@Size(max = 255, message = "属性对应关系超长，最多255个字符")
	private String attrRelation;

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

	public Integer getRecordState(){
		return recordState;
	}
	public void setRecordState(Integer recordState) {
		this.recordState = recordState;
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

	public Long getCmpRid(){
		return cmpRid;
	}
	public void setCmpRid(Long cmpRid) {
		this.cmpRid = cmpRid;
	}

	public Long getRefEtype(){
		return refEtype;
	}
	public void setRefEtype(Long refEtype) {
		this.refEtype = refEtype;
	}

	public Long getRefPrdRid(){
		return refPrdRid;
	}
	public void setRefPrdRid(Long refPrdRid) {
		this.refPrdRid = refPrdRid;
	}

	public Long getRefElmRid(){
		return refElmRid;
	}
	public void setRefElmRid(Long refElmRid) {
		this.refElmRid = refElmRid;
	}

	public Long getRefInstRid(){
		return refInstRid;
	}
	public void setRefInstRid(Long refInstRid) {
		this.refInstRid = refInstRid;
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

