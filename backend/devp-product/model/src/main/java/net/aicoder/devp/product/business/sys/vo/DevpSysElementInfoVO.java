package net.aicoder.devp.product.business.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 系统元素扩充信息的值对象
*/
@ApiModel(value = "展现系统元素扩充信息的值对象")
public class DevpSysElementInfoVO {

    @ApiModelProperty(value = "记录id")
   private Long id;

    /**
    * 租户编号
    * [租户编号]
    */
    @ApiModelProperty(value = "租户编号")
    private Long tid;

    /**
    * 元素类型
    * [元素类型]
    */
    @ApiModelProperty(value = "元素类型")
    private String etype;

    /**
    * 扩展信息代码
    * [扩展信息代码]
    */
    @ApiModelProperty(value = "扩展信息代码")
    private String code;

    /**
    * 扩展信息名称
    * [扩展信息名称]-显示名称
    */
    @ApiModelProperty(value = "扩展信息名称")
    private String name;

    /**
    * 扩展信息别名
    * [扩展信息别名]
    */
    @ApiModelProperty(value = "扩展信息别名")
    private String alias;

    /**
    * 扩展信息描述
    * [扩展信息描述]
    */
    @ApiModelProperty(value = "扩展信息描述")
    private String description;

    /**
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    @ApiModelProperty(value = "记录状态")
    private Integer recordState;

    /**
    * 元素编号
    * [元素编号]
    */
    @ApiModelProperty(value = "元素编号")
    private Long objRid;

    /**
    * 数据类型
    * [数据类型]
    */
    @ApiModelProperty(value = "数据类型")
    private String dataType;

    /**
    * 扩展信息值
    * [扩展信息值]
    */
    @ApiModelProperty(value = "扩展信息值")
    private String infoValue;

    /**
    * 备注
    * [备注]
    */
    @ApiModelProperty(value = "备注")
    private String notes;


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
    public Long getObjRid(){
        return objRid;
    }
    public void setObjRid(Long objRid) {
        this.objRid = objRid;
    }
    public String getDataType(){
        return dataType;
    }
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    public String getInfoValue(){
        return infoValue;
    }
    public void setInfoValue(String infoValue) {
        this.infoValue = infoValue;
    }
    public String getNotes(){
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
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