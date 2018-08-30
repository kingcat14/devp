package net.aicoder.speedcloud.business.env.domain;

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
 * 环境设备关联
 * @author icode
 */
@Entity
@Table(appliesTo = "env_machine", comment = "[环境设备关联]")
//@DynamicUpdate
//@DynamicInsert
public class EnvMachine extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_EVN = "evn";
	public static final String PROPERTY_MACHINE = "machine";


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
    * 环境
    * 
    */
    @Column(name = "evn", nullable = true, updatable = true)
	private Long evn;

    /**
    * 机器
    * 
    */
    @Column(name = "machine", nullable = true, updatable = true)
	private Long machine;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getEvn(){
		return evn;
	}
	public void setEvn(Long evn) {
		this.evn = evn;
	}

	public Long getMachine(){
		return machine;
	}
	public void setMachine(Long machine) {
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

