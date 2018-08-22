package net.aicoder.devp.business.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 系统组件
 * @author icode
 */
@ApiModel(value = "新增系统组件使用的DTO")
public class DevpSysCmpAddDto {

    /**
	 * 租户编号
	 * [租户编号]
     */
	@NotNull(message = "租户编号不能为空")
	@ApiModelProperty(value = "租户编号", required = true)
	private Long tid;

    /**
	 * 元素类型
	 * [元素类型]- SYSTEM// 系统
 SUB_SYS// 子系统
 SYS_CMP// 组件
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
	 * [范围]-访问控制范围:产品内可见，同模块可见，模块内可见
     */
	@ApiModelProperty(value = "范围", required = false)
	@Size(max = 255, message = "范围超长，最多255个字符")
	private String scope;

    /**
	 * 版本
	 * [版本]-当前版本
     */
	@ApiModelProperty(value = "版本", required = false)
	@Size(max = 255, message = "版本超长，最多255个字符")
	private String version;

    /**
	 * 阶段
	 * [阶段]-系统调研,系统设计,系统开发,试运行,系统运维,已停用
     */
	@ApiModelProperty(value = "阶段", required = false)
	@Size(max = 255, message = "阶段超长，最多255个字符")
	private String phase;

    /**
	 * 状态
	 * [状态]-未开始,进行中,已完成,暂停,取消
     */
	@ApiModelProperty(value = "状态", required = false)
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;

    /**
	 * 备注
	 * [备注]
     */
	@ApiModelProperty(value = "备注", required = false)
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String notes;

    /**
	 * 可安装组件
	 * [可安装组件]-是否为可安装组件，0:不可独立安装；1：可安装
     */
	@ApiModelProperty(value = "可安装组件", required = false)
	private Integer installable;

    /**
	 * 共享组件
	 * [共享组件]-是否为共享组件，0:不共享；1：共享
     */
	@ApiModelProperty(value = "共享组件", required = false)
	private Integer sharedComponent;

    /**
	 * 共享服务
	 * [共享服务]-是否提供共享服务，0:不共享；1：共享
     */
	@ApiModelProperty(value = "共享服务", required = false)
	private Integer sharedService;

    /**
	 * 产品编号
	 * [产品编号]
     */
	@NotNull(message = "产品编号不能为空")
	@ApiModelProperty(value = "产品编号", required = true)
	private Long prdRid;

    /**
	 * 父包编号
	 * [父包编号]
     */
	@ApiModelProperty(value = "父包编号", required = false)
	private Long parentRid;

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

	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getInstallable(){
		return installable;
	}
	public void setInstallable(Integer installable) {
		this.installable = installable;
	}

	public Integer getSharedComponent(){
		return sharedComponent;
	}
	public void setSharedComponent(Integer sharedComponent) {
		this.sharedComponent = sharedComponent;
	}

	public Integer getSharedService(){
		return sharedService;
	}
	public void setSharedService(Integer sharedService) {
		this.sharedService = sharedService;
	}

	public Long getPrdRid(){
		return prdRid;
	}
	public void setPrdRid(Long prdRid) {
		this.prdRid = prdRid;
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
