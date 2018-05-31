package net.aicoder.devp.product.business.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 系统元素间关系的值对象
*/
@ApiModel(value = "展现系统元素间关系的值对象")
public class DevpSysElmConnectorVO {

    @ApiModelProperty(value = "记录id")
    private Long id;

    @ApiModelProperty(value = "租户编号")
    /**
    * 租户编号
    * [租户编号]
    */
    private Long tid;

    @ApiModelProperty(value = "对应关系代码")
    /**
    * 对应关系代码
    * [对应关系代码]
    */
    private String code;

    @ApiModelProperty(value = "对应关系名称")
    /**
    * 对应关系名称
    * [对应关系名称]
    */
    private String name;

    @ApiModelProperty(value = "对应关系别名")
    /**
    * 对应关系别名
    * [对应关系别名]
    */
    private String alias;

    @ApiModelProperty(value = "对应关系描述")
    /**
    * 对应关系描述
    * [对应关系描述]
    */
    private String description;

    @ApiModelProperty(value = "来源产品编号")
    /**
    * 来源产品编号
    * [来源产品编号]
    */
    private Long sprdRid;

    @ApiModelProperty(value = "来源系统元素编号")
    /**
    * 来源系统元素编号
    * [来源系统元素编号]
    */
    private Long selmRid;

    @ApiModelProperty(value = "目标产品编号")
    /**
    * 目标产品编号
    * [目标产品编号]
    */
    private Long dprdRid;

    @ApiModelProperty(value = "目标系统元素编号")
    /**
    * 目标系统元素编号
    * [目标系统元素编号]
    */
    private Long delmRid;

    @ApiModelProperty(value = "来源系统元素实例编号")
    /**
    * 来源系统元素实例编号
    * [来源系统元素实例编号]-缺省值为0
    */
    private Long sinstRid;

    @ApiModelProperty(value = "目标系统元素实例编号")
    /**
    * 目标系统元素实例编号
    * [目标系统元素实例编号]-缺省值为0
    */
    private Long dinstRid;

    @ApiModelProperty(value = "顺序号")
    /**
    * 顺序号
    * [顺序号]
    */
    private Integer seq;

    @ApiModelProperty(value = "对应关系类型")
    /**
    * 对应关系类型
    * [对应关系类型]-泛化(generalization)关系,关联(association)关系(关联,聚合,合成),依赖(dependency)关系，实现(realization)
    */
    private String type;

    @ApiModelProperty(value = "对应关系子类型")
    /**
    * 对应关系子类型
    * [对应关系子类型]
    */
    private String subType;

    @ApiModelProperty(value = "构造型")
    /**
    * 构造型
    * [构造型]
    */
    private String stereotype;

    @ApiModelProperty(value = "范围")
    /**
    * 范围
    * [范围]-可见范围
    */
    private String scope;

    @ApiModelProperty(value = "方向")
    /**
    * 方向
    * [方向]-->,<-,UnD,<->,
    */
    private String direction;

    @ApiModelProperty(value = "来源对应数量")
    /**
    * 来源对应数量
    * [来源对应数量]-0/1/ * /0..1/0..* /1..*
    */
    private String srcMulti;

    @ApiModelProperty(value = "来源角色")
    /**
    * 来源角色
    * [来源角色]
    */
    private String srcRole;

    @ApiModelProperty(value = "来源角色类型")
    /**
    * 来源角色类型
    * [来源角色类型]
    */
    private String srcRoleType;

    @ApiModelProperty(value = "目标对应数量")
    /**
    * 目标对应数量
    * [目标对应数量]-0/1/* /0..1/0..* /1..*
    */
    private String destMulti;

    @ApiModelProperty(value = "目标角色")
    /**
    * 目标角色
    * [目标角色]
    */
    private String destRole;

    @ApiModelProperty(value = "目标角色类型")
    /**
    * 目标角色类型
    * [目标角色类型]
    */
    private String destRoleType;

    @ApiModelProperty(value = "属性对应关系")
    /**
    * 属性对应关系
    * [属性对应关系]-描述对象间如何装配，如何转换
    */
    private String attrRelation;

    @ApiModelProperty(value = "记录状态")
    /**
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    private Integer recordState;

    @ApiModelProperty(value = "创建用户代码")
    /**
    * 创建用户代码
    * [创建用户代码]
    */
    private String createUcode;

    @ApiModelProperty(value = "修改用户代码")
    /**
    * 修改用户代码
    * [修改用户代码]
    */
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