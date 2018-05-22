package net.aicoder.devp.product.business.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
 * 产品线定义
 * @author icode
 */
@ApiModel(value = "修改产品线定义使用的DTO")
public class DevpPrdPrdlineEditDto {

    /**
	 * 租户编号
	 * 
     */
	@NotNull(message = "租户编号不能为空")
	@ApiModelProperty(value = "租户编号", required = true)
	private Long tid;

    /**
	 * 产品线代码
	 * 
     */
	@NotNull(message = "产品线代码不能为空")
	@ApiModelProperty(value = "产品线代码", required = true)
	@Size(max = 255, message = "产品线代码超长，最多255个字符")
	private String code;

    /**
	 * 产品线名称
	 * 
     */
	@NotNull(message = "产品线名称不能为空")
	@ApiModelProperty(value = "产品线名称", required = true)
	@Size(max = 255, message = "产品线名称超长，最多255个字符")
	private String name;

    /**
	 * 产品线别名
	 * 
     */
	@ApiModelProperty(value = "产品线别名", required = false)
	@Size(max = 255, message = "产品线别名超长，最多255个字符")
	private String alias;

    /**
	 * 产品线描述
	 * 
     */
	@ApiModelProperty(value = "产品线描述", required = false)
	@Size(max = 255, message = "产品线描述超长，最多255个字符")
	private String description;

    /**
	 * 产品线类型
	 * 
     */
	@ApiModelProperty(value = "产品线类型", required = false)
	@Size(max = 255, message = "产品线类型超长，最多255个字符")
	private String type;

    /**
	 * 领域
	 * 
     */
	@ApiModelProperty(value = "领域", required = false)
	@Size(max = 255, message = "领域超长，最多255个字符")
	private String domain;

    /**
	 * 构造型
	 * 
     */
	@ApiModelProperty(value = "构造型", required = false)
	@Size(max = 255, message = "构造型超长，最多255个字符")
	private String stereotype;

    /**
	 * 访问控制范围
	 * 
     */
	@ApiModelProperty(value = "访问控制范围", required = false)
	@Size(max = 255, message = "访问控制范围超长，最多255个字符")
	private String scope;

    /**
	 * 版本
	 * 
     */
	@ApiModelProperty(value = "版本", required = false)
	@Size(max = 255, message = "版本超长，最多255个字符")
	private String version;

    /**
	 * 阶段
	 * 
     */
	@ApiModelProperty(value = "阶段", required = false)
	@Size(max = 255, message = "阶段超长，最多255个字符")
	private String phase;

    /**
	 * 状态
	 * 
     */
	@ApiModelProperty(value = "状态", required = false)
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;

    /**
	 * 父产品线编号
	 * 
     */
	@ApiModelProperty(value = "父产品线编号", required = false)
	private Long parentRid;

    /**
	 * 顺序号
	 * 
     */
	@ApiModelProperty(value = "顺序号", required = false)
	private Integer seq;

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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
