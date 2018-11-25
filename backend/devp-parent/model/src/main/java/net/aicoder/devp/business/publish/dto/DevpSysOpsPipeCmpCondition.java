package net.aicoder.devp.business.publish.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询产品运维流水线对应的组件使用的DTO")
public class DevpSysOpsPipeCmpCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户编号", notes = "[租户编号]")
	private Long tid;
	@ApiModelProperty(value = "元素类型", notes = "[元素类型]-SYS_OPS_PIPE_CMP")
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
	@ApiModelProperty(value = "是否更新", notes = "[是否更新]-0-不更新；1-更新")
	private Integer updateFlag;
	@ApiModelProperty(value = "是否更新最大值")
	private Integer updateFlagMax;
	@ApiModelProperty(value = "是否更新最小值")
	private Integer updateFlagMin;
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


	public Integer getUpdateFlag(){
		return updateFlag;
	}
	public void setUpdateFlag(Integer updateFlag) {
		this.updateFlag = updateFlag;
	}

	public Integer getUpdateFlagMin(){
		return updateFlagMin;
	}
	public void setUpdateFlagMin(Integer updateFlagMin) {
		this.updateFlagMin = updateFlagMin;
	}

	public Integer getUpdateFlagMax(){
		return updateFlagMax;
	}
	public void setUpdateFlagMax(Integer updateFlagMax) {
		this.updateFlagMax = updateFlagMax;
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
