package net.aicoder.devp.business.publish.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询编译设置使用的DTO")
public class DevpSysOpsTaskCompileCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户编号", notes = "[租户编号]")
	private Long tid;
	@ApiModelProperty(value = "元素类型", notes = "[元素类型]-SYS_OPS_TASK_CONFIG // 生成配置任务")
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
	@ApiModelProperty(value = "采用相同代码仓库", notes = "[采用相同代码仓库]-代码仓库与产品部署方案配置相同")
	private Integer cmSameas;
	@ApiModelProperty(value = "采用相同代码仓库最大值")
	private Integer cmSameasMax;
	@ApiModelProperty(value = "采用相同代码仓库最小值")
	private Integer cmSameasMin;
	@ApiModelProperty(value = "代码仓库类型", notes = "[代码仓库类型]-svn/github/gitlib")
	private String cmType;
	@ApiModelProperty(value = "仓库地址", notes = "[仓库地址]")
	private String cmRepository;
	@ApiModelProperty(value = "分支标识", notes = "[分支标识]")
	private String cmTag;
	@ApiModelProperty(value = "用户名", notes = "[用户名]")
	private String cmUser;
	@ApiModelProperty(value = "密码", notes = "[密码]")
	private String cmPassword;
	@ApiModelProperty(value = "子目录", notes = "[子目录]")
	private String subDir;
	@ApiModelProperty(value = "基线标识", notes = "[基线标识]")
	private String baseline;
	@ApiModelProperty(value = "代码路径", notes = "[代码路径]")
	private String svnCodeUrl;
	@ApiModelProperty(value = "构建工具", notes = "[构建工具]")
	private String buildTools;
	@ApiModelProperty(value = "执行环境版本", notes = "[执行环境版本]-如：JDK版本")
	private String envVersion;
	@ApiModelProperty(value = "工具版本", notes = "[工具版本]-如：Maven版本")
	private String toolVersion;
	@ApiModelProperty(value = "构建前操作", notes = "[构建前操作]")
	private String preAction;
	@ApiModelProperty(value = "构建操作", notes = "[构建操作]-依据不同的工具带出缺省的执行语句")
	private String buildAction;
	@ApiModelProperty(value = "构建后操作", notes = "[构建后操作]")
	private String postAction;
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


	public Integer getCmSameas(){
		return cmSameas;
	}
	public void setCmSameas(Integer cmSameas) {
		this.cmSameas = cmSameas;
	}

	public Integer getCmSameasMin(){
		return cmSameasMin;
	}
	public void setCmSameasMin(Integer cmSameasMin) {
		this.cmSameasMin = cmSameasMin;
	}

	public Integer getCmSameasMax(){
		return cmSameasMax;
	}
	public void setCmSameasMax(Integer cmSameasMax) {
		this.cmSameasMax = cmSameasMax;
	}


	public String getCmType(){
		return cmType;
	}
	public void setCmType(String cmType) {
		this.cmType = cmType;
	}


	public String getCmRepository(){
		return cmRepository;
	}
	public void setCmRepository(String cmRepository) {
		this.cmRepository = cmRepository;
	}


	public String getCmTag(){
		return cmTag;
	}
	public void setCmTag(String cmTag) {
		this.cmTag = cmTag;
	}


	public String getCmUser(){
		return cmUser;
	}
	public void setCmUser(String cmUser) {
		this.cmUser = cmUser;
	}


	public String getCmPassword(){
		return cmPassword;
	}
	public void setCmPassword(String cmPassword) {
		this.cmPassword = cmPassword;
	}


	public String getSubDir(){
		return subDir;
	}
	public void setSubDir(String subDir) {
		this.subDir = subDir;
	}


	public String getBaseline(){
		return baseline;
	}
	public void setBaseline(String baseline) {
		this.baseline = baseline;
	}


	public String getSvnCodeUrl(){
		return svnCodeUrl;
	}
	public void setSvnCodeUrl(String svnCodeUrl) {
		this.svnCodeUrl = svnCodeUrl;
	}


	public String getBuildTools(){
		return buildTools;
	}
	public void setBuildTools(String buildTools) {
		this.buildTools = buildTools;
	}


	public String getEnvVersion(){
		return envVersion;
	}
	public void setEnvVersion(String envVersion) {
		this.envVersion = envVersion;
	}


	public String getToolVersion(){
		return toolVersion;
	}
	public void setToolVersion(String toolVersion) {
		this.toolVersion = toolVersion;
	}


	public String getPreAction(){
		return preAction;
	}
	public void setPreAction(String preAction) {
		this.preAction = preAction;
	}


	public String getBuildAction(){
		return buildAction;
	}
	public void setBuildAction(String buildAction) {
		this.buildAction = buildAction;
	}


	public String getPostAction(){
		return postAction;
	}
	public void setPostAction(String postAction) {
		this.postAction = postAction;
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
