package net.aicoder.devp.business.ops.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 资产分组
 * @author icode
 */
@ApiModel(value = "修改资产分组使用的DTO")
public class DevpOpsAssetGroupEditDto {


    /**
	 * 租户编号
	 * [租户编号]
     */
	@NotNull(message = "租户编号不能为空")
	@ApiModelProperty(value = "租户编号", required = true)
	private Long tid;


    /**
	 * 元素类型
	 * [元素类型]-ASSET_GROUP
     */
	@NotNull(message = "元素类型不能为空")
	@ApiModelProperty(value = "元素类型", required = true)
	@Size(max = 255, message = "元素类型超长，最多255个字符")
	private String etype;


    /**
	 * 名称
	 * [名称]-资产分组名称
     */
	@NotNull(message = "名称不能为空")
	@ApiModelProperty(value = "名称", required = true)
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;


    /**
	 * 代码
	 * [代码]-资产分组代码
     */
	@ApiModelProperty(value = "代码", required = false)
	@Size(max = 255, message = "代码超长，最多255个字符")
	private String code;


    /**
	 * 别名
	 * [别名]-资产分组别名
     */
	@ApiModelProperty(value = "别名", required = false)
	@Size(max = 255, message = "别名超长，最多255个字符")
	private String alias;


    /**
	 * 描述
	 * [描述]-资产分组描述
     */
	@ApiModelProperty(value = "描述", required = false)
	@Size(max = 255, message = "描述超长，最多255个字符")
	private String description;


    /**
	 * 记录状态
	 * [记录状态]-0-失效;1-生效;缺省为1
     */
	@ApiModelProperty(value = "记录状态", required = false)
	private Integer recordState;


    /**
	 * 类型代码
	 * [类型代码]
     */
	@ApiModelProperty(value = "类型代码", required = false)
	@Size(max = 255, message = "类型代码超长，最多255个字符")
	private String typeCode;


    /**
	 * 类型名称
	 * [类型名称]-冗余字段，方便显示
     */
	@ApiModelProperty(value = "类型名称", required = false)
	@Size(max = 255, message = "类型名称超长，最多255个字符")
	private String typeName;


    /**
	 * 构造型
	 * [构造型]-(保留)
     */
	@ApiModelProperty(value = "构造型", required = false)
	@Size(max = 255, message = "构造型超长，最多255个字符")
	private String stereotype;


    /**
	 * 访问控制范围
	 * [访问控制范围]-(保留)
     */
	@ApiModelProperty(value = "访问控制范围", required = false)
	@Size(max = 255, message = "访问控制范围超长，最多255个字符")
	private String scope;


    /**
	 * 版本
	 * [版本]-(保留)
     */
	@ApiModelProperty(value = "版本", required = false)
	@Size(max = 255, message = "版本超长，最多255个字符")
	private String version;


    /**
	 * 阶段
	 * [阶段]-(保留)
     */
	@ApiModelProperty(value = "阶段", required = false)
	@Size(max = 255, message = "阶段超长，最多255个字符")
	private String phase;


    /**
	 * 状态
	 * [状态]-(保留)
     */
	@ApiModelProperty(value = "状态", required = false)
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;


    /**
	 * 父记录编号
	 * [父记录编号]
     */
	@ApiModelProperty(value = "父记录编号", required = false)
	private Long parentRid;


    /**
	 * 顺序号
	 * [顺序号]
     */
	@ApiModelProperty(value = "顺序号", required = false)
	private Integer seq;


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

	public String getTypeCode(){
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName(){
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
