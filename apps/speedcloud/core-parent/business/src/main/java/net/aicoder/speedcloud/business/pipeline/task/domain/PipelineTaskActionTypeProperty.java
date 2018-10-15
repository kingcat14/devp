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
 * 操作类型属性定义
 * @author icode
 */
@Entity
@Table(appliesTo = "pipeline_task_action_type_property", comment = "[操作类型属性定义]")
//@DynamicUpdate
//@DynamicInsert
public class PipelineTaskActionTypeProperty extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_TASK_TYPE = "taskType";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_VIEW_ORDER = "viewOrder";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_OPTION_VALUE = "optionValue";


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
    * 所属任务类型
    * 
    */
    @Column(name = "task_type", nullable = true, updatable = true)
	private Long taskType;

    /**
    * 属性名称
    * 
    */
    @Column(name = "name", nullable = false, updatable = false)
	@Size(max = 255, message = "属性名称超长，最多255个字符")
	private String name;

    /**
    * 属性代码
    * 
    */
    @Column(name = "code", nullable = false, updatable = false)
	@Size(max = 255, message = "属性代码超长，最多255个字符")
	private String code;

    /**
    * 展现顺序
    * 
    */
    @Column(name = "view_order", nullable = false, updatable = true)
	private Integer viewOrder;

    /**
    * 属性类型
    * 
    */
    @Column(name = "type", nullable = true, updatable = true)
	private String type;

    /**
    * 可选值
    * 类型为枚举时使用的值
    */
    @Column(name = "option_value", nullable = true, updatable = true)
	@Size(max = 255, message = "可选值超长，最多255个字符")
	private String optionValue;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getTaskType(){
		return taskType;
	}
	public void setTaskType(Long taskType) {
		this.taskType = taskType;
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

	public Integer getViewOrder(){
		return viewOrder;
	}
	public void setViewOrder(Integer viewOrder) {
		this.viewOrder = viewOrder;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getOptionValue(){
		return optionValue;
	}
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
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

