package net.aicoder.speedcloud.business.app.event;

import com.yunkang.saas.common.framework.message.DomainEvent;

public enum AppBaseInfoEventTopic implements DomainEvent {

	CREATE("create")
	,DELETE("delete")
	,UPDATE("update");

	private String topic = "speedcloud.app";
	private String value;

	AppBaseInfoEventTopic(String event){
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