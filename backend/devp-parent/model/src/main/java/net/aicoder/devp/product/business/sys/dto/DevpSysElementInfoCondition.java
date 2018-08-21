package net.aicoder.devp.product.business.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


@ApiModel(value = "查询系统元素扩充信息使用的DTO")
public class DevpSysElementInfoCondition implements Serializable{

	@ApiModelProperty(value = "租户编号")
	private Long tid;
	@ApiModelProperty(value = "租户编号最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户编号最小值")
	private Long tidMin;
	@ApiModelProperty(value = "元素类型")
	private String etype;
	@ApiModelProperty(value = "扩展信息代码")
	private String code;
	@ApiModelProperty(value = "扩展信息名称")
	private String name;
	@ApiModelProperty(value = "扩展信息别名")
	private String alias;
	@ApiModelProperty(value = "扩展信息描述")
	private String description;
	@ApiModelProperty(value = "记录状态")
	private Integer recordState;
	@ApiModelProperty(value = "记录状态最大值")
	private Integer recordStateMax;
	@ApiModelProperty(value = "记录状态最小值")
	private Integer recordStateMin;
	@ApiModelProperty(value = "元素编号")
	private Long objRid;
	@ApiModelProperty(value = "元素编号最大值")
	private Long objRidMax;
	@ApiModelProperty(value = "元素编号最小值")
	private Long objRidMin;
	@ApiModelProperty(value = "数据类型")
	private String dataType;
	@ApiModelProperty(value = "扩展信息值")
	private String infoValue;
	@ApiModelProperty(value = "备注")
	private String notes;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getTidMin(){
		return tidMin;
	}
	public void setTidMin(Long tidMin) {
		this.tidMin = tidMin;
	}

	public Long getTidMax(){
		return tidMax;
	}
	public void setTidMax(Long tidMax) {
		this.tidMax = tidMax;
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

	public Integer getRecordStateMin(){
		return recordStateMin;
	}
	public void setRecordStateMin(Integer recordStateMin) {
		this.recordStateMin = recordStateMin;
	}

	public Integer getRecordStateMax(){
		return recordStateMax;
	}
	public void setRecordStateMax(Integer recordStateMax) {
		this.recordStateMax = recordStateMax;
	}


	public Long getObjRid(){
		return objRid;
	}
	public void setObjRid(Long objRid) {
		this.objRid = objRid;
	}

	public Long getObjRidMin(){
		return objRidMin;
	}
	public void setObjRidMin(Long objRidMin) {
		this.objRidMin = objRidMin;
	}

	public Long getObjRidMax(){
		return objRidMax;
	}
	public void setObjRidMax(Long objRidMax) {
		this.objRidMax = objRidMax;
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




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
