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
 * 阶段任务关联
 * @author icode
 */
@Entity
@Table(appliesTo = "pipeline_stage_task_relation", comment = "[阶段任务关联]")
//@DynamicUpdate
//@DynamicInsert
public class PipelineStageTaskRelation extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_STAGE = "stage";
	public static final String PROPERTY_TASK = "task";
	public static final String PROPERTY_EXEC_ORDER = "execOrder";


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
    * 阶段
    * 
    */
    @Column(name = "stage", nullable = false, updatable = true)
	private Long stage;

    /**
    * 任务
    * 
    */
    @Column(name = "task", nullable = false, updatable = true)
	private Long task;

    /**
    * 执行排序
    * 
    */
    @Column(name = "exec_order", nullable = false, updatable = true)
	private Integer execOrder;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getStage(){
		return stage;
	}
	public void setStage(Long stage) {
		this.stage = stage;
	}

	public Long getTask(){
		return task;
	}
	public void setTask(Long task) {
		this.task = task;
	}

	public Integer getExecOrder(){
		return execOrder;
	}
	public void setExecOrder(Integer execOrder) {
		this.execOrder = execOrder;
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

