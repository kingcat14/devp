package net.aicoder.devp.product.business.product.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 产品包含的外部组件的值对象
*/
@ApiModel(value = "展现产品包含的外部组件的值对象")
public class DevpSysExtcmpVO {


    private Long id;

    @ApiModelProperty(value = "租户编号")
    /**
    * 租户编号
    * 
    */
    private Long tid;

    @ApiModelProperty(value = "代码")
    /**
    * 代码
    * 
    */
    private String code;

    @ApiModelProperty(value = "名称")
    /**
    * 名称
    * 
    */
    private String name;

    @ApiModelProperty(value = "别名")
    /**
    * 别名
    * 
    */
    private String alias;

    @ApiModelProperty(value = "描述")
    /**
    * 描述
    * 
    */
    private String description;

    @ApiModelProperty(value = "产品编号")
    /**
    * 产品编号
    * 
    */
    private Long prdRid;

    @ApiModelProperty(value = "外部产品编号")
    /**
    * 外部产品编号
    * 
    */
    private Long extPrdRid;

    @ApiModelProperty(value = "外部系统元素编号")
    /**
    * 外部系统元素编号
    * 
    */
    private Long extElmRid;

    @ApiModelProperty(value = "顺序号")
    /**
    * 顺序号
    * 
    */
    private Integer seq;

    @ApiModelProperty(value = "类型")
    /**
    * 类型
    * 
    */
    private String type;

    @ApiModelProperty(value = "构造型")
    /**
    * 构造型
    * 
    */
    private String stereotype;

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
    public Long getPrdRid(){
        return prdRid;
    }
    public void setPrdRid(Long prdRid) {
        this.prdRid = prdRid;
    }
    public Long getExtPrdRid(){
        return extPrdRid;
    }
    public void setExtPrdRid(Long extPrdRid) {
        this.extPrdRid = extPrdRid;
    }
    public Long getExtElmRid(){
        return extElmRid;
    }
    public void setExtElmRid(Long extElmRid) {
        this.extElmRid = extElmRid;
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