package com.yunkang.saas.platform.authentication.business.platform.tenant.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;



/**
 * 租户开通的应用
 * @author icode
 */
@Entity()
@Table(name = "platform_app_tenant_relation")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class AppTenantRelation extends BaseEntity<Long>{

	public static final String PROPERTY_APP = "app";
	public static final String PROPERTY_ACCOUNT = "account";
	public static final String PROPERTY_START_DATE = "startDate";
	public static final String PROPERTY_END_DATE = "endDate";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 应用
    * 
    */
    @Column(name = "app", nullable = true, updatable = true)
	@Size(max = 255, message = "应用超长，最多255个字符")
	private String app;

    /**
    * 账号
    * 
    */
    @Column(name = "account", nullable = true, updatable = true)
	@Size(max = 255, message = "账号超长，最多255个字符")
	private String account;

    /**
    * 生效日期
    * 
    */
    @Column(name = "start_date", nullable = true, updatable = true)
	@Temporal(TemporalType.DATE)
	private Date startDate;

    /**
    * 截止日期
    * 
    */
    @Column(name = "end_date", nullable = true, updatable = true)
	@Temporal(TemporalType.DATE)
	private Date endDate;

	public String getApp(){
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}

	public String getAccount(){
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}

	public Date getStartDate(){
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate(){
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

