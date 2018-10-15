package net.aicoder.devp.business.publish.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotEmpty;
import com.yunkang.saas.common.framework.eo.GenericBaseEntity;



/**
 * 部署容器
 * @author icode
 */
@Entity
@Table(appliesTo = "devp_sys_ops_task_docker", comment = "[部署容器]")
//@DynamicUpdate
//@DynamicInsert
public class DevpSysOpsTaskDocker extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ETYPE = "etype";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_RECORD_STATE = "recordState";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_SUB_TYPE = "subType";
	public static final String PROPERTY_GROUP = "group";
	public static final String PROPERTY_NAMESPACE = "namespace";
	public static final String PROPERTY_PUBLISH_GROUP = "publishGroup";
	public static final String PROPERTY_PUBLISH_ARTIFACT = "publishArtifact";
	public static final String PROPERTY_PUBLISH_VERSION = "publishVersion";
	public static final String PROPERTY_PUBLISH_FILE = "publishFile";
	public static final String PROPERTY_INSTANCES_NUM = "instancesNum";
	public static final String PROPERTY_CPU = "cpu";
	public static final String PROPERTY_MEMORY = "memory";
	public static final String PROPERTY_SERVICEABLE = "serviceable";
	public static final String PROPERTY_LB_STRATEGY = "lbStrategy";
	public static final String PROPERTY_ACCESS_TYPE = "accessType";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_NOTES = "notes";
	public static final String PROPERTY_PRD_RID = "prdRid";
	public static final String PROPERTY_SCHEME_RID = "schemeRid";
	public static final String PROPERTY_CMP_RID = "cmpRid";
	public static final String PROPERTY_TASK_RID = "taskRid";
	public static final String PROPERTY_SEQ = "seq";
	public static final String PROPERTY_CREATE_UCODE = "createUcode";
	public static final String PROPERTY_CREATE_UNAME = "createUname";
	public static final String PROPERTY_MODIFY_UCODE = "modifyUcode";
	public static final String PROPERTY_MODIFY_UNAME = "modifyUname";


    @Id
    @Column(name = "rid")
    private Long id;


    /**
    * 租户编号
    * [租户编号]
    */
    @Column(name = "tid", nullable = false, updatable = true)
	private Long tid;

    /**
    * 元素类型
    * [元素类型]-SYS_OPS_TASK_DOCKER
    */
    @Column(name = "etype", nullable = false, updatable = true)
	@Size(max = 255, message = "元素类型超长，最多255个字符")
	private String etype;

    /**
    * 系统元素名称
    * [系统元素名称]
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "系统元素名称超长，最多255个字符")
	private String name;

    /**
    * 系统元素代码
    * [系统元素代码]
    */
    @Column(name = "code", nullable = true, updatable = true)
	@Size(max = 255, message = "系统元素代码超长，最多255个字符")
	private String code;

    /**
    * 系统元素别名
    * [系统元素别名]
    */
    @Column(name = "alias", nullable = true, updatable = true)
	@Size(max = 255, message = "系统元素别名超长，最多255个字符")
	private String alias;

    /**
    * 系统元素描述
    * [系统元素描述]
    */
    @Column(name = "description", nullable = true, updatable = true)
	@Size(max = 255, message = "系统元素描述超长，最多255个字符")
	private String description;

    /**
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    @Column(name = "record_state", nullable = true, updatable = true)
	private Integer recordState;

    /**
    * 类型
    * [类型]
    */
    @Column(name = "type", nullable = true, updatable = true)
	@Size(max = 255, message = "类型超长，最多255个字符")
	private String type;

    /**
    * 子类型
    * [子类型]
    */
    @Column(name = "sub_type", nullable = true, updatable = true)
	@Size(max = 255, message = "子类型超长，最多255个字符")
	private String subType;

    /**
    * 所在集群
    * [所在集群]
    */
    @Column(name = "group", nullable = false, updatable = true)
	@Size(max = 255, message = "所在集群超长，最多255个字符")
	private String group;

    /**
    * 命名空间
    * [命名空间]
    */
    @Column(name = "namespace", nullable = false, updatable = true)
	@Size(max = 255, message = "命名空间超长，最多255个字符")
	private String namespace;

    /**
    * 发布物分组
    * [发布物分组]
    */
    @Column(name = "publish_group", nullable = true, updatable = true)
	@Size(max = 255, message = "发布物分组超长，最多255个字符")
	private String publishGroup;

    /**
    * 发布物
    * [发布物]
    */
    @Column(name = "publish_artifact", nullable = false, updatable = true)
	@Size(max = 255, message = "发布物超长，最多255个字符")
	private String publishArtifact;

    /**
    * 版本标识
    * [版本标识]
    */
    @Column(name = "publish_version", nullable = true, updatable = true)
	@Size(max = 255, message = "版本标识超长，最多255个字符")
	private String publishVersion;

    /**
    * 发布文件名
    * [发布文件名]-jar包、war包等
    */
    @Column(name = "publish_file", nullable = true, updatable = true)
	@Size(max = 255, message = "发布文件名超长，最多255个字符")
	private String publishFile;

    /**
    * 实例个数
    * [实例个数]
    */
    @Column(name = "instances_num", nullable = true, updatable = true)
	private Integer instancesNum;

    /**
    * CPU
    * [CPU]-单位为核
    */
    @Column(name = "cpu", nullable = true, updatable = true)
	private Integer cpu;

    /**
    * 内存(G)
    * [内存(G)]-单位为G
    */
    @Column(name = "memory", nullable = true, updatable = true)
	private Double memory;

    /**
    * 发布为服务
    * [发布为服务]-0-否；1-是
    */
    @Column(name = "serviceable", nullable = true, updatable = true)
	private Integer serviceable;

    /**
    * 负载策略
    * [负载策略]-轮询/客户端会话
    */
    @Column(name = "lb_strategy", nullable = true, updatable = true)
	@Size(max = 255, message = "负载策略超长，最多255个字符")
	private String lbStrategy;

    /**
    * 访问类型
    * [访问类型]-内部访问/外部访问
    */
    @Column(name = "access_type", nullable = true, updatable = true)
	@Size(max = 255, message = "访问类型超长，最多255个字符")
	private String accessType;

    /**
    * 状态
    * [状态]
    */
    @Column(name = "status", nullable = true, updatable = true)
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;

    /**
    * 备注
    * [备注]
    */
    @Column(name = "notes", nullable = true, updatable = true)
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String notes;

    /**
    * 产品编号
    * [产品编号]
    */
    @Column(name = "prd_rid", nullable = false, updatable = true)
	private Long prdRid;

    /**
    * 部署方案编号
    * [部署方案编号]
    */
    @Column(name = "scheme_rid", nullable = false, updatable = true)
	private Long schemeRid;

    /**
    * 组件编号
    * [组件编号]
    */
    @Column(name = "cmp_rid", nullable = false, updatable = true)
	private Long cmpRid;

    /**
    * 任务编号
    * [任务编号]
    */
    @Column(name = "task_rid", nullable = false, updatable = true)
	private Long taskRid;

    /**
    * 顺序号
    * [顺序号]
    */
    @Column(name = "seq", nullable = true, updatable = true)
	private Integer seq;

    /**
    * 创建用户代码
    * [创建用户代码]
    */
    @Column(name = "create_ucode", nullable = true, updatable = true)
	@Size(max = 255, message = "创建用户代码超长，最多255个字符")
	private String createUcode;

    /**
    * 创建用户姓名
    * [创建用户姓名]
    */
    @Column(name = "create_uname", nullable = true, updatable = true)
	@Size(max = 255, message = "创建用户姓名超长，最多255个字符")
	private String createUname;

    /**
    * 修改用户代码
    * [修改用户代码]
    */
    @Column(name = "modify_ucode", nullable = true, updatable = true)
	@Size(max = 255, message = "修改用户代码超长，最多255个字符")
	private String modifyUcode;

    /**
    * 修改用户姓名
    * [修改用户姓名]
    */
    @Column(name = "modify_uname", nullable = true, updatable = true)
	@Size(max = 255, message = "修改用户姓名超长，最多255个字符")
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


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

