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
 * 任务
 * @author icode
 */
@Entity
@Table(appliesTo = "pipeline_task", comment = "[任务]")
//@DynamicUpdate
//@DynamicInsert
public class PipelineTask extends BaseEntity{

	public static final String PROPERTY_STAGE = "stage";
	public static final String PROPERTY_EXEC_ORDER = "execOrder";
	public static final String PROPERTY_TASK_TYPE = "taskType";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 所属阶段
    * 
    */
    @Column(name = "stage", nullable = true, updatable = true)
	private Long stage;

    /**
    * 执行排序
    * 
    */
    @Column(name = "exec_order", nullable = true, updatable = true)
	private Integer execOrder;

    /**
    * 任务类型
    * 
    */
    @Column(name = "task_type", nullable = true, updatable = true)
	private Long taskType;

	public Long getStage(){
		return stage;
	}
	public void setStage(Long stage) {
		this.stage = stage;
	}

	public Integer getExecOrder(){
		return execOrder;
	}
	public void setExecOrder(Integer execOrder) {
		this.execOrder = execOrder;
	}

	public Long getTaskType(){
		return taskType;
	}
	public void setTaskType(Long taskType) {
		this.taskType = taskType;
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

