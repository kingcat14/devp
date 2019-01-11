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
@ApiModel(value = "修改JenkinsAdapter使用的DTO")
@Setter @Getter
public class JenkinsAdapterEditDto {


	/**所属产品*/
	@ApiModelProperty(value = "所属产品", required = false)
	private String project;


	/**所属环境*/
	@ApiModelProperty(value = "所属环境", required = false)
	private String env;


	/**端口*/
	@ApiModelProperty(value = "端口", required = true)
	private Integer port;


	/**IP*/
	@ApiModelProperty(value = "IP", required = true)
	private String host;



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
