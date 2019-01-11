package net.aicoder.speedcloud.business.pipeline.jenkins.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 任务映射
 * @author icode
 */
@Entity()
@Table(name = "pipeline_job_mapping")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class JobMapping extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_TASK_TYPE = "taskType";
	public static final String PROPERTY_JOB_IN_PLATFORM = "jobInPlatform";
	public static final String PROPERTY_JOB_IN_PLATFORM_NAME = "jobInPlatformName";
	public static final String PROPERTY_JOB_IN_JENKINS_NAME = "jobInJenkinsName";


    @Id
    @Column(name = "id", length = 32)
    private String id;


    /**
    * 租户id
    * 
    */
    @Column(name = "tid", nullable = false, updatable = false)
	private Long tid;

    /**
    * 类型
    * 流水线、JOB
    */
    @Column(name = "task_type", nullable = false, updatable = true)
	@Size(max = 255, message = "类型超长，最多255个字符")
	private String taskType;

    /**
    * 任务或流水线
    * 
    */
    @Column(name = "job_in_platform", nullable = false, updatable = true)
	@Size(max = 255, message = "任务或流水线超长，最多255个字符")
	private String jobInPlatform;

    /**
    * 任务或流水线名称
    * 
    */
    @Column(name = "job_in_platform_name", nullable = true, updatable = true)
	@Size(max = 255, message = "任务或流水线名称超长，最多255个字符")
	private String jobInPlatformName;

    /**
    * Jenkins中任务名称
    * 
    */
    @Column(name = "job_in_jenkins_name", nullable = false, updatable = true)
	@Size(max = 255, message = "Jenkins中任务名称超长，最多255个字符")
	private String jobInJenkinsName;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getTaskType(){
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getJobInPlatform(){
		return jobInPlatform;
	}
	public void setJobInPlatform(String jobInPlatform) {
		this.jobInPlatform = jobInPlatform;
	}

	public String getJobInPlatformName(){
		return jobInPlatformName;
	}
	public void setJobInPlatformName(String jobInPlatformName) {
		this.jobInPlatformName = jobInPlatformName;
	}

	public String getJobInJenkinsName(){
		return jobInJenkinsName;
	}
	public void setJobInJenkinsName(String jobInJenkinsName) {
		this.jobInJenkinsName = jobInJenkinsName;
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

