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
 * JenkinsAdapter
 * @author icode
 */
@Entity()
@Table(name = "pipeline_jenkins_adapter")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class JenkinsAdapter extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_PROJECT = "project";
	public static final String PROPERTY_ENV = "env";
	public static final String PROPERTY_PORT = "port";
	public static final String PROPERTY_HOST = "host";


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
    * 所属产品
    * 
    */
    @Column(name = "project", nullable = false, updatable = true)
	@Size(max = 255, message = "所属产品超长，最多255个字符")
	private String project;

    /**
    * 所属环境
    * 
    */
    @Column(name = "env", nullable = false, updatable = true)
	@Size(max = 255, message = "所属环境超长，最多255个字符")
	private String env;

    /**
    * 端口
    * 
    */
    @Column(name = "port", nullable = false, updatable = true)
	private Integer port;

    /**
    * IP
    * 
    */
    @Column(name = "host", nullable = false, updatable = true)
	@Size(max = 255, message = "IP超长，最多255个字符")
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

