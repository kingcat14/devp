package net.aicoder.devp.product.business.product.vo;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 产品干系人的值对象
*/
public class DevpPrdPersonVO {


    private Long id;


    /**
    * 租户编号
    * 
    */
    private Long tid;


    /**
    * 用户代码
    * 
    */
    private String code;


    /**
    * 用户名称
    * 
    */
    private String name;


    /**
    * 用户别名
    * 
    */
    private String alias;


    /**
    * 用户描述
    * 
    */
    private String description;


    /**
    * 关联元素类型
    * 
    */
    private String nexusType;


    /**
    * 关联元素编号
    * 
    */
    private Long nexusRid;


    /**
    * 顺序号
    * 
    */
    private Integer seq;


    /**
    * 用户编号
    * 
    */
    private Long uid;


    /**
    * 用户类型
    * 
    */
    private String type;


    /**
    * 用户类型
    * 
    */
    private String role;


    /**
    * 状态
    * 
    */
    private String status;


    /**
    * 用户租户编号
    * 
    */
    private Long userTid;


    /**
    * 组织编号
    * 
    */
    private Long orgRid;


    /**
    * 组织名称
    * 
    */
    private String orgName;


    /**
    * 记录状态
    * 
    */
    private Integer recordState;


    /**
    * 创建用户代码
    * 
    */
    private String createUcode;


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