package com.yunkang.saas.platform.monitor.business.supporter.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 支持应用
 * @author icode
 */
@Entity()
@Table(name = "supporter_supporter_app_relation")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class SupporterAppRelation extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_SUPPORTER = "supporter";
	public static final String PROPERTY_APPLICATION = "application";
	public static final String PROPERTY_NOTIFICATION_TYPE = "notificationType";


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
    * 运维人员
    * 
    */
    @Column(name = "supporter", nullable = true, updatable = true)
	@Size(max = 255, message = "运维人员超长，最多255个字符")
	private String supporter;

    /**
    * 支持程序
    * 
    */
    @Column(name = "application", nullable = true, updatable = true)
	@Size(max = 255, message = "支持程序超长，最多255个字符")
	private String application;

    /**
    * 接收通知方式
    * 逗号分隔的告警方式
    */
    @Column(name = "notification_type", nullable = true, updatable = true)
	@Size(max = 255, message = "接收通知方式超长，最多255个字符")
	private String notificationType;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getSupporter(){
		return supporter;
	}
	public void setSupporter(String supporter) {
		this.supporter = supporter;
	}

	public String getApplication(){
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}

	public String getNotificationType(){
		return notificationType;
	}
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
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

