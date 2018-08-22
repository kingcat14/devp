package net.aicoder.devp.business.ops.domain;

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
 * 运维元素扩充信息
 * @author icode
 */
@Entity
@Table(appliesTo = "devp_ops_element_info", comment = "[运维元素扩充信息]")
//@DynamicUpdate
//@DynamicInsert
public class DevpOpsElementInfo extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ETYPE = "etype";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_RECORD_STATE = "recordState";
	public static final String PROPERTY_ELM_RID = "elmRid";
	public static final String PROPERTY_INST_RID = "instRid";
	public static final String PROPERTY_SEQ = "seq";
	public static final String PROPERTY_INFO_CODE1 = "infoCode1";
	public static final String PROPERTY_INFO_VALUE1 = "infoValue1";
	public static final String PROPERTY_INFO_CODE2 = "infoCode2";
	public static final String PROPERTY_INFO_VALUE2 = "infoValue2";
	public static final String PROPERTY_INFO_CODE3 = "infoCode3";
	public static final String PROPERTY_INFO_VALUE3 = "infoValue3";
	public static final String PROPERTY_INFO_CODE4 = "infoCode4";
	public static final String PROPERTY_INFO_VALUE4 = "infoValue4";
	public static final String PROPERTY_INFO_CODE5 = "infoCode5";
	public static final String PROPERTY_INFO_VALUE5 = "infoValue5";
	public static final String PROPERTY_NOTES = "notes";
	public static final String PROPERTY_PARAS_CODE = "parasCode";
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
    * [元素类型]
    */
    @Column(name = "etype", nullable = false, updatable = true)
	@Size(max = 255, message = "元素类型超长，最多255个字符")
	private String etype;

    /**
    * 扩展信息代码
    * [扩展信息代码]
    */
    @Column(name = "code", nullable = false, updatable = true)
	@Size(max = 255, message = "扩展信息代码超长，最多255个字符")
	private String code;

    /**
    * 扩展信息名称
    * [扩展信息名称]-显示名称
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "扩展信息名称超长，最多255个字符")
	private String name;

    /**
    * 扩展信息别名
    * [扩展信息别名]
    */
    @Column(name = "alias", nullable = true, updatable = true)
	@Size(max = 255, message = "扩展信息别名超长，最多255个字符")
	private String alias;

    /**
    * 扩展信息描述
    * [扩展信息描述]-对应当前属性值
    */
    @Column(name = "description", nullable = true, updatable = true)
	@Size(max = 255, message = "扩展信息描述超长，最多255个字符")
	private String description;

    /**
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    @Column(name = "record_state", nullable = true, updatable = true)
	private Integer recordState;

    /**
    * 元素编号
    * [元素编号]
    */
    @Column(name = "elm_rid", nullable = false, updatable = true)
	private Long elmRid;

    /**
    * 元素实例编号
    * [元素实例编号]-缺省值为0
    */
    @Column(name = "inst_rid", nullable = false, updatable = true)
	private Long instRid;

    /**
    * 顺序号
    * [顺序号]
    */
    @Column(name = "seq", nullable = true, updatable = true)
	private Integer seq;

    /**
    * 扩展信息代码1
    * [扩展信息代码1]
    */
    @Column(name = "info_code1", nullable = true, updatable = true)
	@Size(max = 255, message = "扩展信息代码1超长，最多255个字符")
	private String infoCode1;

    /**
    * 扩展信息值1
    * [扩展信息值1]
    */
    @Column(name = "info_value1", nullable = true, updatable = true)
	@Size(max = 255, message = "扩展信息值1超长，最多255个字符")
	private String infoValue1;

    /**
    * 扩展信息代码2
    * [扩展信息代码2]
    */
    @Column(name = "info_code2", nullable = true, updatable = true)
	@Size(max = 255, message = "扩展信息代码2超长，最多255个字符")
	private String infoCode2;

    /**
    * 扩展信息值2
    * [扩展信息值2]
    */
    @Column(name = "info_value2", nullable = true, updatable = true)
	@Size(max = 255, message = "扩展信息值2超长，最多255个字符")
	private String infoValue2;

    /**
    * 扩展信息代码3
    * [扩展信息代码3]
    */
    @Column(name = "info_code3", nullable = true, updatable = true)
	@Size(max = 255, message = "扩展信息代码3超长，最多255个字符")
	private String infoCode3;

    /**
    * 扩展信息值3
    * [扩展信息值3]
    */
    @Column(name = "info_value3", nullable = true, updatable = true)
	@Size(max = 255, message = "扩展信息值3超长，最多255个字符")
	private String infoValue3;

    /**
    * 扩展信息代码4
    * [扩展信息代码4]
    */
    @Column(name = "info_code4", nullable = true, updatable = true)
	@Size(max = 255, message = "扩展信息代码4超长，最多255个字符")
	private String infoCode4;

    /**
    * 扩展信息值4
    * [扩展信息值4]
    */
    @Column(name = "info_value4", nullable = true, updatable = true)
	@Size(max = 255, message = "扩展信息值4超长，最多255个字符")
	private String infoValue4;

    /**
    * 扩展信息代码5
    * [扩展信息代码5]
    */
    @Column(name = "info_code5", nullable = true, updatable = true)
	@Size(max = 255, message = "扩展信息代码5超长，最多255个字符")
	private String infoCode5;

    /**
    * 扩展信息值5
    * [扩展信息值5]
    */
    @Column(name = "info_value5", nullable = true, updatable = true)
	@Size(max = 255, message = "扩展信息值5超长，最多255个字符")
	private String infoValue5;

    /**
    * 备注
    * [备注]
    */
    @Column(name = "notes", nullable = true, updatable = true)
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String notes;

    /**
    * 参数定义标识
    * [参数定义标识]-扩展参数定义的标识
    */
    @Column(name = "paras_code", nullable = true, updatable = true)
	@Size(max = 255, message = "参数定义标识超长，最多255个字符")
	private String parasCode;

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

	public Long getElmRid(){
		return elmRid;
	}
	public void setElmRid(Long elmRid) {
		this.elmRid = elmRid;
	}

	public Long getInstRid(){
		return instRid;
	}
	public void setInstRid(Long instRid) {
		this.instRid = instRid;
	}

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getInfoCode1(){
		return infoCode1;
	}
	public void setInfoCode1(String infoCode1) {
		this.infoCode1 = infoCode1;
	}

	public String getInfoValue1(){
		return infoValue1;
	}
	public void setInfoValue1(String infoValue1) {
		this.infoValue1 = infoValue1;
	}

	public String getInfoCode2(){
		return infoCode2;
	}
	public void setInfoCode2(String infoCode2) {
		this.infoCode2 = infoCode2;
	}

	public String getInfoValue2(){
		return infoValue2;
	}
	public void setInfoValue2(String infoValue2) {
		this.infoValue2 = infoValue2;
	}

	public String getInfoCode3(){
		return infoCode3;
	}
	public void setInfoCode3(String infoCode3) {
		this.infoCode3 = infoCode3;
	}

	public String getInfoValue3(){
		return infoValue3;
	}
	public void setInfoValue3(String infoValue3) {
		this.infoValue3 = infoValue3;
	}

	public String getInfoCode4(){
		return infoCode4;
	}
	public void setInfoCode4(String infoCode4) {
		this.infoCode4 = infoCode4;
	}

	public String getInfoValue4(){
		return infoValue4;
	}
	public void setInfoValue4(String infoValue4) {
		this.infoValue4 = infoValue4;
	}

	public String getInfoCode5(){
		return infoCode5;
	}
	public void setInfoCode5(String infoCode5) {
		this.infoCode5 = infoCode5;
	}

	public String getInfoValue5(){
		return infoValue5;
	}
	public void setInfoValue5(String infoValue5) {
		this.infoValue5 = infoValue5;
	}

	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getParasCode(){
		return parasCode;
	}
	public void setParasCode(String parasCode) {
		this.parasCode = parasCode;
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

