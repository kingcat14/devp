package net.aicoder.devp.business.ops.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询资产项目分组使用的DTO")
public class DevpOpsCiGroupCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户编号", notes = "[租户编号]")
	private Long tid;
	@ApiModelProperty(value = "元素类型", notes = "[元素类型]")
	private String etype;
	@ApiModelProperty(value = "名称", notes = "[名称]")
	private String name;
	@ApiModelProperty(value = "代码", notes = "[代码]")
	private String code;
	@ApiModelProperty(value = "别名", notes = "[别名]")
	private String alias;
	@ApiModelProperty(value = "描述", notes = "[描述]-资产项目所属分组描述")
	private String description;
	@ApiModelProperty(value = "记录状态", notes = "[记录状态]-0-失效;1-生效;缺省为1")
	private Integer recordState;
	@ApiModelProperty(value = "记录状态最大值")
	private Integer recordStateMax;
	@ApiModelProperty(value = "记录状态最小值")
	private Integer recordStateMin;
	@ApiModelProperty(value = "类型代码", notes = "[类型代码]")
	private String typeCode;
	@ApiModelProperty(value = "类型名称", notes = "[类型名称]-冗余字段，方便显示")
	private String typeName;
	@ApiModelProperty(value = "分组记录编号", notes = "[分组记录编号]")
	private Long groupRid;
	@ApiModelProperty(value = "分组记录编号最大值")
	private Long groupRidMax;
	@ApiModelProperty(value = "分组记录编号最小值")
	private Long groupRidMin;
	@ApiModelProperty(value = "资产记录编号", notes = "[资产记录编号]")
	private Long ciRid;
	@ApiModelProperty(value = "资产记录编号最大值")
	private Long ciRidMax;
	@ApiModelProperty(value = "资产记录编号最小值")
	private Long ciRidMin;
	@ApiModelProperty(value = "顺序号", notes = "[顺序号]")
	private Integer seq;
	@ApiModelProperty(value = "顺序号最大值")
	private Integer seqMax;
	@ApiModelProperty(value = "顺序号最小值")
	private Integer seqMin;
	@ApiModelProperty(value = "参数定义标识", notes = "[参数定义标识]-扩展参数定义的标识")
	private String parasCode;
	@ApiModelProperty(value = "创建用户代码", notes = "[创建用户代码]")
	private String createUcode;
	@ApiModelProperty(value = "创建用户姓名", notes = "[创建用户姓名]")
	private String createUname;
	@ApiModelProperty(value = "修改用户代码", notes = "[修改用户代码]")
	private String cmodifyUcode;
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

	public Long getGroupRidMin(){
		return groupRidMin;
	}
	public void setGroupRidMin(Long groupRidMin) {
		this.groupRidMin = groupRidMin;
	}

	public Long getGroupRidMax(){
		return groupRidMax;
	}
	public void setGroupRidMax(Long groupRidMax) {
		this.groupRidMax = groupRidMax;
	}


	public Long getCiRid(){
		return ciRid;
	}
	public void setCiRid(Long ciRid) {
		this.ciRid = ciRid;
	}

	public Long getCiRidMin(){
		return ciRidMin;
	}
	public void setCiRidMin(Long ciRidMin) {
		this.ciRidMin = ciRidMin;
	}

	public Long getCiRidMax(){
		return ciRidMax;
	}
	public void setCiRidMax(Long ciRidMax) {
		this.ciRidMax = ciRidMax;
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


	public String getParasCode(){
		return parasCode;
	}
	public void setParasCode(String parasCode) {
		this.parasCode = parasCode;
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


	public String getCmodifyUcode(){
		return cmodifyUcode;
	}
	public void setCmodifyUcode(String cmodifyUcode) {
		this.cmodifyUcode = cmodifyUcode;
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
