package net.aicoder.devp.business.publish.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询部署容器使用的DTO")
public class DevpSysOpsTaskDockerCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户编号", notes = "[租户编号]")
	private Long tid;
	@ApiModelProperty(value = "元素类型", notes = "[元素类型]-SYS_OPS_TASK_DOCKER")
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
	@ApiModelProperty(value = "所在集群", notes = "[所在集群]")
	private String group;
	@ApiModelProperty(value = "命名空间", notes = "[命名空间]")
	private String namespace;
	@ApiModelProperty(value = "发布物分组", notes = "[发布物分组]")
	private String publishGroup;
	@ApiModelProperty(value = "发布物", notes = "[发布物]")
	private String publishArtifact;
	@ApiModelProperty(value = "版本标识", notes = "[版本标识]")
	private String publishVersion;
	@ApiModelProperty(value = "发布文件名", notes = "[发布文件名]-jar包、war包等")
	private String publishFile;
	@ApiModelProperty(value = "实例个数", notes = "[实例个数]")
	private Integer instancesNum;
	@ApiModelProperty(value = "实例个数最大值")
	private Integer instancesNumMax;
	@ApiModelProperty(value = "实例个数最小值")
	private Integer instancesNumMin;
	@ApiModelProperty(value = "CPU", notes = "[CPU]-单位为核")
	private Integer cpu;
	@ApiModelProperty(value = "CPU最大值")
	private Integer cpuMax;
	@ApiModelProperty(value = "CPU最小值")
	private Integer cpuMin;
	@ApiModelProperty(value = "内存(G)", notes = "[内存(G)]-单位为G")
	private Double memory;
	@ApiModelProperty(value = "内存(G)最大值")
	private Double memoryMax;
	@ApiModelProperty(value = "内存(G)最小值")
	private Double memoryMin;
	@ApiModelProperty(value = "发布为服务", notes = "[发布为服务]-0-否；1-是")
	private Integer serviceable;
	@ApiModelProperty(value = "发布为服务最大值")
	private Integer serviceableMax;
	@ApiModelProperty(value = "发布为服务最小值")
	private Integer serviceableMin;
	@ApiModelProperty(value = "负载策略", notes = "[负载策略]-轮询/客户端会话")
	private String lbStrategy;
	@ApiModelProperty(value = "访问类型", notes = "[访问类型]-内部访问/外部访问")
	private String accessType;
	@ApiModelProperty(value = "状态", notes = "[状态]")
	private String status;
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
	@ApiModelProperty(value = "顺序号", notes = "[顺序号]")
	private Integer seq;
	@ApiModelProperty(value = "顺序号最大值")
	private Integer seqMax;
	@ApiModelProperty(value = "顺序号最小值")
	private Integer seqMin;
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


	public String getGroup(){
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}


	public String getNamespace(){
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}


	public String getPublishGroup(){
		return publishGroup;
	}
	public void setPublishGroup(String publishGroup) {
		this.publishGroup = publishGroup;
	}


	public String getPublishArtifact(){
		return publishArtifact;
	}
	public void setPublishArtifact(String publishArtifact) {
		this.publishArtifact = publishArtifact;
	}


	public String getPublishVersion(){
		return publishVersion;
	}
	public void setPublishVersion(String publishVersion) {
		this.publishVersion = publishVersion;
	}


	public String getPublishFile(){
		return publishFile;
	}
	public void setPublishFile(String publishFile) {
		this.publishFile = publishFile;
	}


	public Integer getInstancesNum(){
		return instancesNum;
	}
	public void setInstancesNum(Integer instancesNum) {
		this.instancesNum = instancesNum;
	}

	public Integer getInstancesNumMin(){
		return instancesNumMin;
	}
	public void setInstancesNumMin(Integer instancesNumMin) {
		this.instancesNumMin = instancesNumMin;
	}

	public Integer getInstancesNumMax(){
		return instancesNumMax;
	}
	public void setInstancesNumMax(Integer instancesNumMax) {
		this.instancesNumMax = instancesNumMax;
	}


	public Integer getCpu(){
		return cpu;
	}
	public void setCpu(Integer cpu) {
		this.cpu = cpu;
	}

	public Integer getCpuMin(){
		return cpuMin;
	}
	public void setCpuMin(Integer cpuMin) {
		this.cpuMin = cpuMin;
	}

	public Integer getCpuMax(){
		return cpuMax;
	}
	public void setCpuMax(Integer cpuMax) {
		this.cpuMax = cpuMax;
	}


	public Double getMemory(){
		return memory;
	}
	public void setMemory(Double memory) {
		this.memory = memory;
	}

	public Double getMemoryMin(){
		return memoryMin;
	}
	public void setMemoryMin(Double memoryMin) {
		this.memoryMin = memoryMin;
	}

	public Double getMemoryMax(){
		return memoryMax;
	}
	public void setMemoryMax(Double memoryMax) {
		this.memoryMax = memoryMax;
	}


	public Integer getServiceable(){
		return serviceable;
	}
	public void setServiceable(Integer serviceable) {
		this.serviceable = serviceable;
	}

	public Integer getServiceableMin(){
		return serviceableMin;
	}
	public void setServiceableMin(Integer serviceableMin) {
		this.serviceableMin = serviceableMin;
	}

	public Integer getServiceableMax(){
		return serviceableMax;
	}
	public void setServiceableMax(Integer serviceableMax) {
		this.serviceableMax = serviceableMax;
	}


	public String getLbStrategy(){
		return lbStrategy;
	}
	public void setLbStrategy(String lbStrategy) {
		this.lbStrategy = lbStrategy;
	}


	public String getAccessType(){
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
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
