package net.aicoder.speedcloud.business.pipeline.exec.domain;

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
 * 运行节点日志
 * @author icode
 */
@Entity(name = "pipeline_pipeline_exec_node_log")
@Table(appliesTo = "pipeline_pipeline_exec_node_log", comment = "[运行节点日志]")
//@DynamicUpdate
//@DynamicInsert
public class PipelineExecNodeLog extends BaseEntity<String>{

	public static final String PROPERTY_LOG = "log";


    @Id
    @Column(name = "id")
    private String id;


    /**
    * log
    * 
    */
    @Column(name = "log", nullable = true, updatable = true)
	@Size(max = 255, message = "log超长，最多255个字符")
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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

