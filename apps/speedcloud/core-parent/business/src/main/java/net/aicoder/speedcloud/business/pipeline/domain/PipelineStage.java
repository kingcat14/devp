package net.aicoder.speedcloud.business.pipeline.domain;

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
 * 阶段
 * @author icode
 */
@Entity
@Table(appliesTo = "pipeline_stage", comment = "[阶段]")
//@DynamicUpdate
//@DynamicInsert
public class PipelineStage extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_PIPELINE = "pipeline";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_FLOW_TYPE = "flowType";
	public static final String PROPERTY_EXEC_MODE = "execMode";
	public static final String PROPERTY_EXEC_ORDER = "execOrder";


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
    * 所属流水线
    * 
    */
    @Column(name = "pipeline", nullable = true, updatable = true)
	private Long pipeline;

    /**
    * 阶段名称
    * 
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "阶段名称超长，最多255个字符")
	private String name;

    /**
    * 流转方式:自动、手动
    */
    @Column(name = "flow_type", nullable = true, updatable = true)
	private String flowType;

    /**
    * 执行方式
    * 并行、串行、
    */
    @Column(name = "exec_mode", nullable = false, updatable = true)
	private String execMode;

	/**执行顺序*/
	@Column(name = "exec_order", nullable = false, updatable = true)
    private Integer execOrder;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getPipeline(){
		return pipeline;
	}
	public void setPipeline(Long pipeline) {
		this.pipeline = pipeline;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getFlowType(){
		return flowType;
	}
	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}

	public String getExecMode(){
		return execMode;
	}
	public void setExecMode(String execMode) {
		this.execMode = execMode;
	}

	public Integer getExecOrder() {
		return execOrder;
	}
	public void setExecOrder(Integer execOrder) {
		this.execOrder = execOrder;
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

