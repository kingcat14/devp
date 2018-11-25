package net.aicoder.devp.business.publish.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询流水线执行节点使用的DTO")
public class DevpSysOpsPipeNodeCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户编号", notes = "[租户编号]")
	private Long tid;
	@ApiModelProperty(value = "元素类型", notes = "[元素类型]-SYS_OPS_PIPE_NODE // 流水线")
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
	@ApiModelProperty(value = "类型", notes = "[类型]-子流水线/控制节点/组件任务")
	private String type;
	@ApiModelProperty(value = "子类型", notes = "[子类型]")
	private String subType;
	@ApiModelProperty(value = "执行方式", notes = "[执行方式]-手工执行/自动执行")
	private String execModel;
	@ApiModelProperty(value = "执行顺序", notes = "[执行顺序]-串行/并行")
	private String execSeq;
	@ApiModelProperty(value = "返回码", notes = "[返回码]-0:成功，错误代码")
	private String returnCode;
	@ApiModelProperty(value = "执行状态", notes = "[执行状态]")
	private String execStatus;
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
	@ApiModelProperty(value = "流水线编号", notes = "[流水线编号]")
	private Long pipelineRid;
	@ApiModelProperty(value = "流水线编号最大值")
	private Long pipelineRidMax;
	@ApiModelProperty(value = "流水线编号最小值")
	private Long pipelineRidMin;
	@ApiModelProperty(value = "组件编号", notes = "[组件编号]")
	private Long cmpRid;
	@ApiModelProperty(value = "组件编号最大值")
	private Long cmpRidMax;
	@ApiModelProperty(value = "组件编号最小值")
	private Long cmpRidMin;
	@ApiModelProperty(value = "任务编号", notes = "[任务编号]")
	private Long taskRid;
	@ApiModelProperty(value = "任务编号最大值")
	private Long taskRidMax;
	@ApiModelProperty(value = "任务编号最小值")
	private Long taskRidMin;
	@ApiModelProperty(value = "父节点编号", notes = "[父节点编号]")
	private Long parentRid;
	@ApiModelProperty(value = "父节点编号最大值")
	private Long parentRidMax;
	@ApiModelProperty(value = "父节点编号最小值")
	private Long parentRidMin;
	@ApiModelProperty(value = "顺序号", notes = "[顺序号]")
	private Integer seq;
	@ApiModelProperty(value = "顺序号最大值")
	private Integer seqMax;
	@ApiModelProperty(value = "顺序号最小值")
	private Integer seqMin;
	@ApiModelProperty(value = "缺省选中流水线", notes = "[缺省选中流水线]-部署方案中相应类型，为1时表示为当前缺省执行的流水线")
	private Integer defaultPipeline;
	@ApiModelProperty(value = "缺省选中流水线最大值")
	private Integer defaultPipelineMax;
	@ApiModelProperty(value = "缺省选中流水线最小值")
	private Integer defaultPipelineMin;
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


	public String getExecModel(){
		return execModel;
	}
	public void setExecModel(String execModel) {
		this.execModel = execModel;
	}


	public String getExecSeq(){
		return execSeq;
	}
	public void setExecSeq(String execSeq) {
		this.execSeq = execSeq;
	}


	public String getReturnCode(){
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}


	public String getExecStatus(){
		return execStatus;
	}
	public void setExecStatus(String execStatus) {
		this.execStatus = execStatus;
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


	public Long getPipelineRid(){
		return pipelineRid;
	}
	public void setPipelineRid(Long pipelineRid) {
		this.pipelineRid = pipelineRid;
	}

	public Long getPipelineRidMin(){
		return pipelineRidMin;
	}
	public void setPipelineRidMin(Long pipelineRidMin) {
		this.pipelineRidMin = pipelineRidMin;
	}

	public Long getPipelineRidMax(){
		return pipelineRidMax;
	}
	public void setPipelineRidMax(Long pipelineRidMax) {
		this.pipelineRidMax = pipelineRidMax;
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


	public Long getTaskRid(){
		return taskRid;
	}
	public void setTaskRid(Long taskRid) {
		this.taskRid = taskRid;
	}

	public Long getTaskRidMin(){
		return taskRidMin;
	}
	public void setTaskRidMin(Long taskRidMin) {
		this.taskRidMin = taskRidMin;
	}

	public Long getTaskRidMax(){
		return taskRidMax;
	}
	public void setTaskRidMax(Long taskRidMax) {
		this.taskRidMax = taskRidMax;
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


	public Integer getDefaultPipeline(){
		return defaultPipeline;
	}
	public void setDefaultPipeline(Integer defaultPipeline) {
		this.defaultPipeline = defaultPipeline;
	}

	public Integer getDefaultPipelineMin(){
		return defaultPipelineMin;
	}
	public void setDefaultPipelineMin(Integer defaultPipelineMin) {
		this.defaultPipelineMin = defaultPipelineMin;
	}

	public Integer getDefaultPipelineMax(){
		return defaultPipelineMax;
	}
	public void setDefaultPipelineMax(Integer defaultPipelineMax) {
		this.defaultPipelineMax = defaultPipelineMax;
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
