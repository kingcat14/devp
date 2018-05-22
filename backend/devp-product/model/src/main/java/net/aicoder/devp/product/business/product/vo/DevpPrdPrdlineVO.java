package net.aicoder.devp.product.business.product.vo;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 产品线定义的值对象
*/
public class DevpPrdPrdlineVO {


    private Long id;


    /**
    * 租户编号
    * 
    */
    private Long tid;


    /**
    * 产品线代码
    * 
    */
    private String code;


    /**
    * 产品线名称
    * 
    */
    private String name;


    /**
    * 产品线别名
    * 
    */
    private String alias;


    /**
    * 产品线描述
    * 
    */
    private String description;


    /**
    * 产品线类型
    * 
    */
    private String type;


    /**
    * 领域
    * 
    */
    private String domain;


    /**
    * 构造型
    * 
    */
    private String stereotype;


    /**
    * 访问控制范围
    * 
    */
    private String scope;


    /**
    * 版本
    * 
    */
    private String version;


    /**
    * 阶段
    * 
    */
    private String phase;


    /**
    * 状态
    * 
    */
    private String status;


    /**
    * 父产品线编号
    * 
    */
    private Long parentRid;


    /**
    * 顺序号
    * 
    */
    private Integer seq;


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
    public String getType(){
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDomain(){
        return domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
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
    public String getVersion(){
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getPhase(){
        return phase;
    }
    public void setPhase(String phase) {
        this.phase = phase;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Long getParentRid(){
        return parentRid;
    }
    public void setParentRid(Long parentRid) {
        this.parentRid = parentRid;
    }
    public Integer getSeq(){
        return seq;
    }
    public void setSeq(Integer seq) {
        this.seq = seq;
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