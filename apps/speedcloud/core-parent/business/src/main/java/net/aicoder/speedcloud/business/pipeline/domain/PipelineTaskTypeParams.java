package net.aicoder.speedcloud.business.pipeline.domain;

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
 * 任务类型参数定义
 * @author icode
 */
@Entity
@Table(appliesTo = "pipeline_task_type_params", comment = "[任务类型参数定义]")
//@DynamicUpdate
//@DynamicInsert
public class PipelineTaskTypeParams extends BaseEntity{

	public static final String PROPERTY_TASK_TYPE = "taskType";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_VIEW_ORDER = "viewOrder";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 所属任务类型
    * 
    */
    @Column(name = "task_type", nullable = true, updatable = true)
	private Long taskType;

    /**
    * 参数名称
    * 
    */
    @Column(name = "name", nullable = false, updatable = false)
	@Size(max = 255, message = "参数名称超长，最多255个字符")
	private String name;

    /**
    * 参数代码
    * 
    */
    @Column(name = "code", nullable = false, updatable = false)
	@Size(max = 255, message = "参数代码超长，最多255个字符")
	private String code;

    /**
    * 展现顺序
    * 
    */
    @Column(name = "view_order", nullable = false, updatable = true)
	private Integer viewOrder;

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

