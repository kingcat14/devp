package net.aicoder.devp.business.deploy.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询系统元素间关系使用的DTO")
public class DevpSysDpyCmpRefCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户编号", notes = "[租户编号]")
	private Long tid;
	@ApiModelProperty(value = "元素类型", notes = "[元素类型]-SYS_DPY_CMP_REF // 部署组件关联元素")
	private String etype;
	@ApiModelProperty(value = "对应关系代码", notes = "[对应关系代码]")
	private String code;
	@ApiModelProperty(value = "对应关系名称", notes = "[对应关系名称]")
	private String name;
	@ApiModelProperty(value = "对应关系别名", notes = "[对应关系别名]")
	private String alias;
	@ApiModelProperty(value = "对应关系描述", notes = "[对应关系描述]")
	private String description;
	@ApiModelProperty(value = "记录状态", notes = "[记录状态]-0-失效;1-生效;缺省为1")
	private Integer recordState;
	@ApiModelProperty(value = "记录状态最大值")
	private Integer recordStateMax;
	@ApiModelProperty(value = "记录状态最小值")
	private Integer recordStateMin;
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
	@ApiModelProperty(value = "组件编号", notes = "[组件编号]")
	private Long cmpRid;
	@ApiModelProperty(value = "组件编号最大值")
	private Long cmpRidMax;
	@ApiModelProperty(value = "组件编号最小值")
	private Long cmpRidMin;
	@ApiModelProperty(value = "关联元素类型", notes = "[关联元素类型]")
	private Long refEtype;
	@ApiModelProperty(value = "关联元素类型最大值")
	private Long refEtypeMax;
	@ApiModelProperty(value = "关联元素类型最小值")
	private Long refEtypeMin;
	@ApiModelProperty(value = "关联产品编号", notes = "[关联产品编号]")
	private Long refPrdRid;
	@ApiModelProperty(value = "关联产品编号最大值")
	private Long refPrdRidMax;
	@ApiModelProperty(value = "关联产品编号最小值")
	private Long refPrdRidMin;
	@ApiModelProperty(value = "关联元素编号", notes = "[关联元素编号]")
	private Long refElmRid;
	@ApiModelProperty(value = "关联元素编号最大值")
	private Long refElmRidMax;
	@ApiModelProperty(value = "关联元素编号最小值")
	private Long refElmRidMin;
	@ApiModelProperty(value = "关联元素实例编号", notes = "[关联元素实例编号]-缺省值为0")
	private Long refInstRid;
	@ApiModelProperty(value = "关联元素实例编号最大值")
	private Long refInstRidMax;
	@ApiModelProperty(value = "关联元素实例编号最小值")
	private Long refInstRidMin;
	@ApiModelProperty(value = "顺序号", notes = "[顺序号]")
	private Integer seq;
	@ApiModelProperty(value = "顺序号最大值")
	private Integer seqMax;
	@ApiModelProperty(value = "顺序号最小值")
	private Integer seqMin;
	@ApiModelProperty(value = "类型", notes = "[类型]-关联类型：部署到、连接、调用")
	private String type;
	@ApiModelProperty(value = "子类型", notes = "[子类型]-连接时：双向[-o)-]，请求[)-],提供[-o]")
	private String subType;
	@ApiModelProperty(value = "构造型", notes = "[构造型]-(保留)")
	private String stereotype;
	@ApiModelProperty(value = "范围", notes = "[范围]-(保留)")
	private String scope;
	@ApiModelProperty(value = "方向", notes = "[方向]-(保留)")
	private String direction;
	@ApiModelProperty(value = "来源对应数量", notes = "[来源对应数量]-0/1/ * /0..1/0..* /1..*")
	private String srcMulti;
	@ApiModelProperty(value = "来源角色", notes = "[来源角色]")
	private String srcRole;
	@ApiModelProperty(value = "来源角色类型", notes = "[来源角色类型]")
	private String srcRoleType;
	@ApiModelProperty(value = "目标对应数量", notes = "[目标对应数量]-0/1/ * /0..1/0..* /1..*")
	private String destMulti;
	@ApiModelProperty(value = "目标角色", notes = "[目标角色]")
	private String destRole;
	@ApiModelProperty(value = "目标角色类型", notes = "[目标角色类型]")
	private String destRoleType;
	@ApiModelProperty(value = "属性对应关系", notes = "[属性对应关系]-(保留)")
	private String attrRelation;
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


	public Long getCmpRid(){
		return cmpRid;
	}
	public void setCmpRid(Long cmpRid) {
		this.cmpRid = cmpRid;
	}

	public Long getCmpRidMin(){
		return cmpRidMin;
	}
	public void setCmpRidMin(Long cmpRidMin) {
		this.cmpRidMin = cmpRidMin;
	}

	public Long getCmpRidMax(){
		return cmpRidMax;
	}
	public void setCmpRidMax(Long cmpRidMax) {
		this.cmpRidMax = cmpRidMax;
	}


	public Long getRefEtype(){
		return refEtype;
	}
	public void setRefEtype(Long refEtype) {
		this.refEtype = refEtype;
	}

	public Long getRefEtypeMin(){
		return refEtypeMin;
	}
	public void setRefEtypeMin(Long refEtypeMin) {
		this.refEtypeMin = refEtypeMin;
	}

	public Long getRefEtypeMax(){
		return refEtypeMax;
	}
	public void setRefEtypeMax(Long refEtypeMax) {
		this.refEtypeMax = refEtypeMax;
	}


	public Long getRefPrdRid(){
		return refPrdRid;
	}
	public void setRefPrdRid(Long refPrdRid) {
		this.refPrdRid = refPrdRid;
	}

	public Long getRefPrdRidMin(){
		return refPrdRidMin;
	}
	public void setRefPrdRidMin(Long refPrdRidMin) {
		this.refPrdRidMin = refPrdRidMin;
	}

	public Long getRefPrdRidMax(){
		return refPrdRidMax;
	}
	public void setRefPrdRidMax(Long refPrdRidMax) {
		this.refPrdRidMax = refPrdRidMax;
	}


	public Long getRefElmRid(){
		return refElmRid;
	}
	public void setRefElmRid(Long refElmRid) {
		this.refElmRid = refElmRid;
	}

	public Long getRefElmRidMin(){
		return refElmRidMin;
	}
	public void setRefElmRidMin(Long refElmRidMin) {
		this.refElmRidMin = refElmRidMin;
	}

	public Long getRefElmRidMax(){
		return refElmRidMax;
	}
	public void setRefElmRidMax(Long refElmRidMax) {
		this.refElmRidMax = refElmRidMax;
	}


	public Long getRefInstRid(){
		return refInstRid;
	}
	public void setRefInstRid(Long refInstRid) {
		this.refInstRid = refInstRid;
	}

	public Long getRefInstRidMin(){
		return refInstRidMin;
	}
	public void setRefInstRidMin(Long refInstRidMin) {
		this.refInstRidMin = refInstRidMin;
	}

	public Long getRefInstRidMax(){
		return refInstRidMax;
	}
	public void setRefInstRidMax(Long refInstRidMax) {
		this.refInstRidMax = refInstRidMax;
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
