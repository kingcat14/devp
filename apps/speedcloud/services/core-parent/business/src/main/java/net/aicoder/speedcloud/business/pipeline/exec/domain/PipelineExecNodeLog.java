package net.aicoder.speedcloud.business.pipeline.exec.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 运行节点日志
 * @author icode
 */
@Entity()
@Table(name = "pipeline_exec_node_log")
//@DynamicUpdate
//@DynamicInsert
public class PipelineExecNodeLog extends BaseEntity<String>{

	public static final String PROPERTY_LOG = "log";


    @Id
    @Column(name = "id")
    private String id;

    private String status;

	@Column(name = "result", nullable = true, updatable = true)
    private String result;

    /**log*/
    @Column(name = "log", nullable = true, updatable = true)
	private String log;


	public String getLog(){
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

