package net.aicoder.speedcloud.business.pipeline.jenkins.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 任务映射
 * @author icode
 */
@ApiModel(value = "修改任务映射使用的DTO")
@Setter @Getter
public class JobMappingEditDto {


	/**所属产品*/
	@ApiModelProperty(value = "所属产品", required = false)
	private String project;


	/**类型*/
	@ApiModelProperty(value = "类型", required = false, notes = "流水线、JOB")
	private String taskType;


	/**任务或流水线*/
	@ApiModelProperty(value = "任务或流水线", required = false)
	private String jobInPlatform;


	/**任务或流水线名称*/
	@ApiModelProperty(value = "任务或流水线名称", required = false)
	private String jobInPlatformName;


	/**Jenkins中任务名称*/
	@ApiModelProperty(value = "Jenkins中任务名称", required = false)
	private String jobInJenkinsName;



	public String getProject(){
        return project;
    }
    public void setProject(String project) {
        this.project = project;
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
