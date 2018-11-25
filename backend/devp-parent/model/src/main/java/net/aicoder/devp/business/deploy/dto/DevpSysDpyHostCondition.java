package net.aicoder.devp.business.deploy.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询部署主机节点使用的DTO")
public class DevpSysDpyHostCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户编号", notes = "[租户编号]")
	private Long tid;
	@ApiModelProperty(value = "元素类型", notes = "[元素类型]-SYS_DPY_RES_INST // 关联资源实例")
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
	@ApiModelProperty(value = "主机标识", notes = "[主机标识]-同一个部署方案中不能重复")
	private String flag;
	@ApiModelProperty(value = "类型", notes = "[类型]")
	private String type;
	@ApiModelProperty(value = "子类型", notes = "[子类型]")
	private String subType;
	@ApiModelProperty(value = "状态", notes = "[状态]")
	private String status;
	@ApiModelProperty(value = "内部访问地址", notes = "[内部访问地址]-内部访问地址，如：内网IP")
	private String intAccessAddr;
	@ApiModelProperty(value = "外部访问地址", notes = "[外部访问地址]-内部访问地址，如：内网IP")
	private String extAccessAddr;
	@ApiModelProperty(value = "备注", notes = "[备注]")
	private String notes;
	@ApiModelProperty(value = "产品编号", notes = "[产品编号]")
	private Long prdRid;
	@ApiModelProperty(value = "产品编号最大值")
	private Long prdRidMax;
	@ApiModelProperty(value = "产品编号最小值")
	private Long prdRidMin;
	@ApiModelProperty(value = "部署方案编号", notes = "[部署方案编号]")
	private Long schemeRid;
	@ApiModelProperty(value = "部署方案编号最大值")
	private Long schemeRidMax;
	@ApiModelProperty(value = "部署方案编号最小值")
	private Long schemeRidMin;
	@ApiModelProperty(value = "顺序号", notes = "[顺序号]")
	private Integer seq;
	@ApiModelProperty(value = "顺序号最大值")
	private Integer seqMax;
	@ApiModelProperty(value = "顺序号最小值")
	private Integer seqMin;
	@ApiModelProperty(value = "关联IT资产编号", notes = "[关联IT资产编号]")
	private Long assetRid;
	@ApiModelProperty(value = "关联IT资产编号最大值")
	private Long assetRidMax;
	@ApiModelProperty(value = "关联IT资产编号最小值")
	private Long assetRidMin;
	@ApiModelProperty(value = "关联IT资产元素类型", notes = "[关联IT资产元素类型]")
	private String assetEtype;
	@ApiModelProperty(value = "关联IT资产类型代码", notes = "[关联IT资产类型代码]")
	private String assetTypeCode;
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


	public String getFlag(){
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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


	public String getIntAccessAddr(){
		return intAccessAddr;
	}
	public void setIntAccessAddr(String intAccessAddr) {
		this.intAccessAddr = intAccessAddr;
	}


	public String getExtAccessAddr(){
		return extAccessAddr;
	}
	public void setExtAccessAddr(String extAccessAddr) {
		this.extAccessAddr = extAccessAddr;
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


	public Long getSchemeRid(){
		return schemeRid;
	}
	public void setSchemeRid(Long schemeRid) {
		this.schemeRid = schemeRid;
	}

	public Long getSchemeRidMin(){
		return schemeRidMin;
	}
	public void setSchemeRidMin(Long schemeRidMin) {
		this.schemeRidMin = schemeRidMin;
	}

	public Long getSchemeRidMax(){
		return schemeRidMax;
	}
	public void setSchemeRidMax(Long schemeRidMax) {
		this.schemeRidMax = schemeRidMax;
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


	public Long getAssetRid(){
		return assetRid;
	}
	public void setAssetRid(Long assetRid) {
		this.assetRid = assetRid;
	}

	public Long getAssetRidMin(){
		return assetRidMin;
	}
	public void setAssetRidMin(Long assetRidMin) {
		this.assetRidMin = assetRidMin;
	}

	public Long getAssetRidMax(){
		return assetRidMax;
	}
	public void setAssetRidMax(Long assetRidMax) {
		this.assetRidMax = assetRidMax;
	}


	public String getAssetEtype(){
		return assetEtype;
	}
	public void setAssetEtype(String assetEtype) {
		this.assetEtype = assetEtype;
	}


	public String getAssetTypeCode(){
		return assetTypeCode;
	}
	public void setAssetTypeCode(String assetTypeCode) {
		this.assetTypeCode = assetTypeCode;
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
