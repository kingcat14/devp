package net.aicoder.speedcloud.business.pipeline.exec.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotEmpty;



/**
 * 运行实例节点
 * @author icode
 */
@Entity
@Table(appliesTo = "pipeline_exec_instance_node", comment = "[运行实例节点]")
//@DynamicUpdate
//@DynamicInsert
public class PipelineExecInstanceNode extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_NODE_TYPE = "nodeType";
	public static final String PROPERTY_EXEC_MODE = "execMode";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_RESULT = "result";
	public static final String PROPERTY_EXEC = "exec";
	public static final String PROPERTY_RESULT_MESSAGE = "resultMessage";
	public static final String PROPERTY_START_TIME = "startTime";
	public static final String PROPERTY_PARENT_ID = "parentId";
	public static final String PROPERTY_TASK = "task";
	public static final String PROPERTY_AUTO_START = "autoStart";
	public static final String PROPERTY_EXEC_INDEX = "execIndex";


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
    * 编号
    * 
    */
    @Column(name = "code", nullable = true, updatable = true)
	@Size(max = 255, message = "编号超长，最多255个字符")
	private String code;

    /**
    * 节点名称
    * 节点或任务的名称
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "节点名称超长，最多255个字符")
	private String name;

    /**
    * 节点类型
    * 阶段、任务
    */
    @Column(name = "node_type", nullable = false, updatable = true)
	@Size(max = 255, message = "节点类型超长，最多255个字符")
	private String nodeType;

    /**
    * 执行方式
    * 并行、串行
    */
    @Column(name = "exec_mode", nullable = false, updatable = true)
	@Size(max = 255, message = "执行方式超长，最多255个字符")
	private String execMode;

    /**
    * 运行状态
    * 未开始、等待中、运行中、已结束
    */
    @Column(name = "status", nullable = true, updatable = true)
	@Size(max = 255, message = "运行状态超长，最多255个字符")
	private String status;

    /**
    * 运行结果
    * 成功、失败
    */
    @Column(name = "result", nullable = true, updatable = true)
	@Size(max = 255, message = "运行结果超长，最多255个字符")
	private String result;

    /**
    * 所属实例
    * 
    */
    @Column(name = "exec", nullable = false, updatable = true)
	private Long exec;

    /**
    * 结果消息
    * 
    */
    @Column(name = "result_message", nullable = true, updatable = true)
	@Size(max = 255, message = "结果消息超长，最多255个字符")
	private String resultMessage;

    /**
    * 开始时间
    * 
    */
    @Column(name = "start_time", nullable = true, updatable = true)
	private Date startTime;

    /**
    * 上级节点
    * 
    */
    @Column(name = "parent_id", nullable = true, updatable = false)
	private Long parentId;

    /**
    * 关联任务
    * 
    */
    @Column(name = "task", nullable = false, updatable = true)
	private Long task;

    /**
    * 自动运行
    * 手动、自动
    */
    @Column(name = "auto_start", nullable = false, updatable = true)
	private Boolean autoStart;

    /**
    * 节点排序
    * 
    */
    @Column(name = "exec_index", nullable = false, updatable = true)
	private Integer execIndex;

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

	public String getNodeType(){
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getExecMode(){
		return execMode;
	}
	public void setExecMode(String execMode) {
		this.execMode = execMode;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getResult(){
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	public Long getExec(){
		return exec;
	}
	public void setExec(Long exec) {
		this.exec = exec;
	}

	public String getResultMessage(){
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public Date getStartTime(){
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Long getParentId(){
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getTask(){
		return task;
	}
	public void setTask(Long task) {
		this.task = task;
	}

	public Boolean getAutoStart(){
		return autoStart;
	}
	public void setAutoStart(Boolean autoStart) {
		this.autoStart = autoStart;
	}

	public Integer getExecIndex(){
		return execIndex;
	}
	public void setExecIndex(Integer execIndex) {
		this.execIndex = execIndex;
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

