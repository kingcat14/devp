package net.aicoder.devp.product.business.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
 * 产品包含的外部组件
 * @author icode
 */
@ApiModel(value = "修改产品包含的外部组件使用的DTO")
public class DevpSysExtcmpEditDto {

    /**
	 * 租户编号
	 * 
     */
	@NotNull(message = "租户编号不能为空")
	@ApiModelProperty(value = "租户编号", required = true)
	private Long tid;

    /**
	 * 代码
	 * 
     */
	@ApiModelProperty(value = "代码", required = false)
	@Size(max = 255, message = "代码超长，最多255个字符")
	private String code;

    /**
	 * 名称
	 * 
     */
	@ApiModelProperty(value = "名称", required = false)
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;

    /**
	 * 别名
	 * 
     */
	@ApiModelProperty(value = "别名", required = false)
	@Size(max = 255, message = "别名超长，最多255个字符")
	private String alias;

    /**
	 * 描述
	 * 
     */
	@ApiModelProperty(value = "描述", required = false)
	@Size(max = 255, message = "描述超长，最多255个字符")
	private String description;

    /**
	 * 产品编号
	 * 
     */
	@NotNull(message = "产品编号不能为空")
	@ApiModelProperty(value = "产品编号", required = true)
	private Long prdRid;

    /**
	 * 外部产品编号
	 * 
     */
	@NotNull(message = "外部产品编号不能为空")
	@ApiModelProperty(value = "外部产品编号", required = true)
	private Long extPrdRid;

    /**
	 * 外部系统元素编号
	 * 
     */
	@NotNull(message = "外部系统元素编号不能为空")
	@ApiModelProperty(value = "外部系统元素编号", required = true)
	private Long extElmRid;

    /**
	 * 顺序号
	 * 
     */
	@ApiModelProperty(value = "顺序号", required = false)
	private Integer seq;

    /**
	 * 类型
	 * 
     */
	@ApiModelProperty(value = "类型", required = false)
	@Size(max = 255, message = "类型超长，最多255个字符")
	private String type;

    /**
	 * 构造型
	 * 
     */
	@ApiModelProperty(value = "构造型", required = false)
	@Size(max = 255, message = "构造型超长，最多255个字符")
	private String stereotype;

    /**
	 * 记录状态
	 * 
     */
	@ApiModelProperty(value = "记录状态", required = false)
	private Integer recordState;

    /**
	 * 创建用户代码
	 * 
     */
	@ApiModelProperty(value = "创建用户代码", required = false)
	@Size(max = 255, message = "创建用户代码超长，最多255个字符")
	private String createUcode;

    /**
	 * 修改用户代码
	 * 
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
