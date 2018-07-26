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
 * 资产项目分组映射
 * @author icode
 */
@Entity
@Table(appliesTo = "devp_ops_ci_group", comment = "[资产项目分组映射]")
//@DynamicUpdate
//@DynamicInsert
public class DevpOpsCiGroup extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ETYPE = "etype";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_RECORD_STATE = "recordState";
	public static final String PROPERTY_TYPE_CODE = "typeCode";
	public static final String PROPERTY_TYPE_NAME = "typeName";
	public static final String PROPERTY_GROUP_RID = "groupRid";
	public static final String PROPERTY_CI_RID = "ciRid";
	public static final String PROPERTY_SEQ = "seq";
	public static final String PROPERTY_PARAS_CODE = "parasCode";
	public static final String PROPERTY_CMODIFY_UCODE = "cmodifyUcode";


    @Id
    @Column(name = "rid")
    private Long id;


    /**
    * 租户编号
    * [租户编号]
    */
    @Column(name = "tid")
	@NotNull(message = "租户编号不能为空")
	private Long tid;

    /**
    * 元素类型
    * [元素类型]
    */
    @Column(name = "etype")
	@NotNull(message = "元素类型不能为空")
	@Size(max = 255, message = "元素类型超长，最多255个字符")
	private String etype;

    /**
    * 名称
    * [名称]
    */
    @Column(name = "name")
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;

    /**
    * 代码
    * [代码]
    */
    @Column(name = "code")
	@Size(max = 255, message = "代码超长，最多255个字符")
	private String code;

    /**
    * 别名
    * [别名]
    */
    @Column(name = "alias")
	@Size(max = 255, message = "别名超长，最多255个字符")
	private String alias;

    /**
    * 描述
    * [描述]-资产项目所属分组描述
    */
    @Column(name = "description")
	@Size(max = 255, message = "描述超长，最多255个字符")
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
    * 分组记录编号
    * [分组记录编号]
    */
    @Column(name = "group_rid")
	@NotNull(message = "分组记录编号不能为空")
	private Long groupRid;

    /**
    * 资产记录编号
    * [资产记录编号]
    */
    @Column(name = "ci_rid")
	@NotNull(message = "资产记录编号不能为空")
	private Long ciRid;

    /**
    * 顺序号
    * [顺序号]
    */
    @Column(name = "seq")
	private Integer seq;

    /**
    * 参数定义标识
    * [参数定义标识]-扩展参数定义的标识
    */
    @Column(name = "paras_code")
	@Size(max = 255, message = "参数定义标识超长，最多255个字符")
	private String parasCode;

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

	public Long getGroupRid(){
		return groupRid;
	}
	public void setGroupRid(Long groupRid) {
		this.groupRid = groupRid;
	}

	public Long getCiRid(){
		return ciRid;
	}
	public void setCiRid(Long ciRid) {
		this.ciRid = ciRid;
	}

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getParasCode(){
		return parasCode;
	}
	public void setParasCode(String parasCode) {
		this.parasCode = parasCode;
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

