package com.yunkang.saas.bootstrap.platform.business.platform.tenant.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.context.ApplicationEvent;


/**
 * 租户新增事件
 * @author icode
 */
public class TenantAddEvent extends ApplicationEvent {

	private Tenant tenant;

	/**
	 * Create a new ApplicationEvent.
	 *
	 * @param source the object on which the event initially occurred (never {@code null})
	 */
	public TenantAddEvent(Object source, Tenant tenant) {
		super(source);
		this.tenant = tenant;
	}

	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

