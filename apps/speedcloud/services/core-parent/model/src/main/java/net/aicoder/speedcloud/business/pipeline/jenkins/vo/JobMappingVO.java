package net.aicoder.speedcloud.business.pipeline.jenkins.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 任务映射的值对象
*/
@ApiModel(value = "展现任务映射的值对象")
@Setter @Getter
public class JobMappingVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**所属产品*/
    @ApiModelProperty(value = "所属产品")
    private String project;
    private ProjectVO projectVO;


    @ApiModelProperty(value = "类型", notes = "流水线、JOB")
    private String taskType;


    @ApiModelProperty(value = "任务或流水线")
    private String jobInPlatform;


    @ApiModelProperty(value = "任务或流水线名称")
    private String jobInPlatformName;


    @ApiModelProperty(value = "Jenkins中任务名称")
    private String jobInJenkinsName;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}