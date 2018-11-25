package net.aicoder.speedcloud.icode.business.domain.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 领域对象行为参数属性
 * @author icode
 */
@Entity()
@Table(name = "domain_entity_action_parameter_property")
//@DynamicUpdate
//@DynamicInsert
public class EntityActionParameterProperty extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ACTION_PARAMETER = "actionParameter";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_WRAPPER_TYPE = "wrapperType";
	public static final String PROPERTY_IDX = "idx";
	public static final String PROPERTY_MEMO = "memo";


    @Id
    @Column(name = "id")
    private String id;


    /**
    * 租户id
    * 
    */
    @Column(name = "tid", nullable = false, updatable = false)
	private Long tid;

    /**
    * 所属行为参数
    * 
    */
    @Column(name = "action_parameter", nullable = false, updatable = true)
	@Size(max = 255, message = "所属行为参数超长，最多255个字符")
	private String actionParameter;

    /**
    * 属性代码
    * 
    */
    @Column(name = "code", nullable = false, updatable = true)
	@Size(max = 255, message = "属性代码超长，最多255个字符")
	private String code;

    /**
    * 属性名称
    * 中文名称
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "属性名称超长，最多255个字符")
	private String name;

    /**
    * 类型
    * 
    */
    @Column(name = "type", nullable = false, updatable = true)
	@Size(max = 255, message = "类型超长，最多255个字符")
	private String type;

    /**
    * 外覆类型
    * List
    */
    @Column(name = "wrapper_type", nullable = true, updatable = true)
	@Size(max = 255, message = "外覆类型超长，最多255个字符")
	private String wrapperType;

    /**
    * 排序
    * 
    */
    @Column(name = "idx", nullable = true, updatable = true)
	private Integer idx;

    /**
    * 备注
    * 
    */
    @Column(name = "memo", nullable = true, updatable = true)
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String memo;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getActionParameter(){
		return actionParameter;
	}
	public void setActionParameter(String actionParameter) {
		this.actionParameter = actionParameter;
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

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getWrapperType(){
		return wrapperType;
	}
	public void setWrapperType(String wrapperType) {
		this.wrapperType = wrapperType;
	}

	public Integer getIdx(){
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public String getMemo(){
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

