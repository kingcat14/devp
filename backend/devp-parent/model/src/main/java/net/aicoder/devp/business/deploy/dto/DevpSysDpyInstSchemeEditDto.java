package net.aicoder.devp.business.deploy.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 资源实例所属的部署方案
 * @author icode
 */
@ApiModel(value = "修改资源实例所属的部署方案使用的DTO")
public class DevpSysDpyInstSchemeEditDto {


    /**
	 * 租户编号
	 * [租户编号]
     */
	@NotNull(message = "租户编号不能为空")
	@ApiModelProperty(value = "租户编号", required = true)
	private Long tid;


    /**
	 * 元素类型
	 * [元素类型]-SYS_DPY_SCHEME// 部署方案
     */
	@NotNull(message = "元素类型不能为空")
	@ApiModelProperty(value = "元素类型", required = true)
	@Size(max = 255, message = "元素类型超长，最多255个字符")
	private String etype;


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
	 * 记录状态
	 * [记录状态]-0-失效;1-生效;缺省为1
     */
	@ApiModelProperty(value = "记录状态", required = false)
	private Integer recordState;


    /**
	 * 类型
	 * [类型]-开发,测试,验证,生产
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
	 * 产品编号
	 * [产品编号]
     */
	@NotNull(message = "产品编号不能为空")
	@ApiModelProperty(value = "产品编号", required = true)
	private Long prdRid;


    /**
	 * 关联资源编号
	 * [关联资源编号]
     */
	@NotNull(message = "关联资源编号不能为空")
	@ApiModelProperty(value = "关联资源编号", required = true)
	private Long resRid;


    /**
	 * 关联资源实例编号
	 * [关联资源实例编号]
     */
	@NotNull(message = "关联资源实例编号不能为空")
	@ApiModelProperty(value = "关联资源实例编号", required = true)
	private Long instRid;


    /**
	 * 部署方案编号
	 * [部署方案编号]
     */
	@NotNull(message = "部署方案编号不能为空")
	@ApiModelProperty(value = "部署方案编号", required = true)
	private Long schemeRid;


    /**
	 * 顺序号
	 * [顺序号]
     */
	@ApiModelProperty(value = "顺序号", required = false)
	private Integer seq;


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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
