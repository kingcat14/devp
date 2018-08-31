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

	public static final String PROPERTY_PIPELINE = "pipeline";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_FLOW_TYPE = "flowType";


    @Id
    @Column(name = "id")
    private Long id;


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
    * 流转方式
    * 流转到下一阶段方式:自动、手动
    */
    @Column(name = "flow_type", nullable = true, updatable = true)
	private String flowType;

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

