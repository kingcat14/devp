package net.aicoder.devp.business.deploy.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 资源部署环境节点
 * @author icode
 */
@ApiModel(value = "新增资源部署环境节点使用的DTO")
public class DevpSysDpyResExecenvAddDto {

    /**租户编号*/
	@ApiModelProperty(value = "租户编号", required = false, notes = "[租户编号]")
	private Long tid;

    /**元素类型*/
	@ApiModelProperty(value = "元素类型", required = false, notes = "[元素类型]-SYS_DPY_RES_EXECENV")
	private String etype;

    /**系统元素名称*/
	@ApiModelProperty(value = "系统元素名称", required = false, notes = "[系统元素名称]")
	private String name;

    /**系统元素代码*/
	@ApiModelProperty(value = "系统元素代码", required = false, notes = "[系统元素代码]")
	private String code;

    /**系统元素别名*/
	@ApiModelProperty(value = "系统元素别名", required = false, notes = "[系统元素别名]")
	private String alias;

    /**系统元素描述*/
	@ApiModelProperty(value = "系统元素描述", required = false, notes = "[系统元素描述]")
	private String description;

    /**记录状态*/
	@ApiModelProperty(value = "记录状态", required = false, notes = "[记录状态]-0-失效;1-生效;缺省为1")
	private Integer recordState;

    /**类型*/
	@ApiModelProperty(value = "类型", required = false, notes = "[类型]")
	private String type;

    /**子类型*/
	@ApiModelProperty(value = "子类型", required = false, notes = "[子类型]")
	private String subType;

    /**状态*/
	@ApiModelProperty(value = "状态", required = false, notes = "[状态]")
	private String status;

    /**备注*/
	@ApiModelProperty(value = "备注", required = false, notes = "[备注]")
	private String notes;

    /**产品编号*/
	@ApiModelProperty(value = "产品编号", required = false, notes = "[产品编号]")
	private Long prdRid;

    /**部署方案编号*/
	@ApiModelProperty(value = "部署方案编号", required = false, notes = "[部署方案编号]")
	private Long schemeRid;

    /**资源实例编号*/
	@ApiModelProperty(value = "资源实例编号", required = false, notes = "[资源实例编号]")
	private Long instRid;

    /**顺序号*/
	@ApiModelProperty(value = "顺序号", required = false, notes = "[顺序号]")
	private Integer seq;

    /**关联主机编号*/
	@ApiModelProperty(value = "关联主机编号", required = false, notes = "[关联主机编号]-对应devp_sys_dpy_host的记录编号")
	private Long hostRid;

    /**创建用户代码*/
	@ApiModelProperty(value = "创建用户代码", required = false, notes = "[创建用户代码]")
	private String createUcode;

    /**创建用户姓名*/
	@ApiModelProperty(value = "创建用户姓名", required = false, notes = "[创建用户姓名]")
	private String createUname;

    /**修改用户代码*/
	@ApiModelProperty(value = "修改用户代码", required = false, notes = "[修改用户代码]")
	private String modifyUcode;

    /**修改用户姓名*/
	@ApiModelProperty(value = "修改用户姓名", required = false, notes = "[修改用户姓名]")
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

	public Long getPrdRid(){
		return prdRid;
	}
	public void setPrdRid(Long prdRid) {
		this.prdRid = prdRid;
	}

	public Long getSchemeRid(){
		return schemeRid;
	}
	public void setSchemeRid(Long schemeRid) {
		this.schemeRid = schemeRid;
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

	public Long getHostRid(){
		return hostRid;
	}
	public void setHostRid(Long hostRid) {
		this.hostRid = hostRid;
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
