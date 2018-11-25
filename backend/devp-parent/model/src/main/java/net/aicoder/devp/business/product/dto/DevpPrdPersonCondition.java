package net.aicoder.devp.business.product.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询产品干系人使用的DTO")
public class DevpPrdPersonCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户编号", notes = "[租户编号]")
	private Long tid;
	@ApiModelProperty(value = "etype", notes = "")
	private String etype;
	@ApiModelProperty(value = "用户代码", notes = "[用户代码]")
	private String code;
	@ApiModelProperty(value = "用户名称", notes = "[用户名称]")
	private String name;
	@ApiModelProperty(value = "用户别名", notes = "[用户别名]")
	private String alias;
	@ApiModelProperty(value = "用户描述", notes = "[用户描述]")
	private String description;
	@ApiModelProperty(value = "关联元素类型", notes = "[关联元素类型]-prdline-产品线、product-产品")
	private String nexusType;
	@ApiModelProperty(value = "关联元素编号", notes = "[关联元素编号]")
	private Long nexusRid;
	@ApiModelProperty(value = "关联元素编号最大值")
	private Long nexusRidMax;
	@ApiModelProperty(value = "关联元素编号最小值")
	private Long nexusRidMin;
	@ApiModelProperty(value = "顺序号", notes = "[顺序号]")
	private Integer seq;
	@ApiModelProperty(value = "顺序号最大值")
	private Integer seqMax;
	@ApiModelProperty(value = "顺序号最小值")
	private Integer seqMin;
	@ApiModelProperty(value = "用户编号", notes = "[用户编号]")
	private Long uid;
	@ApiModelProperty(value = "用户编号最大值")
	private Long uidMax;
	@ApiModelProperty(value = "用户编号最小值")
	private Long uidMin;
	@ApiModelProperty(value = "用户类型", notes = "[用户类型]")
	private String type;
	@ApiModelProperty(value = "用户类型", notes = "[用户类型]")
	private String role;
	@ApiModelProperty(value = "状态", notes = "[状态]")
	private String status;
	@ApiModelProperty(value = "用户租户编号", notes = "[用户租户编号]-为未来交易撮合预留")
	private Long userTid;
	@ApiModelProperty(value = "用户租户编号最大值")
	private Long userTidMax;
	@ApiModelProperty(value = "用户租户编号最小值")
	private Long userTidMin;
	@ApiModelProperty(value = "组织编号", notes = "[组织编号]")
	private Long orgRid;
	@ApiModelProperty(value = "组织编号最大值")
	private Long orgRidMax;
	@ApiModelProperty(value = "组织编号最小值")
	private Long orgRidMin;
	@ApiModelProperty(value = "组织名称", notes = "[组织名称]")
	private String orgName;
	@ApiModelProperty(value = "记录状态", notes = "[记录状态]-0-失效;1-生效;缺省为1")
	private Integer recordState;
	@ApiModelProperty(value = "记录状态最大值")
	private Integer recordStateMax;
	@ApiModelProperty(value = "记录状态最小值")
	private Integer recordStateMin;
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

	public Long getNexusRidMin(){
		return nexusRidMin;
	}
	public void setNexusRidMin(Long nexusRidMin) {
		this.nexusRidMin = nexusRidMin;
	}

	public Long getNexusRidMax(){
		return nexusRidMax;
	}
	public void setNexusRidMax(Long nexusRidMax) {
		this.nexusRidMax = nexusRidMax;
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


	public Long getUid(){
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getUidMin(){
		return uidMin;
	}
	public void setUidMin(Long uidMin) {
		this.uidMin = uidMin;
	}

	public Long getUidMax(){
		return uidMax;
	}
	public void setUidMax(Long uidMax) {
		this.uidMax = uidMax;
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

	public Long getUserTidMin(){
		return userTidMin;
	}
	public void setUserTidMin(Long userTidMin) {
		this.userTidMin = userTidMin;
	}

	public Long getUserTidMax(){
		return userTidMax;
	}
	public void setUserTidMax(Long userTidMax) {
		this.userTidMax = userTidMax;
	}


	public Long getOrgRid(){
		return orgRid;
	}
	public void setOrgRid(Long orgRid) {
		this.orgRid = orgRid;
	}

	public Long getOrgRidMin(){
		return orgRidMin;
	}
	public void setOrgRidMin(Long orgRidMin) {
		this.orgRidMin = orgRidMin;
	}

	public Long getOrgRidMax(){
		return orgRidMax;
	}
	public void setOrgRidMax(Long orgRidMax) {
		this.orgRidMax = orgRidMax;
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
