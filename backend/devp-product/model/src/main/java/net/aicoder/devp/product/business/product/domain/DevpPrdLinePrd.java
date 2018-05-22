package net.aicoder.devp.product.business.product.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;
import com.yunkang.saas.common.framework.eo.BaseEntity;



/**
 * 产品所属产品线定义
 * @author icode
 */
@Entity @Table
public class DevpPrdLinePrd extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_LINE_RID = "lineRid";
	public static final String PROPERTY_PRD_RID = "prdRid";
	public static final String PROPERTY_SEQ = "seq";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_STEREOTYPE = "stereotype";
	public static final String PROPERTY_SCOPE = "scope";
	public static final String PROPERTY_RECORD_STATE = "recordState";
	public static final String PROPERTY_CREATE_UCODE = "createUcode";
	public static final String PROPERTY_CMODIFY_UCODE = "cmodifyUcode";


    @Id
    @Column(name = "rid")
    private Long id;


    /**
    * 租户编号
    * 
    */
    @Column(name = "tid")
	@NotNull(message = "租户编号不能为空")
	private Long tid;

    /**
    * 代码
    * 
    */
    @Column(name = "code")
	@Size(max = 255, message = "代码超长，最多255个字符")
	private String code;

    /**
    * 名称
    * 
    */
    @Column(name = "name")
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;

    /**
    * 别名
    * 
    */
    @Column(name = "alias")
	@Size(max = 255, message = "别名超长，最多255个字符")
	private String alias;

    /**
    * 描述
    * 
    */
    @Column(name = "description")
	@Size(max = 255, message = "描述超长，最多255个字符")
	private String description;

    /**
    * 产品线编号
    * 
    */
    @Column(name = "line_rid")
	@NotNull(message = "产品线编号不能为空")
	private Long lineRid;

    /**
    * 产品编号
    * 
    */
    @Column(name = "prd_rid")
	@NotNull(message = "产品编号不能为空")
	private Long prdRid;

    /**
    * 顺序号
    * 
    */
    @Column(name = "seq")
	private Integer seq;

    /**
    * 类型
    * 
    */
    @Column(name = "type")
	@Size(max = 255, message = "类型超长，最多255个字符")
	private String type;

    /**
    * 构造型
    * 
    */
    @Column(name = "stereotype")
	@Size(max = 255, message = "构造型超长，最多255个字符")
	private String stereotype;

    /**
    * 范围
    * 
    */
    @Column(name = "scope")
	@Size(max = 255, message = "范围超长，最多255个字符")
	private String scope;

    /**
    * 记录状态
    * 
    */
    @Column(name = "record_state")
	private Integer recordState;

    /**
    * 创建用户代码
    * 
    */
    @Column(name = "create_ucode")
	@Size(max = 255, message = "创建用户代码超长，最多255个字符")
	private String createUcode;

    /**
    * 修改用户代码
    * 
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

	public Long getLineRid(){
		return lineRid;
	}
	public void setLineRid(Long lineRid) {
		this.lineRid = lineRid;
	}

	public Long getPrdRid(){
		return prdRid;
	}
	public void setPrdRid(Long prdRid) {
		this.prdRid = prdRid;
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

	public String getStereotype(){
		return stereotype;
	}
	public void setStereotype(String stereotype) {
		this.stereotype = stereotype;
	}

	public String getScope(){
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
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

