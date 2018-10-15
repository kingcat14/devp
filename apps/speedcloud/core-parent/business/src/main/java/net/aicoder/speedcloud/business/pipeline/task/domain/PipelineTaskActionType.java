package net.aicoder.speedcloud.business.pipeline.task.domain;

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
 * 操作类型
 * @author icode
 */
@Entity
@Table(appliesTo = "pipeline_task_action_type", comment = "[操作类型]")
//@DynamicUpdate
//@DynamicInsert
public class PipelineTaskActionType extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_VIEW_ORDER = "viewOrder";
	public static final String PROPERTY_MEMO = "memo";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_CONTENT = "content";


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
    * 类型代码
    * 
    */
    @Column(name = "code", nullable = true, updatable = true)
	@Size(max = 255, message = "类型代码超长，最多255个字符")
	private String code;

    /**
    * 类型名称
    * 
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "类型名称超长，最多255个字符")
	private String name;

    /**
    * 展现顺序
    * 
    */
    @Column(name = "view_order", nullable = true, updatable = true)
	@Size(max = 255, message = "展现顺序超长，最多255个字符")
	private String viewOrder;

    /**
    * 说明
    * 
    */
    @Column(name = "memo", nullable = true, updatable = true)
	@Size(max = 255, message = "说明超长，最多255个字符")
	private String memo;

    /**
    * 描述
    * 
    */
    @Column(name = "description", nullable = true, updatable = true, length=1999, columnDefinition = "TEXT")
	private String description;

    /**
    * 脚本内容
    * 
    */
    @Column(name = "content", nullable = true, updatable = true, length=1999, columnDefinition = "TEXT")
	private String content;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getViewOrder(){
		return viewOrder;
	}
	public void setViewOrder(String viewOrder) {
		this.viewOrder = viewOrder;
	}

	public String getMemo(){
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent(){
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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

