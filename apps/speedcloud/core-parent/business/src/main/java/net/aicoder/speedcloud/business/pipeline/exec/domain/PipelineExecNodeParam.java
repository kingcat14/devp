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
 * 运行实例节点参数
 * @author icode
 */
@Entity
@Table(appliesTo = "pipeline_exec_node_param", comment = "[运行实例节点参数]")
//@DynamicUpdate
//@DynamicInsert
public class PipelineExecNodeParam extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_NODE = "node";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_VALUE = "value";
	public static final String PROPERTY_VIEW_ORDER = "viewOrder";
	public static final String PROPERTY_TYPE = "type";


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
    * 所属节点
    * 
    */
    @Column(name = "node", nullable = true, updatable = true)
	private Long node;

    /**
    * 属性名称
    * 
    */
    @Column(name = "name", nullable = false, updatable = false)
	@Size(max = 255, message = "属性名称超长，最多255个字符")
	private String name;

    /**
    * 属性代码
    * 
    */
    @Column(name = "code", nullable = true, updatable = true)
	@Size(max = 255, message = "属性代码超长，最多255个字符")
	private String code;

    /**
    * 属性值
    * 执行时最终使用的值
    */
    @Column(name = "value", nullable = true, updatable = true, length=1999, columnDefinition = "TEXT")
	private String value;

    /**
    * 排序
    * 
    */
    @Column(name = "view_order", nullable = true, updatable = true)
	private Integer viewOrder;

    /**
    * 类型
    * 
    */
    @Column(name = "type", nullable = true, updatable = true)
	@Size(max = 255, message = "类型超长，最多255个字符")
	private String type;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getNode(){
		return node;
	}
	public void setNode(Long node) {
		this.node = node;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
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

