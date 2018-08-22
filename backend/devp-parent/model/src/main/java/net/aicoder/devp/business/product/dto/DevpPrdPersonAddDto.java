package net.aicoder.devp.business.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 产品干系人
 * @author icode
 */
@ApiModel(value = "新增产品干系人使用的DTO")
public class DevpPrdPersonAddDto {

    /**租户编号*/
	@ApiModelProperty(value = "租户编号", required = false, notes = "[租户编号]")
	private Long tid;

    /**etype*/
	@ApiModelProperty(value = "etype", required = false, notes = "")
	private String etype;

    /**用户代码*/
	@ApiModelProperty(value = "用户代码", required = false, notes = "[用户代码]")
	private String code;

    /**用户名称*/
	@ApiModelProperty(value = "用户名称", required = false, notes = "[用户名称]")
	private String name;

    /**用户别名*/
	@ApiModelProperty(value = "用户别名", required = false, notes = "[用户别名]")
	private String alias;

    /**用户描述*/
	@ApiModelProperty(value = "用户描述", required = false, notes = "[用户描述]")
	private String description;

    /**关联元素类型*/
	@ApiModelProperty(value = "关联元素类型", required = false, notes = "[关联元素类型]-prdline-产品线、product-产品")
	private String nexusType;

    /**关联元素编号*/
	@ApiModelProperty(value = "关联元素编号", required = false, notes = "[关联元素编号]")
	private Long nexusRid;

    /**顺序号*/
	@ApiModelProperty(value = "顺序号", required = false, notes = "[顺序号]")
	private Integer seq;

    /**用户编号*/
	@ApiModelProperty(value = "用户编号", required = false, notes = "[用户编号]")
	private Long uid;

    /**用户类型*/
	@ApiModelProperty(value = "用户类型", required = false, notes = "[用户类型]")
	private String type;

    /**用户类型*/
	@ApiModelProperty(value = "用户类型", required = false, notes = "[用户类型]")
	private String role;

    /**状态*/
	@ApiModelProperty(value = "状态", required = false, notes = "[状态]")
	private String status;

    /**用户租户编号*/
	@ApiModelProperty(value = "用户租户编号", required = false, notes = "[用户租户编号]-为未来交易撮合预留")
	private Long userTid;

    /**组织编号*/
	@ApiModelProperty(value = "组织编号", required = false, notes = "[组织编号]")
	private Long orgRid;

    /**组织名称*/
	@ApiModelProperty(value = "组织名称", required = false, notes = "[组织名称]")
	private String orgName;

    /**记录状态*/
	@ApiModelProperty(value = "记录状态", required = false, notes = "[记录状态]-0-失效;1-生效;缺省为1")
	private Integer recordState;

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

	public String getNexusType(){
		return nexusType;
	}
	public void setNexusType(String nexusType) {
		this.nexusType = nexusType;
	}

	public Long getNexusRid(){
		return nexusRid;
	}
	public void setNexusRid(Long nexusRid) {
		this.nexusRid = nexusRid;
	}

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Long getUid(){
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getRole(){
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Long getUserTid(){
		return userTid;
	}
	public void setUserTid(Long userTid) {
		this.userTid = userTid;
	}

	public Long getOrgRid(){
		return orgRid;
	}
	public void setOrgRid(Long orgRid) {
		this.orgRid = orgRid;
	}

	public String getOrgName(){
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
