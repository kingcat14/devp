package net.aicoder.devp.deploy.business.deploy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 系统元素间关系定义的值对象
*/
@ApiModel(value = "展现系统元素间关系定义的值对象")
public class DevpSysDpyCmpRefVO {

    @ApiModelProperty(value = "记录id")
   private Long id;

    /**
    * 元素类型
    * [元素类型]-SYS_DPY_CMP_REF // 部署组件关联元素
    */
    @ApiModelProperty(value = "元素类型")
    private String etype;

    /**
    * 对应关系代码
    * [对应关系代码]
    */
    @ApiModelProperty(value = "对应关系代码")
    private String code;

    /**
    * 对应关系名称
    * [对应关系名称]
    */
    @ApiModelProperty(value = "对应关系名称")
    private String name;

    /**
    * 对应关系别名
    * [对应关系别名]
    */
    @ApiModelProperty(value = "对应关系别名")
    private String alias;

    /**
    * 对应关系描述
    * [对应关系描述]
    */
    @ApiModelProperty(value = "对应关系描述")
    private String description;

    /**
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    @ApiModelProperty(value = "记录状态")
    private Integer recordState;

    /**
    * 产品编号
    * [产品编号]
    */
    @ApiModelProperty(value = "产品编号")
    private Long prdRid;

    /**
    * 部署方案编号
    * [部署方案编号]
    */
    @ApiModelProperty(value = "部署方案编号")
    private Long schemeRid;

    /**
    * 组件编号
    * [组件编号]
    */
    @ApiModelProperty(value = "组件编号")
    private Long cmpRid;

    /**
    * 关联元素类型
    * [关联元素类型]
    */
    @ApiModelProperty(value = "关联元素类型")
    private Long refEtype;

    /**
    * 关联产品编号
    * [关联产品编号]
    */
    @ApiModelProperty(value = "关联产品编号")
    private Long refPrdRid;

    /**
    * 关联元素编号
    * [关联元素编号]
    */
    @ApiModelProperty(value = "关联元素编号")
    private Long refElmRid;

    /**
    * 关联元素实例编号
    * [关联元素实例编号]-缺省值为0
    */
    @ApiModelProperty(value = "关联元素实例编号")
    private Long refInstRid;

    /**
    * 顺序号
    * [顺序号]
    */
    @ApiModelProperty(value = "顺序号")
    private Integer seq;

    /**
    * 类型
    * [类型]-关联类型：部署到、连接、调用
    */
    @ApiModelProperty(value = "类型")
    private String type;

    /**
    * 子类型
    * [子类型]-连接时：双向[-o)-]，请求[)-],提供[-o]
    */
    @ApiModelProperty(value = "子类型")
    private String subType;

    /**
    * 构造型
    * [构造型]-(保留)
    */
    @ApiModelProperty(value = "构造型")
    private String stereotype;

    /**
    * 范围
    * [范围]-(保留)
    */
    @ApiModelProperty(value = "范围")
    private String scope;

    /**
    * 方向
    * [方向]-(保留)
    */
    @ApiModelProperty(value = "方向")
    private String direction;

    /**
    * 来源对应数量
    * [来源对应数量]-0/1/ * /0..1/0..* /1..*
    */
    @ApiModelProperty(value = "来源对应数量")
    private String srcMulti;

    /**
    * 来源角色
    * [来源角色]
    */
    @ApiModelProperty(value = "来源角色")
    private String srcRole;

    /**
    * 来源角色类型
    * [来源角色类型]
    */
    @ApiModelProperty(value = "来源角色类型")
    private String srcRoleType;

    /**
    * 目标对应数量
    * [目标对应数量]-0/1/ * /0..1/0..* /1..*
    */
    @ApiModelProperty(value = "目标对应数量")
    private String destMulti;

    /**
    * 目标角色
    * [目标角色]
    */
    @ApiModelProperty(value = "目标角色")
    private String destRole;

    /**
    * 目标角色类型
    * [目标角色类型]
    */
    @ApiModelProperty(value = "目标角色类型")
    private String destRoleType;

    /**
    * 属性对应关系
    * [属性对应关系]-(保留)
    */
    @ApiModelProperty(value = "属性对应关系")
    private String attrRelation;


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