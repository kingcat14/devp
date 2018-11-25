package net.aicoder.speedcloud.business.pipeline.task.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;



/**
 * 任务
 * @author icode
 */
@Entity
@Table(appliesTo = "pipeline_task", comment = "[任务]")
//@DynamicUpdate
//@DynamicInsert
public class PipelineTask extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_TASK_TYPE = "taskType";
	public static final String PROPERTY_EXEC_TYPE = "execType";
	public static final String PROPERTY_TASK_START_TIME = "taskStartTime";
	public static final String PROPERTY_TASK_DAY_OF_WEEKS = "taskDayOfWeeks";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_PROJECT = "project";


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
    * 任务名称
    * 
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "任务名称超长，最多255个字符")
	private String name;

    /**
    * 任务类型
    * 
    */
    @Column(name = "task_type", nullable = true, updatable = true)
	private String taskType;

    /**
    * 执行计划
    * 手工，每日，每周
    */
    @Column(name = "exec_type", nullable = true, updatable = true)
	private String execType;

    /**
    * 执行开始时间
    * 数据格式 HH:ss
    */
    @Column(name = "task_start_time", nullable = true, updatable = true)
	@Size(max = 255, message = "执行开始时间超长，最多255个字符")
	private String taskStartTime;

    /**
    * 执行日
    * 1,2,3,4,5,6,7
    */
    @Column(name = "task_day_of_weeks", nullable = true, updatable = true)
	@Size(max = 255, message = "执行日超长，最多255个字符")
	private String taskDayOfWeeks;

    /**
    * 任务描述
    * 
    */
    @Column(name = "description", nullable = true, updatable = true, length=1999, columnDefinition = "TEXT")
	private String description;

    /**
    * 所属产品
    * 
    */
    @Column(name = "project", nullable = false, updatable = true)
	private String project;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getTaskType(){
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getExecType(){
		return execType;
	}
	public void setExecType(String execType) {
		this.execType = execType;
	}

	public String getTaskStartTime(){
		return taskStartTime;
	}
	public void setTaskStartTime(String taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public String getTaskDayOfWeeks(){
		return taskDayOfWeeks;
	}
	public void setTaskDayOfWeeks(String taskDayOfWeeks) {
		this.taskDayOfWeeks = taskDayOfWeeks;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getProject(){
		return project;
	}
	public void setProject(String project) {
		this.project = project;
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

