package net.aicoder.devp.product.business.product.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;
import com.yunkang.saas.common.framework.eo.BaseEntity;



/**
 * 产品线定义
 * @author icode
 */
@Entity
@Table
public class DevpPrdPrdline extends BaseEntity{

	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_DOMAIN = "domain";
	public static final String PROPERTY_STEREOTYPE = "stereotype";
	public static final String PROPERTY_SCOPE = "scope";
	public static final String PROPERTY_VERSION = "version";
	public static final String PROPERTY_PHASE = "phase";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_PARENT_RID = "parentRid";
	public static final String PROPERTY_SEQ = "seq";
	public static final String PROPERTY_RECORD_STATE = "recordState";
	public static final String PROPERTY_CREATE_UCODE = "createUcode";
	public static final String PROPERTY_MODIFY_UCODE = "modifyUcode";


    @Id
    @Column(name = "rid")
    private Long id;


    /**
    * 产品线代码
    * 
    */
    @Column(name = "code")
	@NotNull(message = "产品线代码不能为空")
	@Size(max = 255, message = "产品线代码超长，最多255个字符")
	private String code;

    /**
    * 产品线名称
    * 
    */
    @Column(name = "name")
	@NotNull(message = "产品线名称不能为空")
	@Size(max = 255, message = "产品线名称超长，最多255个字符")
	private String name;

    /**
    * 产品线别名
    * 
    */
    @Column(name = "alias")
	@Size(max = 255, message = "产品线别名超长，最多255个字符")
	private String alias;

    /**
    * 产品线描述
    * 
    */
    @Column(name = "description")
	@Size(max = 255, message = "产品线描述超长，最多255个字符")
	private String description;

    /**
    * 产品线类型
    * 
    */
    @Column(name = "type")
	@Size(max = 255, message = "产品线类型超长，最多255个字符")
	private String type;

    /**
    * 领域
    * 
    */
    @Column(name = "domain")
	@Size(max = 255, message = "领域超长，最多255个字符")
	private String domain;

    /**
    * 构造型
    * 
    */
    @Column(name = "stereotype")
	@Size(max = 255, message = "构造型超长，最多255个字符")
	private String stereotype;

    /**
    * 访问控制范围
    * 
    */
    @Column(name = "scope")
	@Size(max = 255, message = "访问控制范围超长，最多255个字符")
	private String scope;

    /**
    * 版本
    * 
    */
    @Column(name = "version")
	@Size(max = 255, message = "版本超长，最多255个字符")
	private String version;

    /**
    * 阶段
    * 
    */
    @Column(name = "phase")
	@Size(max = 255, message = "阶段超长，最多255个字符")
	private String phase;

    /**
    * 状态
    * 
    */
    @Column(name = "status")
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;

    /**
    * 父产品线编号
    * 
    */
    @Column(name = "parent_rid")
	private Long parentRid;

    /**
    * 顺序号
    * 
    */
    @Column(name = "seq")
	private Integer seq;

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
    @Column(name = "modify_ucode")
	@Size(max = 255, message = "修改用户代码超长，最多255个字符")
	private String modifyUcode;

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

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getDomain(){
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
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

	public String getVersion(){
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	public String getPhase(){
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Long getParentRid(){
		return parentRid;
	}
	public void setParentRid(Long parentRid) {
		this.parentRid = parentRid;
	}

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
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
