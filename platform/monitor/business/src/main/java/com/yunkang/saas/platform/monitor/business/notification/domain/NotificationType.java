package com.yunkang.saas.platform.monitor.business.notification.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 通知方式
 * @author icode
 */
@Entity()
@Table(name = "notification_notification_type")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class NotificationType extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_CODE = "code";


    @Id
    @Column(name = "id")
    private String id;


    /**
    * 租户id
    * 
    */
    @Column(name = "tid", nullable = false, updatable = false)
	private Long tid;

    /**
    * 通知方式
    * SMS、EMAIL
    */
    @Column(name = "code", nullable = false, updatable = false)
	@Size(max = 255, message = "通知方式超长，最多255个字符")
	private String code;

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

