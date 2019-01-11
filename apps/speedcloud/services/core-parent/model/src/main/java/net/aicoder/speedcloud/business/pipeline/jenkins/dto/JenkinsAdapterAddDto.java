package net.aicoder.speedcloud.business.pipeline.jenkins.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * JenkinsAdapter
 * @author icode
 */
@ApiModel(value = "新增JenkinsAdapter使用的DTO")
@Setter @Getter
public class JenkinsAdapterAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**所属产品*/
	@ApiModelProperty(value = "所属产品", required = true)
	private String project;

    /**所属环境*/
	@ApiModelProperty(value = "所属环境", required = true)
	private String env;

    /**端口*/
	@ApiModelProperty(value = "端口", required = true)
	private Integer port;

    /**IP*/
	@ApiModelProperty(value = "IP", required = true)
	private String host;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getProject(){
        return project;
    }
    public void setProject(String project) {
        this.project = project;
    }

	public String getEnv(){
        return env;
    }
    public void setEnv(String env) {
        this.env = env;
    }

	public Integer getPort(){
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}

	public String getHost(){
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
