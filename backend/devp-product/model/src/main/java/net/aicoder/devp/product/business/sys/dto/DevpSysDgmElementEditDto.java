package net.aicoder.devp.product.business.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
 * UML图包含元素
 * @author icode
 */
@ApiModel(value = "修改UML图包含元素使用的DTO")
public class DevpSysDgmElementEditDto {

    /**
	 * 租户编号
	 * [租户编号]
     */
	@NotNull(message = "租户编号不能为空")
	@ApiModelProperty(value = "租户编号", required = true)
	private Long tid;

    /**
	 * 系统元素名称
	 * [系统元素名称]
     */
	@NotNull(message = "系统元素名称不能为空")
	@ApiModelProperty(value = "系统元素名称", required = true)
	@Size(max = 255, message = "系统元素名称超长，最多255个字符")
	private String name;

    /**
	 * 系统元素代码
	 * [系统元素代码]
     */
	@ApiModelProperty(value = "系统元素代码", required = false)
	@Size(max = 255, message = "系统元素代码超长，最多255个字符")
	private String code;

    /**
	 * 系统元素别名
	 * [系统元素别名]
     */
	@ApiModelProperty(value = "系统元素别名", required = false)
	@Size(max = 255, message = "系统元素别名超长，最多255个字符")
	private String alias;

    /**
	 * 系统元素描述
	 * [系统元素描述]
     */
	@ApiModelProperty(value = "系统元素描述", required = false)
	@Size(max = 255, message = "系统元素描述超长，最多255个字符")
	private String description;

    /**
	 * 产品编号
	 * [产品编号]
     */
	@NotNull(message = "产品编号不能为空")
	@ApiModelProperty(value = "产品编号", required = true)
	private Long prdRid;

    /**
	 * UML图编号
	 * [UML图编号]
     */
	@NotNull(message = "UML图编号不能为空")
	@ApiModelProperty(value = "UML图编号", required = true)
	private Long dgmRid;

    /**
	 * 系统元素编号
	 * [系统元素编号]
     */
	@NotNull(message = "系统元素编号不能为空")
	@ApiModelProperty(value = "系统元素编号", required = true)
	private Long elmRid;

    /**
	 * 系统元素实例编号
	 * [系统元素实例编号]
     */
	@ApiModelProperty(value = "系统元素实例编号", required = false)
	private Long elmInstRid;

    /**
	 * 顺序号
	 * [顺序号]
     */
	@ApiModelProperty(value = "顺序号", required = false)
	private Integer seq;

    /**
	 * 类型
	 * [类型]
     */
	@ApiModelProperty(value = "类型", required = false)
	@Size(max = 255, message = "类型超长，最多255个字符")
	private String type;

    /**
	 * 子类型
	 * [子类型]
     */
	@ApiModelProperty(value = "子类型", required = false)
	@Size(max = 255, message = "子类型超长，最多255个字符")
	private String subType;

    /**
	 * 构造型
	 * [构造型]
     */
	@ApiModelProperty(value = "构造型", required = false)
	@Size(max = 255, message = "构造型超长，最多255个字符")
	private String stereotype;

    /**
	 * 范围
	 * [范围]
     */
	@ApiModelProperty(value = "范围", required = false)
	@Size(max = 255, message = "范围超长，最多255个字符")
	private String scope;

    /**
	 * 记录状态
	 * [记录状态]-0-失效;1-生效;缺省为1
     */
	@ApiModelProperty(value = "记录状态", required = false)
	private Integer recordState;

    /**
	 * 创建用户代码
	 * [创建用户代码]
     */
	@ApiModelProperty(value = "创建用户代码", required = false)
	@Size(max = 255, message = "创建用户代码超长，最多255个字符")
	private String createUcode;

    /**
	 * 修改用户代码
	 * [修改用户代码]
     */
	@ApiModelProperty(value = "修改用户代码", required = false)
	@Size(max = 255, message = "修改用户代码超长，最多255个字符")
	private String modifyUcode;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
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

	public Long getPrdRid(){
		return prdRid;
	}
	public void setPrdRid(Long prdRid) {
		this.prdRid = prdRid;
	}

	public Long getDgmRid(){
		return dgmRid;
	}
	public void setDgmRid(Long dgmRid) {
		this.dgmRid = dgmRid;
	}

	public Long getElmRid(){
		return elmRid;
	}
	public void setElmRid(Long elmRid) {
		this.elmRid = elmRid;
	}

	public Long getElmInstRid(){
		return elmInstRid;
	}
	public void setElmInstRid(Long elmInstRid) {
		this.elmInstRid = elmInstRid;
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

	public String getModifyUcode(){
		return modifyUcode;
	}
	public void setModifyUcode(String modifyUcode) {
		this.modifyUcode = modifyUcode;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
