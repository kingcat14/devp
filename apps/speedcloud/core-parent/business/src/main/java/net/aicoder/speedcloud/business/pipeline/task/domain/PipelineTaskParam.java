package net.aicoder.speedcloud.business.pipeline.task.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotEmpty;



/**
 * 任务参数
 * @author icode
 */
@Entity
@Table(appliesTo = "pipeline_task_param", comment = "[任务参数]")
//@DynamicUpdate
//@DynamicInsert
public class PipelineTaskParam extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_TASK = "task";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_DEFAULT_VALUE = "defaultValue";
	public static final String PROPERTY_VIEW_ORDER = "viewOrder";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_DELETABLE = "deletable";
	public static final String PROPERTY_ENUM_VALUE = "enumValue";
	public static final String PROPERTY_VALUE = "value";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 租户id
    * 
    */
    @Column(name = "tid", nullable = false, updatable = false)
	private Long tid;

    /**
    * 所属任务
    * 
    */
    @Column(name = "task", nullable = true, updatable = true)
	private Long task;

    /**
    * 参数名称
    * 
    */
    @Column(name = "name", nullable = false, updatable = false)
	@Size(max = 255, message = "参数名称超长，最多255个字符")
	private String name;

    /**
    * 参数类型
    * 字符或者枚举
    */
    @Column(name = "type", nullable = true, updatable = true)
	@Size(max = 255, message = "参数类型超长，最多255个字符")
	private String type;

    /**
    * 默认值
    * 
    */
    @Column(name = "default_value", nullable = true, updatable = true)
	@Size(max = 255, message = "默认值超长，最多255个字符")
	private String defaultValue;

    /**
    * 展现顺序
    * 
    */
    @Column(name = "view_order", nullable = false, updatable = true)
	private Integer viewOrder;

    /**
    * 参数描述
    * 
    */
    @Column(name = "description", nullable = true, updatable = true)
	@Size(max = 255, message = "参数描述超长，最多255个字符")
	private String description;

    /**
    * 可删除
    * 
    */
    @Column(name = "deletable", nullable = true, updatable = true)
	private Boolean deletable;

    /**
    * 可选值
    * 类型为枚举时可选的值，分号分隔
    */
    @Column(name = "enum_value", nullable = true, updatable = true)
	@Size(max = 255, message = "可选值超长，最多255个字符")
	private String enumValue;

    /**
    * 参数值
    * 执行时最终使用的值
    */
    @Column(name = "value", nullable = true, updatable = true)
	@Size(max = 255, message = "参数值超长，最多255个字符")
	private String value;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getTask(){
		return task;
	}
	public void setTask(Long task) {
		this.task = task;
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

	public String getDefaultValue(){
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Integer getViewOrder(){
		return viewOrder;
	}
	public void setViewOrder(Integer viewOrder) {
		this.viewOrder = viewOrder;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDeletable(){
		return deletable;
	}
	public void setDeletable(Boolean deletable) {
		this.deletable = deletable;
	}

	public String getEnumValue(){
		return enumValue;
	}
	public void setEnumValue(String enumValue) {
		this.enumValue = enumValue;
	}

	public String getValue(){
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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

