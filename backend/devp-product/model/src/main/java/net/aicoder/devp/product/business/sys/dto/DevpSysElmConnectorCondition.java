package net.aicoder.devp.product.business.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询系统元素间关系使用的DTO")
public class DevpSysElmConnectorCondition implements Serializable{

	@ApiModelProperty(value = "租户编号")
	private Long tid;
	@ApiModelProperty(value = "租户编号最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户编号最小值")
	private Long tidMin;
	@ApiModelProperty(value = "对应关系代码")
	private String code;
	@ApiModelProperty(value = "对应关系名称")
	private String name;
	@ApiModelProperty(value = "对应关系别名")
	private String alias;
	@ApiModelProperty(value = "对应关系描述")
	private String description;
	@ApiModelProperty(value = "来源产品编号")
	private Long sprdRid;
	@ApiModelProperty(value = "来源产品编号最大值")
	private Long sprdRidMax;
	@ApiModelProperty(value = "来源产品编号最小值")
	private Long sprdRidMin;
	@ApiModelProperty(value = "来源系统元素编号")
	private Long selmRid;
	@ApiModelProperty(value = "来源系统元素编号最大值")
	private Long selmRidMax;
	@ApiModelProperty(value = "来源系统元素编号最小值")
	private Long selmRidMin;
	@ApiModelProperty(value = "目标产品编号")
	private Long dprdRid;
	@ApiModelProperty(value = "目标产品编号最大值")
	private Long dprdRidMax;
	@ApiModelProperty(value = "目标产品编号最小值")
	private Long dprdRidMin;
	@ApiModelProperty(value = "目标系统元素编号")
	private Long delmRid;
	@ApiModelProperty(value = "目标系统元素编号最大值")
	private Long delmRidMax;
	@ApiModelProperty(value = "目标系统元素编号最小值")
	private Long delmRidMin;
	@ApiModelProperty(value = "来源系统元素实例编号")
	private Long sinstRid;
	@ApiModelProperty(value = "来源系统元素实例编号最大值")
	private Long sinstRidMax;
	@ApiModelProperty(value = "来源系统元素实例编号最小值")
	private Long sinstRidMin;
	@ApiModelProperty(value = "目标系统元素实例编号")
	private Long dinstRid;
	@ApiModelProperty(value = "目标系统元素实例编号最大值")
	private Long dinstRidMax;
	@ApiModelProperty(value = "目标系统元素实例编号最小值")
	private Long dinstRidMin;
	@ApiModelProperty(value = "顺序号")
	private Integer seq;
	@ApiModelProperty(value = "顺序号最大值")
	private Integer seqMax;
	@ApiModelProperty(value = "顺序号最小值")
	private Integer seqMin;
	@ApiModelProperty(value = "对应关系类型")
	private String type;
	@ApiModelProperty(value = "对应关系子类型")
	private String subType;
	@ApiModelProperty(value = "构造型")
	private String stereotype;
	@ApiModelProperty(value = "范围")
	private String scope;
	@ApiModelProperty(value = "方向")
	private String direction;
	@ApiModelProperty(value = "来源对应数量")
	private String srcMulti;
	@ApiModelProperty(value = "来源角色")
	private String srcRole;
	@ApiModelProperty(value = "来源角色类型")
	private String srcRoleType;
	@ApiModelProperty(value = "目标对应数量")
	private String destMulti;
	@ApiModelProperty(value = "目标角色")
	private String destRole;
	@ApiModelProperty(value = "目标角色类型")
	private String destRoleType;
	@ApiModelProperty(value = "属性对应关系")
	private String attrRelation;
	@ApiModelProperty(value = "记录状态")
	private Integer recordState;
	@ApiModelProperty(value = "记录状态最大值")
	private Integer recordStateMax;
	@ApiModelProperty(value = "记录状态最小值")
	private Integer recordStateMin;
	@ApiModelProperty(value = "创建用户代码")
	private String createUcode;
	@ApiModelProperty(value = "修改用户代码")
	private String modifyUcode;


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


	public Long getSprdRid(){
		return sprdRid;
	}
	public void setSprdRid(Long sprdRid) {
		this.sprdRid = sprdRid;
	}

	public Long getSprdRidMin(){
		return sprdRidMin;
	}
	public void setSprdRidMin(Long sprdRidMin) {
		this.sprdRidMin = sprdRidMin;
	}

	public Long getSprdRidMax(){
		return sprdRidMax;
	}
	public void setSprdRidMax(Long sprdRidMax) {
		this.sprdRidMax = sprdRidMax;
	}


	public Long getSelmRid(){
		return selmRid;
	}
	public void setSelmRid(Long selmRid) {
		this.selmRid = selmRid;
	}

	public Long getSelmRidMin(){
		return selmRidMin;
	}
	public void setSelmRidMin(Long selmRidMin) {
		this.selmRidMin = selmRidMin;
	}

	public Long getSelmRidMax(){
		return selmRidMax;
	}
	public void setSelmRidMax(Long selmRidMax) {
		this.selmRidMax = selmRidMax;
	}


	public Long getDprdRid(){
		return dprdRid;
	}
	public void setDprdRid(Long dprdRid) {
		this.dprdRid = dprdRid;
	}

	public Long getDprdRidMin(){
		return dprdRidMin;
	}
	public void setDprdRidMin(Long dprdRidMin) {
		this.dprdRidMin = dprdRidMin;
	}

	public Long getDprdRidMax(){
		return dprdRidMax;
	}
	public void setDprdRidMax(Long dprdRidMax) {
		this.dprdRidMax = dprdRidMax;
	}


	public Long getDelmRid(){
		return delmRid;
	}
	public void setDelmRid(Long delmRid) {
		this.delmRid = delmRid;
	}

	public Long getDelmRidMin(){
		return delmRidMin;
	}
	public void setDelmRidMin(Long delmRidMin) {
		this.delmRidMin = delmRidMin;
	}

	public Long getDelmRidMax(){
		return delmRidMax;
	}
	public void setDelmRidMax(Long delmRidMax) {
		this.delmRidMax = delmRidMax;
	}


	public Long getSinstRid(){
		return sinstRid;
	}
	public void setSinstRid(Long sinstRid) {
		this.sinstRid = sinstRid;
	}

	public Long getSinstRidMin(){
		return sinstRidMin;
	}
	public void setSinstRidMin(Long sinstRidMin) {
		this.sinstRidMin = sinstRidMin;
	}

	public Long getSinstRidMax(){
		return sinstRidMax;
	}
	public void setSinstRidMax(Long sinstRidMax) {
		this.sinstRidMax = sinstRidMax;
	}


	public Long getDinstRid(){
		return dinstRid;
	}
	public void setDinstRid(Long dinstRid) {
		this.dinstRid = dinstRid;
	}

	public Long getDinstRidMin(){
		return dinstRidMin;
	}
	public void setDinstRidMin(Long dinstRidMin) {
		this.dinstRidMin = dinstRidMin;
	}

	public Long getDinstRidMax(){
		return dinstRidMax;
	}
	public void setDinstRidMax(Long dinstRidMax) {
		this.dinstRidMax = dinstRidMax;
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


	public String getDirection(){
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}


	public String getSrcMulti(){
		return srcMulti;
	}
	public void setSrcMulti(String srcMulti) {
		this.srcMulti = srcMulti;
	}


	public String getSrcRole(){
		return srcRole;
	}
	public void setSrcRole(String srcRole) {
		this.srcRole = srcRole;
	}


	public String getSrcRoleType(){
		return srcRoleType;
	}
	public void setSrcRoleType(String srcRoleType) {
		this.srcRoleType = srcRoleType;
	}


	public String getDestMulti(){
		return destMulti;
	}
	public void setDestMulti(String destMulti) {
		this.destMulti = destMulti;
	}


	public String getDestRole(){
		return destRole;
	}
	public void setDestRole(String destRole) {
		this.destRole = destRole;
	}


	public String getDestRoleType(){
		return destRoleType;
	}
	public void setDestRoleType(String destRoleType) {
		this.destRoleType = destRoleType;
	}


	public String getAttrRelation(){
		return attrRelation;
	}
	public void setAttrRelation(String attrRelation) {
		this.attrRelation = attrRelation;
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


	public String getModifyUcode(){
		return modifyUcode;
	}
	public void setModifyUcode(String modifyUcode) {
		this.modifyUcode = modifyUcode;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
