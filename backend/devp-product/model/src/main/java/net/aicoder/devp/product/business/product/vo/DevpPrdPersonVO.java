package net.aicoder.devp.product.business.product.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 产品干系人的值对象
*/
@ApiModel(value = "展现产品干系人的值对象")
public class DevpPrdPersonVO {


    private Long id;

    @ApiModelProperty(value = "租户编号")
    /**
    * 租户编号
    * 
    */
    private Long tid;

    @ApiModelProperty(value = "用户代码")
    /**
    * 用户代码
    * 
    */
    private String code;

    @ApiModelProperty(value = "用户名称")
    /**
    * 用户名称
    * 
    */
    private String name;

    @ApiModelProperty(value = "用户别名")
    /**
    * 用户别名
    * 
    */
    private String alias;

    @ApiModelProperty(value = "用户描述")
    /**
    * 用户描述
    * 
    */
    private String description;

    @ApiModelProperty(value = "关联元素类型")
    /**
    * 关联元素类型
    * 
    */
    private String nexusType;

    @ApiModelProperty(value = "关联元素编号")
    /**
    * 关联元素编号
    * 
    */
    private Long nexusRid;

    @ApiModelProperty(value = "顺序号")
    /**
    * 顺序号
    * 
    */
    private Integer seq;

    @ApiModelProperty(value = "用户编号")
    /**
    * 用户编号
    * 
    */
    private Long uid;

    @ApiModelProperty(value = "用户类型")
    /**
    * 用户类型
    * 
    */
    private String type;

    @ApiModelProperty(value = "用户类型")
    /**
    * 用户类型
    * 
    */
    private String role;

    @ApiModelProperty(value = "状态")
    /**
    * 状态
    * 
    */
    private String status;

    @ApiModelProperty(value = "用户租户编号")
    /**
    * 用户租户编号
    * 
    */
    private Long userTid;

    @ApiModelProperty(value = "组织编号")
    /**
    * 组织编号
    * 
    */
    private Long orgRid;

    @ApiModelProperty(value = "组织名称")
    /**
    * 组织名称
    * 
    */
    private String orgName;

    @ApiModelProperty(value = "记录状态")
    /**
    * 记录状态
    * 
    */
    private Integer recordState;

    @ApiModelProperty(value = "创建用户代码")
    /**
    * 创建用户代码
    * 
    */
    private String createUcode;

    @ApiModelProperty(value = "修改用户代码")
    /**
    * 修改用户代码
    * 
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