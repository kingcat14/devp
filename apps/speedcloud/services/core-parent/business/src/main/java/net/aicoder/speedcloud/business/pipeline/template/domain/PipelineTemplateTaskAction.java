package net.aicoder.speedcloud.business.pipeline.template.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 操作模板
 * @author icode
 */
@Entity()
@Table(name = "pipeline_pipeline_template_task_action")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class PipelineTemplateTaskAction extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_TASK = "task";
	public static final String PROPERTY_MEMO = "memo";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_EXEC_INDEX = "execIndex";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_CONTENT = "content";


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
    * 所属任务
    * 
    */
    @Column(name = "task", nullable = false, updatable = true)
	@Size(max = 255, message = "所属任务超长，最多255个字符")
	private String task;

    /**
    * 操作说明
    * 
    */
    @Column(name = "memo", nullable = true, updatable = true)
	@Size(max = 255, message = "操作说明超长，最多255个字符")
	private String memo;

    /**
    * 操作名称
    * 
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "操作名称超长，最多255个字符")
	private String name;

    /**
    * 执行顺序
    * 
    */
    @Column(name = "exec_index", nullable = false, updatable = true)
	private Integer execIndex;

    /**
    * 操作类型
    * 
    */
    @Column(name = "type", nullable = true, updatable = true)
	private Long type;

    /**
    * 脚本内容
    * 脚本内容
    */
    @Column(name = "content", nullable = true, updatable = true, length=1999, columnDefinition = "TEXT")
	private String content;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getTask(){
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}

	public String getMemo(){
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getExecIndex(){
		return execIndex;
	}
	public void setExecIndex(Integer execIndex) {
		this.execIndex = execIndex;
	}

	public Long getType(){
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}

	public String getContent(){
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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

