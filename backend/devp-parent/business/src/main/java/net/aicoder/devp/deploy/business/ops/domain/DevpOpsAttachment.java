package net.aicoder.devp.deploy.business.ops.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotEmpty;
import com.yunkang.saas.common.framework.eo.BaseEntity;



/**
 * 附件定义
 * @author icode
 */
@Entity
@Table(appliesTo = "devp_ops_attachment", comment = "[附件定义]")
//@DynamicUpdate
//@DynamicInsert
public class DevpOpsAttachment extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ETYPE = "etype";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_RECORD_STATE = "recordState";
	public static final String PROPERTY_TYPE_CODE = "typeCode";
	public static final String PROPERTY_TYPE_NAME = "typeName";
	public static final String PROPERTY_FILE_TYPE = "fileType";
	public static final String PROPERTY_ACCESS_TYPE = "accessType";
	public static final String PROPERTY_DOMAIN = "domain";
	public static final String PROPERTY_ADDRESS = "address";
	public static final String PROPERTY_FILE_VERSION = "fileVersion";
	public static final String PROPERTY_NEXUS_TYPE = "nexusType";
	public static final String PROPERTY_NEXUS_RID = "nexusRid";
	public static final String PROPERTY_SEQ = "seq";
	public static final String PROPERTY_CMODIFY_UCODE = "cmodifyUcode";


    @Id
    @Column(name = "rid")
    private Long id;


    /**
    * 租户编号
    * [租户编号]
    */
    @Column(name = "tid", updatable = false)
	@NotNull(message = "租户编号不能为空")
	private Long tid;

    /**
    * 元素类型
    * [元素类型]
    */
    @Column(name = "etype")
	@Size(max = 255, message = "元素类型超长，最多255个字符")
	private String etype;

    /**
    * 附件代码
    * [附件代码]
    */
    @Column(name = "code")
	@Size(max = 255, message = "附件代码超长，最多255个字符")
	private String code;

    /**
    * 附件名称
    * [附件名称]
    */
    @Column(name = "name")
	@Size(max = 255, message = "附件名称超长，最多255个字符")
	private String name;

    /**
    * 附件别名
    * [附件别名]
    */
    @Column(name = "alias")
	@Size(max = 255, message = "附件别名超长，最多255个字符")
	private String alias;

    /**
    * 附件描述
    * [附件描述]
    */
    @Column(name = "description")
	@Size(max = 255, message = "附件描述超长，最多255个字符")
	private String description;

    /**
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    @Column(name = "record_state")
	private Integer recordState;

    /**
    * 类型代码
    * [类型代码]
    */
    @Column(name = "type_code")
	@Size(max = 255, message = "类型代码超长，最多255个字符")
	private String typeCode;

    /**
    * 类型名称
    * [类型名称]-冗余字段，方便显示
    */
    @Column(name = "type_name")
	@Size(max = 255, message = "类型名称超长，最多255个字符")
	private String typeName;

    /**
    * 文件类型
    * [文件类型]
    */
    @Column(name = "file_type")
	@Size(max = 255, message = "文件类型超长，最多255个字符")
	private String fileType;

    /**
    * 访问方式
    * [访问方式]
    */
    @Column(name = "access_type")
	@Size(max = 255, message = "访问方式超长，最多255个字符")
	private String accessType;

    /**
    * 访问域
    * [访问域]
    */
    @Column(name = "domain")
	@Size(max = 255, message = "访问域超长，最多255个字符")
	private String domain;

    /**
    * 访问地址
    * [访问地址]
    */
    @Column(name = "address")
	@Size(max = 255, message = "访问地址超长，最多255个字符")
	private String address;

    /**
    * 附件版本
    * [附件版本]-当前版本
    */
    @Column(name = "file_version")
	@Size(max = 255, message = "附件版本超长，最多255个字符")
	private String fileVersion;

    /**
    * 关联记录类型
    * [关联记录类型]
    */
    @Column(name = "nexus_type")
	@NotNull(message = "关联记录类型不能为空")
	@Size(max = 255, message = "关联记录类型超长，最多255个字符")
	private String nexusType;

    /**
    * 关联记录编号
    * [关联记录编号]
    */
    @Column(name = "nexus_rid")
	@NotNull(message = "关联记录编号不能为空")
	private Long nexusRid;

    /**
    * 顺序号
    * [顺序号]
    */
    @Column(name = "seq")
	private Integer seq;

    /**
    * 修改用户代码
    * [修改用户代码]
    */
    @Column(name = "cmodify_ucode")
	@Size(max = 255, message = "修改用户代码超长，最多255个字符")
	private String cmodifyUcode;

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

	public String getTypeCode(){
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName(){
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	public String getDomain(){
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAddress(){
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getFileVersion(){
		return fileVersion;
	}
	public void setFileVersion(String fileVersion) {
		this.fileVersion = fileVersion;
	}

	public String getNexusType(){
		return nexusType;
	}
	public void setNexusType(String nexusType) {
		this.nexusType = nexusType;
	}

	public Long getNexusRid(){
		return nexusRid;
	}
	public void setNexusRid(Long nexusRid) {
		this.nexusRid = nexusRid;
	}

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getCmodifyUcode(){
		return cmodifyUcode;
	}
	public void setCmodifyUcode(String cmodifyUcode) {
		this.cmodifyUcode = cmodifyUcode;
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

