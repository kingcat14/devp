package net.aicoder.devp.business.deploy.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 系统元素间关系
 * @author icode
 */
@ApiModel(value = "新增系统元素间关系使用的DTO")
public class DevpSysDpyCmpRefAddDto {

    /**租户编号*/
	@ApiModelProperty(value = "租户编号", required = false, notes = "[租户编号]")
	private Long tid;

    /**元素类型*/
	@ApiModelProperty(value = "元素类型", required = false, notes = "[元素类型]-SYS_DPY_CMP_REF // 部署组件关联元素")
	private String etype;

    /**对应关系代码*/
	@ApiModelProperty(value = "对应关系代码", required = false, notes = "[对应关系代码]")
	private String code;

    /**对应关系名称*/
	@ApiModelProperty(value = "对应关系名称", required = false, notes = "[对应关系名称]")
	private String name;

    /**对应关系别名*/
	@ApiModelProperty(value = "对应关系别名", required = false, notes = "[对应关系别名]")
	private String alias;

    /**对应关系描述*/
	@ApiModelProperty(value = "对应关系描述", required = false, notes = "[对应关系描述]")
	private String description;

    /**记录状态*/
	@ApiModelProperty(value = "记录状态", required = false, notes = "[记录状态]-0-失效;1-生效;缺省为1")
	private Integer recordState;

    /**产品编号*/
	@ApiModelProperty(value = "产品编号", required = false, notes = "[产品编号]")
	private Long prdRid;

    /**部署方案编号*/
	@ApiModelProperty(value = "部署方案编号", required = false, notes = "[部署方案编号]")
	private Long schemeRid;

    /**组件编号*/
	@ApiModelProperty(value = "组件编号", required = false, notes = "[组件编号]")
	private Long cmpRid;

    /**关联元素类型*/
	@ApiModelProperty(value = "关联元素类型", required = false, notes = "[关联元素类型]")
	private Long refEtype;

    /**关联产品编号*/
	@ApiModelProperty(value = "关联产品编号", required = false, notes = "[关联产品编号]")
	private Long refPrdRid;

    /**关联元素编号*/
	@ApiModelProperty(value = "关联元素编号", required = false, notes = "[关联元素编号]")
	private Long refElmRid;

    /**关联元素实例编号*/
	@ApiModelProperty(value = "关联元素实例编号", required = false, notes = "[关联元素实例编号]-缺省值为0")
	private Long refInstRid;

    /**顺序号*/
	@ApiModelProperty(value = "顺序号", required = false, notes = "[顺序号]")
	private Integer seq;

    /**类型*/
	@ApiModelProperty(value = "类型", required = false, notes = "[类型]-关联类型：部署到、连接、调用")
	private String type;

    /**子类型*/
	@ApiModelProperty(value = "子类型", required = false, notes = "[子类型]-连接时：双向[-o)-]，请求[)-],提供[-o]")
	private String subType;

    /**构造型*/
	@ApiModelProperty(value = "构造型", required = false, notes = "[构造型]-(保留)")
	private String stereotype;

    /**范围*/
	@ApiModelProperty(value = "范围", required = false, notes = "[范围]-(保留)")
	private String scope;

    /**方向*/
	@ApiModelProperty(value = "方向", required = false, notes = "[方向]-(保留)")
	private String direction;

    /**来源对应数量*/
	@ApiModelProperty(value = "来源对应数量", required = false, notes = "[来源对应数量]-0/1/ * /0..1/0..* /1..*")
	private String srcMulti;

    /**来源角色*/
	@ApiModelProperty(value = "来源角色", required = false, notes = "[来源角色]")
	private String srcRole;

    /**来源角色类型*/
	@ApiModelProperty(value = "来源角色类型", required = false, notes = "[来源角色类型]")
	private String srcRoleType;

    /**目标对应数量*/
	@ApiModelProperty(value = "目标对应数量", required = false, notes = "[目标对应数量]-0/1/ * /0..1/0..* /1..*")
	private String destMulti;

    /**目标角色*/
	@ApiModelProperty(value = "目标角色", required = false, notes = "[目标角色]")
	private String destRole;

    /**目标角色类型*/
	@ApiModelProperty(value = "目标角色类型", required = false, notes = "[目标角色类型]")
	private String destRoleType;

    /**属性对应关系*/
	@ApiModelProperty(value = "属性对应关系", required = false, notes = "[属性对应关系]-(保留)")
	private String attrRelation;

    /**创建用户代码*/
	@ApiModelProperty(value = "创建用户代码", required = false, notes = "[创建用户代码]")
	private String createUcode;

    /**创建用户姓名*/
	@ApiModelProperty(value = "创建用户姓名", required = false, notes = "[创建用户姓名]")
	private String createUname;

    /**修改用户代码*/
	@ApiModelProperty(value = "修改用户代码", required = false, notes = "[修改用户代码]")
	private String modifyUcode;

    /**修改用户姓名*/
	@ApiModelProperty(value = "修改用户姓名", required = false, notes = "[修改用户姓名]")
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
