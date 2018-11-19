package net.aicoder.speedcloud.business.pipeline.exec.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Date;



/**
 * 运行计划
 * @author icode
 */
@Entity
@Table(appliesTo = "pipeline_exec_instance", comment = "[运行计划]")
//@DynamicUpdate
//@DynamicInsert
public class PipelineExecInstance extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_EXECUTE_TARGET_ID = "executeTargetId";
	public static final String PROPERTY_EXECUTE_TARGET_TYPE = "executeTargetType";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_RESULT = "result";
	public static final String PROPERTY_START_TIME = "startTime";


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
    * 运行主体
    * 
    */
    @Column(name = "execute_target_id", nullable = true, updatable = true)
	private Long executeTargetId;

    /**
    * 运行类型
    * 流水线、任务
    */
    @Column(name = "execute_target_type", nullable = true, updatable = true)
	@Size(max = 255, message = "运行类型超长，最多255个字符")
	private String executeTargetType;

    /**
    * 运行状态
    * 未开始、运行中、已结束
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

    /**开始时间*/
    @Column(name = "start_time", nullable = true, updatable = true)
	private Date startTime;

	/**结束时间*/
	@Column(name = "finish_time", nullable = true, updatable = true)
	private Date finishTime;

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

	public Long getExecuteTargetId(){
		return executeTargetId;
	}
	public void setExecuteTargetId(Long executeTargetId) {
		this.executeTargetId = executeTargetId;
	}

	public String getExecuteTargetType(){
		return executeTargetType;
	}
	public void setExecuteTargetType(String executeTargetType) {
		this.executeTargetType = executeTargetType;
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

	public Date getStartTime(){
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
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

