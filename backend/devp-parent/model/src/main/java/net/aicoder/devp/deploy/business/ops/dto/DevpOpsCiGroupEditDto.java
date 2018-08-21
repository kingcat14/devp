package net.aicoder.devp.deploy.business.ops.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 资产项目分组映射
 * @author icode
 */
@ApiModel(value = "修改资产项目分组映射使用的DTO")
public class DevpOpsCiGroupEditDto {


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
	 * 名称
	 * [名称]
     */
	@ApiModelProperty(value = "名称", required = false)
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;


    /**
	 * 代码
	 * [代码]
     */
	@ApiModelProperty(value = "代码", required = false)
	@Size(max = 255, message = "代码超长，最多255个字符")
	private String code;


    /**
	 * 别名
	 * [别名]
     */
	@ApiModelProperty(value = "别名", required = false)
	@Size(max = 255, message = "别名超长，最多255个字符")
	private String alias;


    /**
	 * 描述
	 * [描述]-资产项目所属分组描述
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
	 * 分组记录编号
	 * [分组记录编号]
     */
	@NotNull(message = "分组记录编号不能为空")
	@ApiModelProperty(value = "分组记录编号", required = true)
	private Long groupRid;


    /**
	 * 资产记录编号
	 * [资产记录编号]
     */
	@NotNull(message = "资产记录编号不能为空")
	@ApiModelProperty(value = "资产记录编号", required = true)
	private Long ciRid;


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


    /**
	 * 修改用户代码
	 * [修改用户代码]
     */
	@ApiModelProperty(value = "修改用户代码", required = false)
	@Size(max = 255, message = "修改用户代码超长，最多255个字符")
	private String cmodifyUcode;


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

	public Long getGroupRid(){
		return groupRid;
	}
	public void setGroupRid(Long groupRid) {
		this.groupRid = groupRid;
	}

	public Long getCiRid(){
		return ciRid;
	}
	public void setCiRid(Long ciRid) {
		this.ciRid = ciRid;
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

	public String getCmodifyUcode(){
		return cmodifyUcode;
	}
	public void setCmodifyUcode(String cmodifyUcode) {
		this.cmodifyUcode = cmodifyUcode;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
