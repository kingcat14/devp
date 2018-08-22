package net.aicoder.devp.business.publish.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
	import java.math.BigDecimal;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 部署容器
 * @author icode
 */
@ApiModel(value = "修改部署容器使用的DTO")
public class DevpSysOpsTaskDockerEditDto {


	/**租户编号*/
	@ApiModelProperty(value = "租户编号", required = false, notes = "[租户编号]")
	private Long tid;


	/**元素类型*/
	@ApiModelProperty(value = "元素类型", required = false, notes = "[元素类型]-SYS_OPS_TASK_DOCKER")
	private String etype;


	/**系统元素名称*/
	@ApiModelProperty(value = "系统元素名称", required = false, notes = "[系统元素名称]")
	private String name;


	/**系统元素代码*/
	@ApiModelProperty(value = "系统元素代码", required = false, notes = "[系统元素代码]")
	private String code;


	/**系统元素别名*/
	@ApiModelProperty(value = "系统元素别名", required = false, notes = "[系统元素别名]")
	private String alias;


	/**系统元素描述*/
	@ApiModelProperty(value = "系统元素描述", required = false, notes = "[系统元素描述]")
	private String description;


	/**记录状态*/
	@ApiModelProperty(value = "记录状态", required = false, notes = "[记录状态]-0-失效;1-生效;缺省为1")
	private Integer recordState;


	/**类型*/
	@ApiModelProperty(value = "类型", required = false, notes = "[类型]")
	private String type;


	/**子类型*/
	@ApiModelProperty(value = "子类型", required = false, notes = "[子类型]")
	private String subType;


	/**所在集群*/
	@ApiModelProperty(value = "所在集群", required = false, notes = "[所在集群]")
	private String group;


	/**命名空间*/
	@ApiModelProperty(value = "命名空间", required = false, notes = "[命名空间]")
	private String namespace;


	/**发布物分组*/
	@ApiModelProperty(value = "发布物分组", required = false, notes = "[发布物分组]")
	private String publishGroup;


	/**发布物*/
	@ApiModelProperty(value = "发布物", required = false, notes = "[发布物]")
	private String publishArtifact;


	/**版本标识*/
	@ApiModelProperty(value = "版本标识", required = false, notes = "[版本标识]")
	private String publishVersion;


	/**发布文件名*/
	@ApiModelProperty(value = "发布文件名", required = false, notes = "[发布文件名]-jar包、war包等")
	private String publishFile;


	/**实例个数*/
	@ApiModelProperty(value = "实例个数", required = false, notes = "[实例个数]")
	private Integer instancesNum;


	/**CPU*/
	@ApiModelProperty(value = "CPU", required = false, notes = "[CPU]-单位为核")
	private Integer cpu;


	/**内存(G)*/
	@ApiModelProperty(value = "内存(G)", required = false, notes = "[内存(G)]-单位为G")
	private Double memory;


	/**发布为服务*/
	@ApiModelProperty(value = "发布为服务", required = false, notes = "[发布为服务]-0-否；1-是")
	private Integer serviceable;


	/**负载策略*/
	@ApiModelProperty(value = "负载策略", required = false, notes = "[负载策略]-轮询/客户端会话")
	private String lbStrategy;


	/**访问类型*/
	@ApiModelProperty(value = "访问类型", required = false, notes = "[访问类型]-内部访问/外部访问")
	private String accessType;


	/**状态*/
	@ApiModelProperty(value = "状态", required = false, notes = "[状态]")
	private String status;


	/**备注*/
	@ApiModelProperty(value = "备注", required = false, notes = "[备注]")
	private String notes;


	/**产品编号*/
	@ApiModelProperty(value = "产品编号", required = false, notes = "[产品编号]")
	private Long prdRid;


	/**部署方案编号*/
	@ApiModelProperty(value = "部署方案编号", required = false, notes = "[部署方案编号]")
	private Long schemeRid;


	/**组件编号*/
	@ApiModelProperty(value = "组件编号", required = false, notes = "[组件编号]")
	private Long cmpRid;


	/**任务编号*/
	@ApiModelProperty(value = "任务编号", required = false, notes = "[任务编号]")
	private Long taskRid;


	/**顺序号*/
	@ApiModelProperty(value = "顺序号", required = false, notes = "[顺序号]")
	private Integer seq;


	/**创建用户代码*/
	@ApiModelProperty(value = "创建用户代码", required = false, notes = "[创建用户代码]")
	private String createUcode;


	/**创建用户姓名*/
	@ApiModelProperty(value = "创建用户姓名", required = false, notes = "[创建用户姓名]")
	private String createUname;


	/**修改用户代码*/
	@ApiModelProperty(value = "修改用户代码", required = false, notes = "[修改用户代码]")
	private String modifyUcode;


	/**修改用户姓名*/
	@ApiModelProperty(value = "修改用户姓名", required = false, notes = "[修改用户姓名]")
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


	public Integer getCpu(){
		return cpu;
	}
	public void setCpu(Integer cpu) {
		this.cpu = cpu;
	}


	public Double getMemory(){
	if(memory==null){
		return null;
	}
	BigDecimal b = new BigDecimal(memory);
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public void setMemory(Double memory) {
		this.memory = memory;
	}


	public Integer getServiceable(){
		return serviceable;
	}
	public void setServiceable(Integer serviceable) {
		this.serviceable = serviceable;
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


	public Long getSchemeRid(){
		return schemeRid;
	}
	public void setSchemeRid(Long schemeRid) {
		this.schemeRid = schemeRid;
	}


	public Long getCmpRid(){
		return cmpRid;
	}
	public void setCmpRid(Long cmpRid) {
		this.cmpRid = cmpRid;
	}


	public Long getTaskRid(){
		return taskRid;
	}
	public void setTaskRid(Long taskRid) {
		this.taskRid = taskRid;
	}


	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
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
