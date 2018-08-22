package net.aicoder.devp.business.ops.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询运维元素连接扩充信息使用的DTO")
public class DevpOpsConnectorInfoCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户编号", notes = "[租户编号]")
	private Long tid;
	@ApiModelProperty(value = "租户编号最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户编号最小值")
	private Long tidMin;
	@ApiModelProperty(value = "元素类型", notes = "[元素类型]")
	private String etype;
	@ApiModelProperty(value = "扩展信息代码", notes = "[扩展信息代码]")
	private String code;
	@ApiModelProperty(value = "扩展信息名称", notes = "[扩展信息名称]-显示名称")
	private String name;
	@ApiModelProperty(value = "扩展信息别名", notes = "[扩展信息别名]")
	private String alias;
	@ApiModelProperty(value = "扩展信息描述", notes = "[扩展信息描述]-对应当前属性值")
	private String description;
	@ApiModelProperty(value = "记录状态", notes = "[记录状态]-0-失效;1-生效;缺省为1")
	private Integer recordState;
	@ApiModelProperty(value = "记录状态最大值")
	private Integer recordStateMax;
	@ApiModelProperty(value = "记录状态最小值")
	private Integer recordStateMin;
	@ApiModelProperty(value = "关联连接编号", notes = "[关联连接编号]")
	private Long contRid;
	@ApiModelProperty(value = "关联连接编号最大值")
	private Long contRidMax;
	@ApiModelProperty(value = "关联连接编号最小值")
	private Long contRidMin;
	@ApiModelProperty(value = "来源元素编号", notes = "[来源元素编号]")
	private Long srcElmRid;
	@ApiModelProperty(value = "来源元素编号最大值")
	private Long srcElmRidMax;
	@ApiModelProperty(value = "来源元素编号最小值")
	private Long srcElmRidMin;
	@ApiModelProperty(value = "来源元素实例编号", notes = "[来源元素实例编号]-缺省值为0")
	private Long srcInstRid;
	@ApiModelProperty(value = "来源元素实例编号最大值")
	private Long srcInstRidMax;
	@ApiModelProperty(value = "来源元素实例编号最小值")
	private Long srcInstRidMin;
	@ApiModelProperty(value = "目的元素编号", notes = "[目的元素编号]")
	private Long destElmRid;
	@ApiModelProperty(value = "目的元素编号最大值")
	private Long destElmRidMax;
	@ApiModelProperty(value = "目的元素编号最小值")
	private Long destElmRidMin;
	@ApiModelProperty(value = "目的元素实例编号", notes = "[目的元素实例编号]-缺省值为0")
	private Long destInstRid;
	@ApiModelProperty(value = "目的元素实例编号最大值")
	private Long destInstRidMax;
	@ApiModelProperty(value = "目的元素实例编号最小值")
	private Long destInstRidMin;
	@ApiModelProperty(value = "顺序号", notes = "[顺序号]")
	private Integer seq;
	@ApiModelProperty(value = "顺序号最大值")
	private Integer seqMax;
	@ApiModelProperty(value = "顺序号最小值")
	private Integer seqMin;
	@ApiModelProperty(value = "设值方式代码", notes = "[设值方式代码]-S-仅对来源,D-仅对目标,Bi-双方,N-均无效")
	private String typeCode;
	@ApiModelProperty(value = "设值方式名称", notes = "[设值方式名称]-S-仅对来源,D-仅对目标,Bi-双方,N-均无效")
	private String typeName;
	@ApiModelProperty(value = "扩展信息代码1", notes = "[扩展信息代码1]")
	private String infoCode1;
	@ApiModelProperty(value = "扩展信息值1", notes = "[扩展信息值1]")
	private String infoValue1;
	@ApiModelProperty(value = "扩展信息代码2", notes = "[扩展信息代码2]")
	private String infoCode2;
	@ApiModelProperty(value = "扩展信息值2", notes = "[扩展信息值2]")
	private String infoValue2;
	@ApiModelProperty(value = "扩展信息代码3", notes = "[扩展信息代码3]")
	private String infoCode3;
	@ApiModelProperty(value = "扩展信息值3", notes = "[扩展信息值3]")
	private String infoValue3;
	@ApiModelProperty(value = "扩展信息代码4", notes = "[扩展信息代码4]")
	private String infoCode4;
	@ApiModelProperty(value = "扩展信息值4", notes = "[扩展信息值4]")
	private String infoValue4;
	@ApiModelProperty(value = "扩展信息代码5", notes = "[扩展信息代码5]")
	private String infoCode5;
	@ApiModelProperty(value = "扩展信息值5", notes = "[扩展信息值5]")
	private String infoValue5;
	@ApiModelProperty(value = "备注", notes = "[备注]")
	private String notes;
	@ApiModelProperty(value = "参数定义标识", notes = "[参数定义标识]-扩展参数定义的标识")
	private String parasCode;
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

	public Long getTidMin(){
		return tidMin;
	}
	public void setTidMin(Long tidMin) {
		this.tidMin = tidMin;
	}

	public Long getTidMax(){
		return tidMax;
	}
	public void setTidMax(Long tidMax) {
		this.tidMax = tidMax;
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


	public Long getContRid(){
		return contRid;
	}
	public void setContRid(Long contRid) {
		this.contRid = contRid;
	}

	public Long getContRidMin(){
		return contRidMin;
	}
	public void setContRidMin(Long contRidMin) {
		this.contRidMin = contRidMin;
	}

	public Long getContRidMax(){
		return contRidMax;
	}
	public void setContRidMax(Long contRidMax) {
		this.contRidMax = contRidMax;
	}


	public Long getSrcElmRid(){
		return srcElmRid;
	}
	public void setSrcElmRid(Long srcElmRid) {
		this.srcElmRid = srcElmRid;
	}

	public Long getSrcElmRidMin(){
		return srcElmRidMin;
	}
	public void setSrcElmRidMin(Long srcElmRidMin) {
		this.srcElmRidMin = srcElmRidMin;
	}

	public Long getSrcElmRidMax(){
		return srcElmRidMax;
	}
	public void setSrcElmRidMax(Long srcElmRidMax) {
		this.srcElmRidMax = srcElmRidMax;
	}


	public Long getSrcInstRid(){
		return srcInstRid;
	}
	public void setSrcInstRid(Long srcInstRid) {
		this.srcInstRid = srcInstRid;
	}

	public Long getSrcInstRidMin(){
		return srcInstRidMin;
	}
	public void setSrcInstRidMin(Long srcInstRidMin) {
		this.srcInstRidMin = srcInstRidMin;
	}

	public Long getSrcInstRidMax(){
		return srcInstRidMax;
	}
	public void setSrcInstRidMax(Long srcInstRidMax) {
		this.srcInstRidMax = srcInstRidMax;
	}


	public Long getDestElmRid(){
		return destElmRid;
	}
	public void setDestElmRid(Long destElmRid) {
		this.destElmRid = destElmRid;
	}

	public Long getDestElmRidMin(){
		return destElmRidMin;
	}
	public void setDestElmRidMin(Long destElmRidMin) {
		this.destElmRidMin = destElmRidMin;
	}

	public Long getDestElmRidMax(){
		return destElmRidMax;
	}
	public void setDestElmRidMax(Long destElmRidMax) {
		this.destElmRidMax = destElmRidMax;
	}


	public Long getDestInstRid(){
		return destInstRid;
	}
	public void setDestInstRid(Long destInstRid) {
		this.destInstRid = destInstRid;
	}

	public Long getDestInstRidMin(){
		return destInstRidMin;
	}
	public void setDestInstRidMin(Long destInstRidMin) {
		this.destInstRidMin = destInstRidMin;
	}

	public Long getDestInstRidMax(){
		return destInstRidMax;
	}
	public void setDestInstRidMax(Long destInstRidMax) {
		this.destInstRidMax = destInstRidMax;
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


	public String getInfoCode1(){
		return infoCode1;
	}
	public void setInfoCode1(String infoCode1) {
		this.infoCode1 = infoCode1;
	}


	public String getInfoValue1(){
		return infoValue1;
	}
	public void setInfoValue1(String infoValue1) {
		this.infoValue1 = infoValue1;
	}


	public String getInfoCode2(){
		return infoCode2;
	}
	public void setInfoCode2(String infoCode2) {
		this.infoCode2 = infoCode2;
	}


	public String getInfoValue2(){
		return infoValue2;
	}
	public void setInfoValue2(String infoValue2) {
		this.infoValue2 = infoValue2;
	}


	public String getInfoCode3(){
		return infoCode3;
	}
	public void setInfoCode3(String infoCode3) {
		this.infoCode3 = infoCode3;
	}


	public String getInfoValue3(){
		return infoValue3;
	}
	public void setInfoValue3(String infoValue3) {
		this.infoValue3 = infoValue3;
	}


	public String getInfoCode4(){
		return infoCode4;
	}
	public void setInfoCode4(String infoCode4) {
		this.infoCode4 = infoCode4;
	}


	public String getInfoValue4(){
		return infoValue4;
	}
	public void setInfoValue4(String infoValue4) {
		this.infoValue4 = infoValue4;
	}


	public String getInfoCode5(){
		return infoCode5;
	}
	public void setInfoCode5(String infoCode5) {
		this.infoCode5 = infoCode5;
	}


	public String getInfoValue5(){
		return infoValue5;
	}
	public void setInfoValue5(String infoValue5) {
		this.infoValue5 = infoValue5;
	}


	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
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
