package net.aicoder.devp.business.ops.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
 * 系统元素扩充信息
 * @author icode
 */
@ApiModel(value = "新增系统元素扩充信息使用的DTO")
public class DevpOpsElementInfoAddDto {

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
	 * [扩展信息描述]-对应当前属性值
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
	private Long elmRid;

    /**
	 * 元素实例编号
	 * [元素实例编号]-缺省值为0
     */
	@NotNull(message = "元素实例编号不能为空")
	@ApiModelProperty(value = "元素实例编号", required = true)
	private Long instRid;

    /**
	 * 顺序号
	 * [顺序号]
     */
	@ApiModelProperty(value = "顺序号", required = false)
	private Integer seq;

    /**
	 * 扩展信息代码1
	 * [扩展信息代码1]
     */
	@ApiModelProperty(value = "扩展信息代码1", required = false)
	@Size(max = 255, message = "扩展信息代码1超长，最多255个字符")
	private String infoCode1;

    /**
	 * 扩展信息值1
	 * [扩展信息值1]
     */
	@ApiModelProperty(value = "扩展信息值1", required = false)
	@Size(max = 255, message = "扩展信息值1超长，最多255个字符")
	private String infoValue1;

    /**
	 * 扩展信息代码2
	 * [扩展信息代码2]
     */
	@ApiModelProperty(value = "扩展信息代码2", required = false)
	@Size(max = 255, message = "扩展信息代码2超长，最多255个字符")
	private String infoCode2;

    /**
	 * 扩展信息值2
	 * [扩展信息值2]
     */
	@ApiModelProperty(value = "扩展信息值2", required = false)
	@Size(max = 255, message = "扩展信息值2超长，最多255个字符")
	private String infoValue2;

    /**
	 * 扩展信息代码3
	 * [扩展信息代码3]
     */
	@ApiModelProperty(value = "扩展信息代码3", required = false)
	@Size(max = 255, message = "扩展信息代码3超长，最多255个字符")
	private String infoCode3;

    /**
	 * 扩展信息值3
	 * [扩展信息值3]
     */
	@ApiModelProperty(value = "扩展信息值3", required = false)
	@Size(max = 255, message = "扩展信息值3超长，最多255个字符")
	private String infoValue3;

    /**
	 * 扩展信息代码4
	 * [扩展信息代码4]
     */
	@ApiModelProperty(value = "扩展信息代码4", required = false)
	@Size(max = 255, message = "扩展信息代码4超长，最多255个字符")
	private String infoCode4;

    /**
	 * 扩展信息值4
	 * [扩展信息值4]
     */
	@ApiModelProperty(value = "扩展信息值4", required = false)
	@Size(max = 255, message = "扩展信息值4超长，最多255个字符")
	private String infoValue4;

    /**
	 * 扩展信息代码5
	 * [扩展信息代码5]
     */
	@ApiModelProperty(value = "扩展信息代码5", required = false)
	@Size(max = 255, message = "扩展信息代码5超长，最多255个字符")
	private String infoCode5;

    /**
	 * 扩展信息值5
	 * [扩展信息值5]
     */
	@ApiModelProperty(value = "扩展信息值5", required = false)
	@Size(max = 255, message = "扩展信息值5超长，最多255个字符")
	private String infoValue5;

    /**
	 * 备注
	 * [备注]
     */
	@ApiModelProperty(value = "备注", required = false)
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String notes;

    /**
	 * 参数定义标识
	 * [参数定义标识]-扩展参数定义的标识
     */
	@ApiModelProperty(value = "参数定义标识", required = false)
	@Size(max = 255, message = "参数定义标识超长，最多255个字符")
	private String parasCode;


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

	public Long getElmRid(){
		return elmRid;
	}
	public void setElmRid(Long elmRid) {
		this.elmRid = elmRid;
	}

	public Long getInstRid(){
		return instRid;
	}
	public void setInstRid(Long instRid) {
		this.instRid = instRid;
	}

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getInfoCode1(){
		return infoCode1;
	}
	public void setInfoCode1(String infoCode1) {
		this.infoCode1 = infoCode1;
	}

	public String getInfoValue1(){
		return infoValue1;
	}
	public void setInfoValue1(String infoValue1) {
		this.infoValue1 = infoValue1;
	}

	public String getInfoCode2(){
		return infoCode2;
	}
	public void setInfoCode2(String infoCode2) {
		this.infoCode2 = infoCode2;
	}

	public String getInfoValue2(){
		return infoValue2;
	}
	public void setInfoValue2(String infoValue2) {
		this.infoValue2 = infoValue2;
	}

	public String getInfoCode3(){
		return infoCode3;
	}
	public void setInfoCode3(String infoCode3) {
		this.infoCode3 = infoCode3;
	}

	public String getInfoValue3(){
		return infoValue3;
	}
	public void setInfoValue3(String infoValue3) {
		this.infoValue3 = infoValue3;
	}

	public String getInfoCode4(){
		return infoCode4;
	}
	public void setInfoCode4(String infoCode4) {
		this.infoCode4 = infoCode4;
	}

	public String getInfoValue4(){
		return infoValue4;
	}
	public void setInfoValue4(String infoValue4) {
		this.infoValue4 = infoValue4;
	}

	public String getInfoCode5(){
		return infoCode5;
	}
	public void setInfoCode5(String infoCode5) {
		this.infoCode5 = infoCode5;
	}

	public String getInfoValue5(){
		return infoValue5;
	}
	public void setInfoValue5(String infoValue5) {
		this.infoValue5 = infoValue5;
	}

	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getParasCode(){
		return parasCode;
	}
	public void setParasCode(String parasCode) {
		this.parasCode = parasCode;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
