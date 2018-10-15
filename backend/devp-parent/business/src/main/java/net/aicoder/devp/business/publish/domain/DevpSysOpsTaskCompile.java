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
 * 编译设置
 * @author icode
 */
@Entity
@Table(appliesTo = "devp_sys_ops_task_compile", comment = "[编译设置]")
//@DynamicUpdate
//@DynamicInsert
public class DevpSysOpsTaskCompile extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ETYPE = "etype";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_RECORD_STATE = "recordState";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_SUB_TYPE = "subType";
	public static final String PROPERTY_CM_SAMEAS = "cmSameas";
	public static final String PROPERTY_CM_TYPE = "cmType";
	public static final String PROPERTY_CM_REPOSITORY = "cmRepository";
	public static final String PROPERTY_CM_TAG = "cmTag";
	public static final String PROPERTY_CM_USER = "cmUser";
	public static final String PROPERTY_CM_PASSWORD = "cmPassword";
	public static final String PROPERTY_SUB_DIR = "subDir";
	public static final String PROPERTY_BASELINE = "baseline";
	public static final String PROPERTY_SVN_CODE_URL = "svnCodeUrl";
	public static final String PROPERTY_BUILD_TOOLS = "buildTools";
	public static final String PROPERTY_ENV_VERSION = "envVersion";
	public static final String PROPERTY_TOOL_VERSION = "toolVersion";
	public static final String PROPERTY_PRE_ACTION = "preAction";
	public static final String PROPERTY_BUILD_ACTION = "buildAction";
	public static final String PROPERTY_POST_ACTION = "postAction";
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
    * [元素类型]-SYS_OPS_TASK_CONFIG // 生成配置任务
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
    * 采用相同代码仓库
    * [采用相同代码仓库]-代码仓库与产品部署方案配置相同
    */
    @Column(name = "cm_sameas", nullable = true, updatable = true)
	private Integer cmSameas;

    /**
    * 代码仓库类型
    * [代码仓库类型]-svn/github/gitlib
    */
    @Column(name = "cm_type", nullable = true, updatable = true)
	@Size(max = 255, message = "代码仓库类型超长，最多255个字符")
	private String cmType;

    /**
    * 仓库地址
    * [仓库地址]
    */
    @Column(name = "cm_repository", nullable = true, updatable = true)
	@Size(max = 255, message = "仓库地址超长，最多255个字符")
	private String cmRepository;

    /**
    * 分支标识
    * [分支标识]
    */
    @Column(name = "cm_tag", nullable = true, updatable = true)
	@Size(max = 255, message = "分支标识超长，最多255个字符")
	private String cmTag;

    /**
    * 用户名
    * [用户名]
    */
    @Column(name = "cm_user", nullable = true, updatable = true)
	@Size(max = 255, message = "用户名超长，最多255个字符")
	private String cmUser;

    /**
    * 密码
    * [密码]
    */
    @Column(name = "cm_password", nullable = true, updatable = true)
	@Size(max = 255, message = "密码超长，最多255个字符")
	private String cmPassword;

    /**
    * 子目录
    * [子目录]
    */
    @Column(name = "sub_dir", nullable = true, updatable = true)
	@Size(max = 255, message = "子目录超长，最多255个字符")
	private String subDir;

    /**
    * 基线标识
    * [基线标识]
    */
    @Column(name = "baseline", nullable = true, updatable = true)
	@Size(max = 255, message = "基线标识超长，最多255个字符")
	private String baseline;

    /**
    * 代码路径
    * [代码路径]
    */
    @Column(name = "svn_code_url", nullable = true, updatable = true)
	@Size(max = 255, message = "代码路径超长，最多255个字符")
	private String svnCodeUrl;

    /**
    * 构建工具
    * [构建工具]
    */
    @Column(name = "build_tools", nullable = true, updatable = true)
	@Size(max = 255, message = "构建工具超长，最多255个字符")
	private String buildTools;

    /**
    * 执行环境版本
    * [执行环境版本]-如：JDK版本
    */
    @Column(name = "env_version", nullable = true, updatable = true)
	@Size(max = 255, message = "执行环境版本超长，最多255个字符")
	private String envVersion;

    /**
    * 工具版本
    * [工具版本]-如：Maven版本
    */
    @Column(name = "tool_version", nullable = true, updatable = true)
	@Size(max = 255, message = "工具版本超长，最多255个字符")
	private String toolVersion;

    /**
    * 构建前操作
    * [构建前操作]
    */
    @Column(name = "pre_action", nullable = true, updatable = true)
	@Size(max = 255, message = "构建前操作超长，最多255个字符")
	private String preAction;

    /**
    * 构建操作
    * [构建操作]-依据不同的工具带出缺省的执行语句
    */
    @Column(name = "build_action", nullable = true, updatable = true)
	@Size(max = 255, message = "构建操作超长，最多255个字符")
	private String buildAction;

    /**
    * 构建后操作
    * [构建后操作]
    */
    @Column(name = "post_action", nullable = true, updatable = true)
	@Size(max = 255, message = "构建后操作超长，最多255个字符")
	private String postAction;

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

	public Integer getCmSameas(){
		return cmSameas;
	}
	public void setCmSameas(Integer cmSameas) {
		this.cmSameas = cmSameas;
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

