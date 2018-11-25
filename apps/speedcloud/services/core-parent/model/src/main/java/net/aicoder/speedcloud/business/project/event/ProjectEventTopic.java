package net.aicoder.speedcloud.business.project.event;

import com.yunkang.saas.common.framework.message.DomainEvent;

public enum ProjectEventTopic implements DomainEvent {

	CREATE("create")
	,DELETE("delete")
	,UPDATE("update");

	private String topic = "speedcloud.project";
	private String value;

	ProjectEventTopic(String event){
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