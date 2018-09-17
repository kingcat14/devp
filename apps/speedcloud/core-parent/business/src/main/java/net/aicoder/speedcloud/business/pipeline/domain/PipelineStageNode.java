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
 * 阶段执行节点
 * @author icode
 */
@Entity
@Table(appliesTo = "pipeline_stage_node", comment = "[阶段执行节点]")
//@DynamicUpdate
//@DynamicInsert
public class PipelineStageNode extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_STAGE = "stage";
	public static final String PROPERTY_NODE_TYPE = "nodeType";
	public static final String PROPERTY_NODE_ID = "nodeId";
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
    * 名称
    * 
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;

    /**
    * 阶段
    * 
    */
    @Column(name = "stage", nullable = false, updatable = true)
	private Long stage;

    /**
    * 节点类型
    * 
    */
    @Column(name = "node_type", nullable = true, updatable = true)
	private String nodeType;

    /**
    * 节点节点ID
    * 
    */
    @Column(name = "node_id", nullable = true, updatable = true)
	private Long nodeId;

    /**
    * 执行排序
    * 
    */
    @Column(name = "exec_order", nullable = false, updatable = true)
	private Integer execOrder;

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

	public Long getStage(){
		return stage;
	}
	public void setStage(Long stage) {
		this.stage = stage;
	}

	public String getNodeType(){
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public Long getNodeId(){
		return nodeId;
	}
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public Integer getExecOrder(){
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

