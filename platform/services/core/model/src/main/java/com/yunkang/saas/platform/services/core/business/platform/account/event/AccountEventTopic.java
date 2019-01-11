package com.yunkang.saas.platform.services.core.business.platform.account.event;

import com.yunkang.saas.common.framework.message.DomainEvent;

public enum AccountEventTopic implements DomainEvent {

	CREATE("create")
	,DELETE("delete")
	,UPDATE("update");

	private String topic = "core.platform.account";
	private String value;
  
	AccountEventTopic(String event){
		this.value = event;
	}

	@Override
	public String toString() {
		return topic+"."+this.value;
	}

	public String getTopic(){
		return topic;
	}

	@Override
	public String getEventType() {
		return value;
	}

}