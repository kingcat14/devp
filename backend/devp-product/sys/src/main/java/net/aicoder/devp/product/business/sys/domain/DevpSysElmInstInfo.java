package net.aicoder.devp.product.business.sys.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;
import com.yunkang.saas.common.framework.eo.BaseEntity;



/**
 * 系统元素实例
 * @author icode
 */
@Entity
@Table
public class DevpSysElmInstInfo extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_PRD_RID = "prdRid";
	public static final String PROPERTY_CONT_RID = "contRid";
	public static final String PROPERTY_SPRD_RID = "sprdRid";
	public static final String PROPERTY_SELM_RID = "selmRid";
	public static final String PROPERTY_DPRD_RID = "dprdRid";
	public static final String PROPERTY_DELM_RID = "delmRid";
	public static final String PROPERTY_SINST_RID = "sinstRid";
	public static final String PROPERTY_DINST_RID = "dinstRid";
	public static final String PROPERTY_SEQ = "seq";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_INFO_VALUE1 = "infoValue1";
	public static final String PROPERTY_INFO_VALUE2 = "infoValue2";
	public static final String PROPERTY_INFO_VALUE3 = "infoValue3";
	public static final String PROPERTY_INFO_VALUE4 = "infoValue4";
	public static final String PROPERTY_INFO_VALUE5 = "infoValue5";
	public static final String PROPERTY_NOTES = "notes";
	public static final String PROPERTY_RECORD_STATE = "recordState";
	public static final String PROPERTY_CREATE_UCODE = "createUcode";
	public static final String PROPERTY_MODIFY_UCODE = "modifyUcode";


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
    * [扩展信息描述]-对应当前属性值
    */
    @Column(name = "description")
	@Size(max = 255, message = "扩展信息描述超长，最多255个字符")
	private String description;

    /**
    * 产品编号
    * [产品编号]
    */
    @Column(name = "prd_rid")
	@NotNull(message = "产品编号不能为空")
	private Long prdRid;

    /**
    * 关联连接编号
    * [关联连接编号]
    */
    @Column(name = "cont_rid")
	@NotNull(message = "关联连接编号不能为空")
	private Long contRid;

    /**
    * 来源产品编号
    * [来源产品编号]
    */
    @Column(name = "sprd_rid")
	@NotNull(message = "来源产品编号不能为空")
	private Long sprdRid;

    /**
    * 来源系统元素编号
    * [来源系统元素编号]
    */
    @Column(name = "selm_rid")
	@NotNull(message = "来源系统元素编号不能为空")
	private Long selmRid;

    /**
    * 目标产品编号
    * [目标产品编号]
    */
    @Column(name = "dprd_rid")
	@NotNull(message = "目标产品编号不能为空")
	private Long dprdRid;

    /**
    * 目标系统元素编号
    * [目标系统元素编号]
    */
    @Column(name = "delm_rid")
	@NotNull(message = "目标系统元素编号不能为空")
	private Long delmRid;

    /**
    * 来源系统元素实例编号
    * [来源系统元素实例编号]-缺省值为0
    */
    @Column(name = "sinst_rid")
	@NotNull(message = "来源系统元素实例编号不能为空")
	private Long sinstRid;

    /**
    * 目标系统元素实例编号
    * [目标系统元素实例编号]-缺省值为0
    */
    @Column(name = "dinst_rid")
	@NotNull(message = "目标系统元素实例编号不能为空")
	private Long dinstRid;

    /**
    * 顺序号
    * [顺序号]
    */
    @Column(name = "seq")
	private Integer seq;

    /**
    * 设值方式
    * [设值方式]-S-仅对来源,D-仅对目标,Bi-双方,N-均无效
    */
    @Column(name = "type")
	@Size(max = 255, message = "设值方式超长，最多255个字符")
	private String type;

    /**
    * 信息值1
    * [信息值1]
    */
    @Column(name = "info_value1")
	@Size(max = 255, message = "信息值1超长，最多255个字符")
	private String infoValue1;

    /**
    * 信息值2
    * [信息值2]
    */
    @Column(name = "info_value2")
	@Size(max = 255, message = "信息值2超长，最多255个字符")
	private String infoValue2;

    /**
    * 信息值3
    * [信息值3]
    */
    @Column(name = "info_value3")
	@Size(max = 255, message = "信息值3超长，最多255个字符")
	private String infoValue3;

    /**
    * 信息值4
    * [信息值4]
    */
    @Column(name = "info_value4")
	@Size(max = 255, message = "信息值4超长，最多255个字符")
	private String infoValue4;

    /**
    * 信息值5
    * [信息值5]
    */
    @Column(name = "info_value5")
	@Size(max = 255, message = "信息值5超长，最多255个字符")
	private String infoValue5;

    /**
    * 备注
    * [备注]
    */
    @Column(name = "notes")
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String notes;

    /**
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    @Column(name = "record_state")
	private Integer recordState;

    /**
    * 创建用户代码
    * [创建用户代码]
    */
    @Column(name = "create_ucode")
	@Size(max = 255, message = "创建用户代码超长，最多255个字符")
	private String createUcode;

    /**
    * 修改用户代码
    * [修改用户代码]
    */
    @Column(name = "modify_ucode")
	@Size(max = 255, message = "修改用户代码超长，最多255个字符")
	private String modifyUcode;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
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

	public Long getPrdRid(){
		return prdRid;
	}
	public void setPrdRid(Long prdRid) {
		this.prdRid = prdRid;
	}

	public Long getContRid(){
		return contRid;
	}
	public void setContRid(Long contRid) {
		this.contRid = contRid;
	}

	public Long getSprdRid(){
		return sprdRid;
	}
	public void setSprdRid(Long sprdRid) {
		this.sprdRid = sprdRid;
	}

	public Long getSelmRid(){
		return selmRid;
	}
	public void setSelmRid(Long selmRid) {
		this.selmRid = selmRid;
	}

	public Long getDprdRid(){
		return dprdRid;
	}
	public void setDprdRid(Long dprdRid) {
		this.dprdRid = dprdRid;
	}

	public Long getDelmRid(){
		return delmRid;
	}
	public void setDelmRid(Long delmRid) {
		this.delmRid = delmRid;
	}

	public Long getSinstRid(){
		return sinstRid;
	}
	public void setSinstRid(Long sinstRid) {
		this.sinstRid = sinstRid;
	}

	public Long getDinstRid(){
		return dinstRid;
	}
	public void setDinstRid(Long dinstRid) {
		this.dinstRid = dinstRid;
	}

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getInfoValue1(){
		return infoValue1;
	}
	public void setInfoValue1(String infoValue1) {
		this.infoValue1 = infoValue1;
	}

	public String getInfoValue2(){
		return infoValue2;
	}
	public void setInfoValue2(String infoValue2) {
		this.infoValue2 = infoValue2;
	}

	public String getInfoValue3(){
		return infoValue3;
	}
	public void setInfoValue3(String infoValue3) {
		this.infoValue3 = infoValue3;
	}

	public String getInfoValue4(){
		return infoValue4;
	}
	public void setInfoValue4(String infoValue4) {
		this.infoValue4 = infoValue4;
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

	public Integer getRecordState(){
		return recordState;
	}
	public void setRecordState(Integer recordState) {
		this.recordState = recordState;
	}

	public String getCreateUcode(){
		return createUcode;
	}
	public void setCreateUcode(String createUcode) {
		this.createUcode = createUcode;
	}

	public String getModifyUcode(){
		return modifyUcode;
	}
	public void setModifyUcode(String modifyUcode) {
		this.modifyUcode = modifyUcode;
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

