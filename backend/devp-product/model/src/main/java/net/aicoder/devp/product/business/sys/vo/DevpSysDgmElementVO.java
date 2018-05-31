package net.aicoder.devp.product.business.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* UML图包含元素的值对象
*/
@ApiModel(value = "展现UML图包含元素的值对象")
public class DevpSysDgmElementVO {

    @ApiModelProperty(value = "记录id")
    private Long id;

    @ApiModelProperty(value = "租户编号")
    /**
    * 租户编号
    * [租户编号]
    */
    private Long tid;

    @ApiModelProperty(value = "系统元素名称")
    /**
    * 系统元素名称
    * [系统元素名称]
    */
    private String name;

    @ApiModelProperty(value = "系统元素代码")
    /**
    * 系统元素代码
    * [系统元素代码]
    */
    private String code;

    @ApiModelProperty(value = "系统元素别名")
    /**
    * 系统元素别名
    * [系统元素别名]
    */
    private String alias;

    @ApiModelProperty(value = "系统元素描述")
    /**
    * 系统元素描述
    * [系统元素描述]
    */
    private String description;

    @ApiModelProperty(value = "产品编号")
    /**
    * 产品编号
    * [产品编号]
    */
    private Long prdRid;

    @ApiModelProperty(value = "UML图编号")
    /**
    * UML图编号
    * [UML图编号]
    */
    private Long dgmRid;

    @ApiModelProperty(value = "系统元素编号")
    /**
    * 系统元素编号
    * [系统元素编号]
    */
    private Long elmRid;

    @ApiModelProperty(value = "系统元素实例编号")
    /**
    * 系统元素实例编号
    * [系统元素实例编号]
    */
    private Long elmInstRid;

    @ApiModelProperty(value = "顺序号")
    /**
    * 顺序号
    * [顺序号]
    */
    private Integer seq;

    @ApiModelProperty(value = "类型")
    /**
    * 类型
    * [类型]
    */
    private String type;

    @ApiModelProperty(value = "子类型")
    /**
    * 子类型
    * [子类型]
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
    * [范围]
    */
    private String scope;

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
    public Long getPrdRid(){
        return prdRid;
    }
    public void setPrdRid(Long prdRid) {
        this.prdRid = prdRid;
    }
    public Long getDgmRid(){
        return dgmRid;
    }
    public void setDgmRid(Long dgmRid) {
        this.dgmRid = dgmRid;
    }
    public Long getElmRid(){
        return elmRid;
    }
    public void setElmRid(Long elmRid) {
        this.elmRid = elmRid;
    }
    public Long getElmInstRid(){
        return elmInstRid;
    }
    public void setElmInstRid(Long elmInstRid) {
        this.elmInstRid = elmInstRid;
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