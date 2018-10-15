package net.aicoder.devp.business.publish.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
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
 * 部署容器访问参数
 * @author icode
 */
@Entity
@Table(appliesTo = "devp_sys_ops_docker_access", comment = "[部署容器访问参数]")
//@DynamicUpdate
//@DynamicInsert
public class DevpSysOpsDockerAccess extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ETYPE = "etype";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_RECORD_STATE = "recordState";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_NOTES = "notes";
	public static final String PROPERTY_SERVICES_PORT = "servicesPort";
	public static final String PROPERTY_DOCKER_PORT = "dockerPort";
	public static final String PROPERTY_PROTOCOL = "protocol";
	public static final String PROPERTY_AUTO_ASSIGN = "autoAssign";
	public static final String PROPERTY_HOST_PORT = "hostPort";
	public static final String PROPERTY_PRD_RID = "prdRid";
	public static final String PROPERTY_SCHEME_RID = "schemeRid";
	public static final String PROPERTY_CMP_RID = "cmpRid";
	public static final String PROPERTY_TASK_RID = "taskRid";
	public static final String PROPERTY_DOCKER_RID = "dockerRid";
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
    * [元素类型]-SYS_OPS_DOCKER_ACCESS
    */
    @Column(name = "etype", nullable = false, updatable = true)
	@Size(max = 255, message = "元素类型超长，最多255个字符")
	private String etype;

    /**
    * 系统元素名称
    * [系统元素名称]
    */
    @Column(name = "name", nullable = true, updatable = true)
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
    * [系统元素描述]-docker参数值/主机路径
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
    * 备注
    * [备注]
    */
    @Column(name = "notes", nullable = true, updatable = true)
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String notes;

    /**
    * 服务端口
    * [服务端口]
    */
    @Column(name = "services_port", nullable = false, updatable = true)
	private Integer servicesPort;

    /**
    * 服务端口
    * [服务端口]
    */
    @Column(name = "docker_port", nullable = false, updatable = true)
	private Integer dockerPort;

    /**
    * 协议
    * [协议]-TCP/UDP
    */
    @Column(name = "protocol", nullable = true, updatable = true)
	@Size(max = 255, message = "协议超长，最多255个字符")
	private String protocol;

    /**
    * 自动分配
    * [自动分配]-节点端口是否自动分配
    */
    @Column(name = "auto_assign", nullable = true, updatable = true)
	private Integer autoAssign;

    /**
    * 自动分配
    * [自动分配]-主机节点端口
    */
    @Column(name = "host_port", nullable = true, updatable = true)
	private Integer hostPort;

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
    * 容器编号
    * [容器编号]
    */
    @Column(name = "docker_rid", nullable = false, updatable = true)
	private Long dockerRid;

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

	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getServicesPort(){
		return servicesPort;
	}
	public void setServicesPort(Integer servicesPort) {
		this.servicesPort = servicesPort;
	}

	public Integer getDockerPort(){
		return dockerPort;
	}
	public void setDockerPort(Integer dockerPort) {
		this.dockerPort = dockerPort;
	}

	public String getProtocol(){
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Integer getAutoAssign(){
		return autoAssign;
	}
	public void setAutoAssign(Integer autoAssign) {
		this.autoAssign = autoAssign;
	}

	public Integer getHostPort(){
		return hostPort;
	}
	public void setHostPort(Integer hostPort) {
		this.hostPort = hostPort;
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

	public Long getDockerRid(){
		return dockerRid;
	}
	public void setDockerRid(Long dockerRid) {
		this.dockerRid = dockerRid;
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

