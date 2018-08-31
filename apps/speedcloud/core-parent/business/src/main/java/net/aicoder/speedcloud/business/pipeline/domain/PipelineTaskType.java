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
 * 任务类型
 * @author icode
 */
@Entity
@Table(appliesTo = "pipeline_task_type", comment = "[任务类型]")
//@DynamicUpdate
//@DynamicInsert
public class PipelineTaskType extends BaseEntity{

	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_VIEW_ORDER = "viewOrder";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 类型代码
    * 
    */
    @Column(name = "code", nullable = true, updatable = true)
	@Size(max = 255, message = "类型代码超长，最多255个字符")
	private String code;

    /**
    * 类型名称
    * 
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "类型名称超长，最多255个字符")
	private String name;

    /**
    * 展现顺序
    * 
    */
    @Column(name = "view_order", nullable = true, updatable = true)
	@Size(max = 255, message = "展现顺序超长，最多255个字符")
	private String viewOrder;

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

	public String getViewOrder(){
		return viewOrder;
	}
	public void setViewOrder(String viewOrder) {
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

