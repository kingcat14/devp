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
 * 运行实例
 * @author icode
 */
@Entity
@Table(appliesTo = "exec", comment = "[运行实例]")
//@DynamicUpdate
//@DynamicInsert
public class Exec extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_RUNNER_ID = "runnerId";
	public static final String PROPERTY_RUNNER_TYPE = "runnerType";
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
    @Column(name = "runner_id", nullable = true, updatable = true)
	private Long runnerId;

    /**
    * 运行类型
    * 流水线、任务
    */
    @Column(name = "runner_type", nullable = true, updatable = true)
	@Size(max = 255, message = "运行类型超长，最多255个字符")
	private String runnerType;

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

    /**
    * 开始时间
    * 
    */
    @Column(name = "start_time", nullable = true, updatable = true)
	private Date startTime;

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

	public Long getRunnerId(){
		return runnerId;
	}
	public void setRunnerId(Long runnerId) {
		this.runnerId = runnerId;
	}

	public String getRunnerType(){
		return runnerType;
	}
	public void setRunnerType(String runnerType) {
		this.runnerType = runnerType;
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

