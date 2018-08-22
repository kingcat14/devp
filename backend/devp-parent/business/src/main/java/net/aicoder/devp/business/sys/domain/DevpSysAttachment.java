package net.aicoder.devp.business.sys.domain;

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
 * 附件
 * @author icode
 */
@Entity
@Table(appliesTo = "devp_sys_attachment", comment = "[附件]")
//@DynamicUpdate
//@DynamicInsert
public class DevpSysAttachment extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ETYPE = "etype";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_RECORD_STATE = "recordState";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_SUB_TYPE = "subType";
	public static final String PROPERTY_FILE_TYPE = "fileType";
	public static final String PROPERTY_ACCESS_TYPE = "accessType";
	public static final String PROPERTY_WORKSPACE = "workspace";
	public static final String PROPERTY_PATH = "path";
	public static final String PROPERTY_FILE_NAME = "fileName";
	public static final String PROPERTY_FILE_VERSION = "fileVersion";
	public static final String PROPERTY_OBJ_RID = "objRid";
	public static final String PROPERTY_SEQ = "seq";
	public static final String PROPERTY_TPL_RID = "tplRid";
	public static final String PROPERTY_FILE_CREATER = "fileCreater";
	public static final String PROPERTY_FILE_EDITOR = "fileEditor";
	public static final String PROPERTY_REVISABILITY = "revisability";
	public static final String PROPERTY_CREATE_UCODE = "createUcode";
	public static final String PROPERTY_CREATE_UNAME = "createUname";
	public static final String PROPERTY_CMODIFY_UCODE = "cmodifyUcode";
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
    * [元素类型]
    */
    @Column(name = "etype", nullable = false, updatable = true)
	@Size(max = 255, message = "元素类型超长，最多255个字符")
	private String etype;

    /**
    * 附件名称
    * [附件名称]
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "附件名称超长，最多255个字符")
	private String name;

    /**
    * 附件代码
    * [附件代码]
    */
    @Column(name = "code", nullable = true, updatable = true)
	@Size(max = 255, message = "附件代码超长，最多255个字符")
	private String code;

    /**
    * 附件别名
    * [附件别名]
    */
    @Column(name = "alias", nullable = true, updatable = true)
	@Size(max = 255, message = "附件别名超长，最多255个字符")
	private String alias;

    /**
    * 附件描述
    * [附件描述]
    */
    @Column(name = "description", nullable = true, updatable = true)
	@Size(max = 255, message = "附件描述超长，最多255个字符")
	private String description;

    /**
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    @Column(name = "record_state", nullable = true, updatable = true)
	private Integer recordState;

    /**
    * 类型
    * [类型]-配置文件(依据模板创建)；工作成果物；参考资料；其它
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
    * 文件类型
    * [文件类型]
    */
    @Column(name = "file_type", nullable = true, updatable = true)
	@Size(max = 255, message = "文件类型超长，最多255个字符")
	private String fileType;

    /**
    * 访问方式
    * [访问方式]
    */
    @Column(name = "access_type", nullable = true, updatable = true)
	@Size(max = 255, message = "访问方式超长，最多255个字符")
	private String accessType;

    /**
    * 工作空间
    * [工作空间]
    */
    @Column(name = "workspace", nullable = true, updatable = true)
	@Size(max = 255, message = "工作空间超长，最多255个字符")
	private String workspace;

    /**
    * 访问路径
    * [访问路径]
    */
    @Column(name = "path", nullable = true, updatable = true)
	@Size(max = 255, message = "访问路径超长，最多255个字符")
	private String path;

    /**
    * 访问地址
    * [访问地址]
    */
    @Column(name = "file_name", nullable = true, updatable = true)
	@Size(max = 255, message = "访问地址超长，最多255个字符")
	private String fileName;

    /**
    * 附件版本
    * [附件版本]-当前版本
    */
    @Column(name = "file_version", nullable = true, updatable = true)
	@Size(max = 255, message = "附件版本超长，最多255个字符")
	private String fileVersion;

    /**
    * 关联记录编号
    * [关联记录编号]
    */
    @Column(name = "obj_rid", nullable = false, updatable = true)
	private Long objRid;

    /**
    * 顺序号
    * [顺序号]
    */
    @Column(name = "seq", nullable = true, updatable = true)
	private Integer seq;

    /**
    * 模板编号
    * [模板编号]
    */
    @Column(name = "tpl_rid", nullable = true, updatable = true)
	private Long tplRid;

    /**
    * 文件创建器
    * [文件创建器]
    */
    @Column(name = "file_creater", nullable = true, updatable = true)
	@Size(max = 255, message = "文件创建器超长，最多255个字符")
	private String fileCreater;

    /**
    * 文件编辑器
    * [文件编辑器]
    */
    @Column(name = "file_editor", nullable = true, updatable = true)
	@Size(max = 255, message = "文件编辑器超长，最多255个字符")
	private String fileEditor;

    /**
    * 是否可修改
    * [是否可修改]-0:不可修改；1：可修改
    */
    @Column(name = "revisability", nullable = true, updatable = true)
	private Integer revisability;

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
    @Column(name = "cmodify_ucode", nullable = true, updatable = true)
	@Size(max = 255, message = "修改用户代码超长，最多255个字符")
	private String cmodifyUcode;

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

	public String getFileType(){
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getAccessType(){
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getWorkspace(){
		return workspace;
	}
	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}

	public String getPath(){
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public String getFileName(){
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileVersion(){
		return fileVersion;
	}
	public void setFileVersion(String fileVersion) {
		this.fileVersion = fileVersion;
	}

	public Long getObjRid(){
		return objRid;
	}
	public void setObjRid(Long objRid) {
		this.objRid = objRid;
	}

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Long getTplRid(){
		return tplRid;
	}
	public void setTplRid(Long tplRid) {
		this.tplRid = tplRid;
	}

	public String getFileCreater(){
		return fileCreater;
	}
	public void setFileCreater(String fileCreater) {
		this.fileCreater = fileCreater;
	}

	public String getFileEditor(){
		return fileEditor;
	}
	public void setFileEditor(String fileEditor) {
		this.fileEditor = fileEditor;
	}

	public Integer getRevisability(){
		return revisability;
	}
	public void setRevisability(Integer revisability) {
		this.revisability = revisability;
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

	public String getCmodifyUcode(){
		return cmodifyUcode;
	}
	public void setCmodifyUcode(String cmodifyUcode) {
		this.cmodifyUcode = cmodifyUcode;
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

