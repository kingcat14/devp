package net.aicoder.devp.product.business.sys.domain;

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
 * 系统元素扩充信息
 * @author icode
 */
@Entity
@Table(appliesTo = "devp_sys_element_info", comment = "[系统元素扩充信息]")
//@DynamicUpdate
//@DynamicInsert
public class DevpSysElementInfo extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ETYPE = "etype";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_RECORD_STATE = "recordState";
	public static final String PROPERTY_OBJ_RID = "objRid";
	public static final String PROPERTY_DATA_TYPE = "dataType";
	public static final String PROPERTY_INFO_VALUE = "infoValue";
	public static final String PROPERTY_NOTES = "notes";


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
    * 扩展信息代码
    * [扩展信息代码]
    */
    @Column(name = "code")
	@NotNull(message = "扩展信息代码不能为空")
	@Size(max = 255, message = "扩展信息代码超长，最多255个字符")
	private String code;

    /**
    * 扩展信息名称
    * [扩展信息名称]-显示名称
    */
    @Column(name = "name")
	@Size(max = 255, message = "扩展信息名称超长，最多255个字符")
	private String name;

    /**
    * 扩展信息别名
    * [扩展信息别名]
    */
    @Column(name = "alias")
	@Size(max = 255, message = "扩展信息别名超长，最多255个字符")
	private String alias;

    /**
    * 扩展信息描述
    * [扩展信息描述]
    */
    @Column(name = "description")
	@Size(max = 255, message = "扩展信息描述超长，最多255个字符")
	private String description;

    /**
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    @Column(name = "record_state")
	private Integer recordState;

    /**
    * 元素编号
    * [元素编号]
    */
    @Column(name = "obj_rid")
	@NotNull(message = "元素编号不能为空")
	private Long objRid;

    /**
    * 数据类型
    * [数据类型]
    */
    @Column(name = "data_type")
	@Size(max = 255, message = "数据类型超长，最多255个字符")
	private String dataType;

    /**
    * 扩展信息值
    * [扩展信息值]
    */
    @Column(name = "info_value")
	@Size(max = 255, message = "扩展信息值超长，最多255个字符")
	private String infoValue;

    /**
    * 备注
    * [备注]
    */
    @Column(name = "notes")
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String notes;

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

	public Long getObjRid(){
		return objRid;
	}
	public void setObjRid(Long objRid) {
		this.objRid = objRid;
	}

	public String getDataType(){
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getInfoValue(){
		return infoValue;
	}
	public void setInfoValue(String infoValue) {
		this.infoValue = infoValue;
	}

	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
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

