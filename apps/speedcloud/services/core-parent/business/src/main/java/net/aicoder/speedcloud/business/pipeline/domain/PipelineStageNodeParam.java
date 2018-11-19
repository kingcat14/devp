package net.aicoder.speedcloud.business.pipeline.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;



/**
 * 阶段执行节点参数
 * @author icode
 */
@Entity
@Table(appliesTo = "pipeline_stage_node_param", comment = "[阶段执行节点参数]")
//@DynamicUpdate
//@DynamicInsert
public class PipelineStageNodeParam extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_VALUE = "value";
	public static final String PROPERTY_VIEW_ORDER = "viewOrder";
	public static final String PROPERTY_PIPELINE_STAGE_NODE = "pipelineStageNode";


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
    * 参数名称
    * 
    */
    @Column(name = "name", nullable = false, updatable = false)
	@Size(max = 255, message = "参数名称超长，最多255个字符")
	private String name;

    /**
    * 参数值
    * 
    */
    @Column(name = "value", nullable = false, updatable = true)
	@Size(max = 255, message = "参数值超长，最多255个字符")
	private String value;

    /**
    * 展现顺序
    * 
    */
    @Column(name = "view_order", nullable = true, updatable = true)
	private Integer viewOrder;

    /**
    * 阶段执行节点
    * 
    */
    @Column(name = "pipeline_stage_node", nullable = false, updatable = true)
	private Long pipelineStageNode;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getValue(){
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public Integer getViewOrder(){
		return viewOrder;
	}
	public void setViewOrder(Integer viewOrder) {
		this.viewOrder = viewOrder;
	}

	public Long getPipelineStageNode(){
		return pipelineStageNode;
	}
	public void setPipelineStageNode(Long pipelineStageNode) {
		this.pipelineStageNode = pipelineStageNode;
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

