package net.aicoder.speedcloud.business.pipeline.jenkins.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import net.aicoder.speedcloud.business.env.vo.AppEnvConfigVO;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* JenkinsAdapter的值对象
*/
@ApiModel(value = "展现JenkinsAdapter的值对象")
@Setter @Getter
public class JenkinsAdapterVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**所属产品*/
    @ApiModelProperty(value = "所属产品")
    private String project;
    private ProjectVO projectVO;


    /**所属环境*/
    @ApiModelProperty(value = "所属环境")
    private String env;
    private AppEnvConfigVO envVO;


    @ApiModelProperty(value = "端口")
    private Integer port;


    @ApiModelProperty(value = "IP")
    private String host;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}