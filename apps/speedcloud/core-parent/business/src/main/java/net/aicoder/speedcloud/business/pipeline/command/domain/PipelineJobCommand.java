package net.aicoder.speedcloud.business.pipeline.command.domain;

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
 * 创建Job指令
 * @author icode
 */
@Entity
@Table(appliesTo = "pipeline_job_command", comment = "[创建Job指令]")
//@DynamicUpdate
//@DynamicInsert
public class PipelineJobCommand extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_TASK = "task";
	public static final String PROPERTY_CREATE_TIME = "createTime";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_RESULT = "result";


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
    * 关联任务
    * 
    */
    @Column(name = "task", nullable = true, updatable = true)
	private Long task;

    /**
    * 创建时间
    * 
    */
    @Column(name = "create_time", nullable = true, updatable = true)
	private Date createTime;

    /**
    * 状态
    * 未执行
    */
    @Column(name = "status", nullable = true, updatable = true)
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;

    /**
    * 之类类型
    * 新增修改删除
    */
    @Column(name = "type", nullable = true, updatable = true)
	@Size(max = 255, message = "之类类型超长，最多255个字符")
	private String type;

    /**
    * 执行结果
    * 
    */
    @Column(name = "result", nullable = true, updatable = true)
	@Size(max = 255, message = "执行结果超长，最多255个字符")
	private String result;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getTask(){
		return task;
	}
	public void setTask(Long task) {
		this.task = task;
	}

	public Date getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getResult(){
		return result;
	}
	public void setResult(String result) {
		this.result = result;
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

