package net.aicoder.devp.business.sys.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询系统组件使用的DTO")
public class DevpSysCmpCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户编号", notes = "[租户编号]")
	private Long tid;
	@ApiModelProperty(value = "元素类型", notes = "[元素类型]- SYSTEM// 系统 SUB_SYS// 子系统 SYS_CMP// 组件 ")
	private String etype;
	@ApiModelProperty(value = "系统元素名称", notes = "[系统元素名称]")
	private String name;
	@ApiModelProperty(value = "系统元素代码", notes = "[系统元素代码]")
	private String code;
	@ApiModelProperty(value = "系统元素别名", notes = "[系统元素别名]")
	private String alias;
	@ApiModelProperty(value = "系统元素描述", notes = "[系统元素描述]")
	private String description;
	@ApiModelProperty(value = "记录状态", notes = "[记录状态]-0-失效;1-生效;缺省为1")
	private Integer recordState;
	@ApiModelProperty(value = "记录状态最大值")
	private Integer recordStateMax;
	@ApiModelProperty(value = "记录状态最小值")
	private Integer recordStateMin;
	@ApiModelProperty(value = "类型", notes = "[类型]")
	private String type;
	@ApiModelProperty(value = "子类型", notes = "[子类型]")
	private String subType;
	@ApiModelProperty(value = "构造型", notes = "[构造型]")
	private String stereotype;
	@ApiModelProperty(value = "范围", notes = "[范围]-访问控制范围:产品内可见，同模块可见，模块内可见")
	private String scope;
	@ApiModelProperty(value = "版本", notes = "[版本]-当前版本")
	private String version;
	@ApiModelProperty(value = "阶段", notes = "[阶段]-系统调研,系统设计,系统开发,试运行,系统运维,已停用")
	private String phase;
	@ApiModelProperty(value = "状态", notes = "[状态]-未开始,进行中,已完成,暂停,取消")
	private String status;
	@ApiModelProperty(value = "备注", notes = "[备注]")
	private String notes;
	@ApiModelProperty(value = "可安装组件", notes = "[可安装组件]-是否为可安装组件，0:不可独立安装；1：可安装")
	private Integer installable;
	@ApiModelProperty(value = "可安装组件最大值")
	private Integer installableMax;
	@ApiModelProperty(value = "可安装组件最小值")
	private Integer installableMin;
	@ApiModelProperty(value = "共享组件", notes = "[共享组件]-是否为共享组件，0:不共享；1：共享")
	private Integer sharedComponent;
	@ApiModelProperty(value = "共享组件最大值")
	private Integer sharedComponentMax;
	@ApiModelProperty(value = "共享组件最小值")
	private Integer sharedComponentMin;
	@ApiModelProperty(value = "共享服务", notes = "[共享服务]-是否提供共享服务，0:不共享；1：共享")
	private Integer sharedService;
	@ApiModelProperty(value = "共享服务最大值")
	private Integer sharedServiceMax;
	@ApiModelProperty(value = "共享服务最小值")
	private Integer sharedServiceMin;
	@ApiModelProperty(value = "产品编号", notes = "[产品编号]")
	private Long prdRid;
	@ApiModelProperty(value = "产品编号最大值")
	private Long prdRidMax;
	@ApiModelProperty(value = "产品编号最小值")
	private Long prdRidMin;
	@ApiModelProperty(value = "父包编号", notes = "[父包编号]")
	private Long parentRid;
	@ApiModelProperty(value = "父包编号最大值")
	private Long parentRidMax;
	@ApiModelProperty(value = "父包编号最小值")
	private Long parentRidMin;
	@ApiModelProperty(value = "顺序号", notes = "[顺序号]")
	private Integer seq;
	@ApiModelProperty(value = "顺序号最大值")
	private Integer seqMax;
	@ApiModelProperty(value = "顺序号最小值")
	private Integer seqMin;
	@ApiModelProperty(value = "创建用户代码", notes = "[创建用户代码]")
	private String createUcode;
	@ApiModelProperty(value = "创建用户姓名", notes = "[创建用户姓名]")
	private String createUname;
	@ApiModelProperty(value = "修改用户代码", notes = "[修改用户代码]")
	private String modifyUcode;
	@ApiModelProperty(value = "修改用户姓名", notes = "[修改用户姓名]")
	private String modifyUname;


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

	public Integer getInstallableMin(){
		return installableMin;
	}
	public void setInstallableMin(Integer installableMin) {
		this.installableMin = installableMin;
	}

	public Integer getInstallableMax(){
		return installableMax;
	}
	public void setInstallableMax(Integer installableMax) {
		this.installableMax = installableMax;
	}


	public Integer getSharedComponent(){
		return sharedComponent;
	}
	public void setSharedComponent(Integer sharedComponent) {
		this.sharedComponent = sharedComponent;
	}

	public Integer getSharedComponentMin(){
		return sharedComponentMin;
	}
	public void setSharedComponentMin(Integer sharedComponentMin) {
		this.sharedComponentMin = sharedComponentMin;
	}

	public Integer getSharedComponentMax(){
		return sharedComponentMax;
	}
	public void setSharedComponentMax(Integer sharedComponentMax) {
		this.sharedComponentMax = sharedComponentMax;
	}


	public Integer getSharedService(){
		return sharedService;
	}
	public void setSharedService(Integer sharedService) {
		this.sharedService = sharedService;
	}

	public Integer getSharedServiceMin(){
		return sharedServiceMin;
	}
	public void setSharedServiceMin(Integer sharedServiceMin) {
		this.sharedServiceMin = sharedServiceMin;
	}

	public Integer getSharedServiceMax(){
		return sharedServiceMax;
	}
	public void setSharedServiceMax(Integer sharedServiceMax) {
		this.sharedServiceMax = sharedServiceMax;
	}


	public Long getPrdRid(){
		return prdRid;
	}
	public void setPrdRid(Long prdRid) {
		this.prdRid = prdRid;
	}

	public Long getPrdRidMin(){
		return prdRidMin;
	}
	public void setPrdRidMin(Long prdRidMin) {
		this.prdRidMin = prdRidMin;
	}

	public Long getPrdRidMax(){
		return prdRidMax;
	}
	public void setPrdRidMax(Long prdRidMax) {
		this.prdRidMax = prdRidMax;
	}


	public Long getParentRid(){
		return parentRid;
	}
	public void setParentRid(Long parentRid) {
		this.parentRid = parentRid;
	}

	public Long getParentRidMin(){
		return parentRidMin;
	}
	public void setParentRidMin(Long parentRidMin) {
		this.parentRidMin = parentRidMin;
	}

	public Long getParentRidMax(){
		return parentRidMax;
	}
	public void setParentRidMax(Long parentRidMax) {
		this.parentRidMax = parentRidMax;
	}


	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getSeqMin(){
		return seqMin;
	}
	public void setSeqMin(Integer seqMin) {
		this.seqMin = seqMin;
	}

	public Integer getSeqMax(){
		return seqMax;
	}
	public void setSeqMax(Integer seqMax) {
		this.seqMax = seqMax;
	}


	public String getCreateUcode(){
		return createUcode;
	}
	public void setCreateUcode(String createUcode) {
		this.createUcode = createUcode;
	}


	public String getCreateUname(){
		return createUname;
	}
	public void setCreateUname(String createUname) {
		this.createUname = createUname;
	}


	public String getModifyUcode(){
		return modifyUcode;
	}
	public void setModifyUcode(String modifyUcode) {
		this.modifyUcode = modifyUcode;
	}


	public String getModifyUname(){
		return modifyUname;
	}
	public void setModifyUname(String modifyUname) {
		this.modifyUname = modifyUname;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
