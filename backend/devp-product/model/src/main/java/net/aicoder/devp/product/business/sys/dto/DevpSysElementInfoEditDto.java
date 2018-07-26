package net.aicoder.devp.product.business.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 系统元素扩充信息
 * @author icode
 */
@ApiModel(value = "修改系统元素扩充信息使用的DTO")
public class DevpSysElementInfoEditDto {


    /**
	 * 租户编号
	 * [租户编号]
     */
	@NotNull(message = "租户编号不能为空")
	@ApiModelProperty(value = "租户编号", required = true)
	private Long tid;


    /**
	 * 元素类型
	 * [元素类型]
     */
	@NotNull(message = "元素类型不能为空")
	@ApiModelProperty(value = "元素类型", required = true)
	@Size(max = 255, message = "元素类型超长，最多255个字符")
	private String etype;


    /**
	 * 扩展信息代码
	 * [扩展信息代码]
     */
	@NotNull(message = "扩展信息代码不能为空")
	@ApiModelProperty(value = "扩展信息代码", required = true)
	@Size(max = 255, message = "扩展信息代码超长，最多255个字符")
	private String code;


    /**
	 * 扩展信息名称
	 * [扩展信息名称]-显示名称
     */
	@ApiModelProperty(value = "扩展信息名称", required = false)
	@Size(max = 255, message = "扩展信息名称超长，最多255个字符")
	private String name;


    /**
	 * 扩展信息别名
	 * [扩展信息别名]
     */
	@ApiModelProperty(value = "扩展信息别名", required = false)
	@Size(max = 255, message = "扩展信息别名超长，最多255个字符")
	private String alias;


    /**
	 * 扩展信息描述
	 * [扩展信息描述]
     */
	@ApiModelProperty(value = "扩展信息描述", required = false)
	@Size(max = 255, message = "扩展信息描述超长，最多255个字符")
	private String description;


    /**
	 * 记录状态
	 * [记录状态]-0-失效;1-生效;缺省为1
     */
	@ApiModelProperty(value = "记录状态", required = false)
	private Integer recordState;


    /**
	 * 元素编号
	 * [元素编号]
     */
	@NotNull(message = "元素编号不能为空")
	@ApiModelProperty(value = "元素编号", required = true)
	private Long objRid;


    /**
	 * 数据类型
	 * [数据类型]
     */
	@ApiModelProperty(value = "数据类型", required = false)
	@Size(max = 255, message = "数据类型超长，最多255个字符")
	private String dataType;


    /**
	 * 扩展信息值
	 * [扩展信息值]
     */
	@ApiModelProperty(value = "扩展信息值", required = false)
	@Size(max = 255, message = "扩展信息值超长，最多255个字符")
	private String infoValue;


    /**
	 * 备注
	 * [备注]
     */
	@ApiModelProperty(value = "备注", required = false)
	@Size(max = 255, message = "备注超长，最多255个字符")
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
