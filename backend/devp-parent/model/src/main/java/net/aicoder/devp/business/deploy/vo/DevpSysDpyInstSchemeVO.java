package net.aicoder.devp.business.deploy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 资源实例所属的部署方案的值对象
*/
@ApiModel(value = "展现资源实例所属的部署方案的值对象")
public class DevpSysDpyInstSchemeVO {

    @ApiModelProperty(value = "记录id")
   private Long id;

    /**
    * 元素类型
    * [元素类型]-SYS_DPY_SCHEME// 部署方案
    */
    @ApiModelProperty(value = "元素类型")
    private String etype;

    /**
    * 系统元素名称
    * [系统元素名称]
    */
    @ApiModelProperty(value = "系统元素名称")
    private String name;

    /**
    * 系统元素代码
    * [系统元素代码]
    */
    @ApiModelProperty(value = "系统元素代码")
    private String code;

    /**
    * 系统元素别名
    * [系统元素别名]
    */
    @ApiModelProperty(value = "系统元素别名")
    private String alias;

    /**
    * 系统元素描述
    * [系统元素描述]
    */
    @ApiModelProperty(value = "系统元素描述")
    private String description;

    /**
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    @ApiModelProperty(value = "记录状态")
    private Integer recordState;

    /**
    * 类型
    * [类型]-开发,测试,验证,生产
    */
    @ApiModelProperty(value = "类型")
    private String type;

    /**
    * 子类型
    * [子类型]
    */
    @ApiModelProperty(value = "子类型")
    private String subType;

    /**
    * 构造型
    * [构造型]
    */
    @ApiModelProperty(value = "构造型")
    private String stereotype;

    /**
    * 产品编号
    * [产品编号]
    */
    @ApiModelProperty(value = "产品编号")
    private Long prdRid;

    /**
    * 关联资源编号
    * [关联资源编号]
    */
    @ApiModelProperty(value = "关联资源编号")
    private Long resRid;

    /**
    * 关联资源实例编号
    * [关联资源实例编号]
    */
    @ApiModelProperty(value = "关联资源实例编号")
    private Long instRid;

    /**
    * 部署方案编号
    * [部署方案编号]
    */
    @ApiModelProperty(value = "部署方案编号")
    private Long schemeRid;

    /**
    * 顺序号
    * [顺序号]
    */
    @ApiModelProperty(value = "顺序号")
    private Integer seq;


    public String getEtype(){
        return etype;
    }
    public void setEtype(String etype) {
        this.etype = etype;
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
    public Integer getRecordState(){
        return recordState;
    }
    public void setRecordState(Integer recordState) {
        this.recordState = recordState;
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
    public Long getPrdRid(){
        return prdRid;
    }
    public void setPrdRid(Long prdRid) {
        this.prdRid = prdRid;
    }
    public Long getResRid(){
        return resRid;
    }
    public void setResRid(Long resRid) {
        this.resRid = resRid;
    }
    public Long getInstRid(){
        return instRid;
    }
    public void setInstRid(Long instRid) {
        this.instRid = instRid;
    }
    public Long getSchemeRid(){
        return schemeRid;
    }
    public void setSchemeRid(Long schemeRid) {
        this.schemeRid = schemeRid;
    }
    public Integer getSeq(){
        return seq;
    }
    public void setSeq(Integer seq) {
        this.seq = seq;
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