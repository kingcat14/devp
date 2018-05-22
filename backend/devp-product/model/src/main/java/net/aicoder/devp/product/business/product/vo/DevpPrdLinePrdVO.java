package net.aicoder.devp.product.business.product.vo;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 产品所属产品线定义的值对象
*/
public class DevpPrdLinePrdVO {


    private Long id;


    /**
    * 租户编号
    * 
    */
    private Long tid;


    /**
    * 代码
    * 
    */
    private String code;


    /**
    * 名称
    * 
    */
    private String name;


    /**
    * 别名
    * 
    */
    private String alias;


    /**
    * 描述
    * 
    */
    private String description;


    /**
    * 产品线编号
    * 
    */
    private Long lineRid;


    /**
    * 产品编号
    * 
    */
    private Long prdRid;


    /**
    * 顺序号
    * 
    */
    private Integer seq;


    /**
    * 类型
    * 
    */
    private String type;


    /**
    * 构造型
    * 
    */
    private String stereotype;


    /**
    * 范围
    * 
    */
    private String scope;


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
    private String cmodifyUcode;


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
    public Long getLineRid(){
        return lineRid;
    }
    public void setLineRid(Long lineRid) {
        this.lineRid = lineRid;
    }
    public Long getPrdRid(){
        return prdRid;
    }
    public void setPrdRid(Long prdRid) {
        this.prdRid = prdRid;
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
    public String getCmodifyUcode(){
        return cmodifyUcode;
    }
    public void setCmodifyUcode(String cmodifyUcode) {
        this.cmodifyUcode = cmodifyUcode;
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