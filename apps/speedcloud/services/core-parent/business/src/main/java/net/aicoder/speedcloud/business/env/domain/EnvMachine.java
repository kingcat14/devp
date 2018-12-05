package net.aicoder.speedcloud.business.env.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 环境设备关联
 * @author icode
 */
@Entity()
@Table(name = "env_env_machine")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class EnvMachine extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_EVN = "evn";
	public static final String PROPERTY_MACHINE = "machine";


    @Id
    @Column(name = "id", length = 32)
    private Long id;


    /**
    * 租户id
    * 
    */
    @Column(name = "tid", nullable = false, updatable = false)
	private Long tid;

    /**
    * 环境
    * 
    */
    @Column(name = "evn", nullable = true, updatable = true)
	@Size(max = 255, message = "环境超长，最多255个字符")
	private String evn;

    /**
    * 机器
    * 
    */
    @Column(name = "machine", nullable = true, updatable = true)
	@Size(max = 255, message = "机器超长，最多255个字符")
	private String machine;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getEvn(){
		return evn;
	}
	public void setEvn(String evn) {
		this.evn = evn;
	}

	public String getMachine(){
		return machine;
	}
	public void setMachine(String machine) {
		this.machine = machine;
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

